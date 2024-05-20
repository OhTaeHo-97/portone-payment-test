package com.example.portone.log;

import static ch.qos.logback.classic.Level.DEBUG;
import static ch.qos.logback.classic.Level.INFO;
import static ch.qos.logback.classic.Level.OFF;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import org.slf4j.LoggerFactory;

public class LogbackConsole {
    private final LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
    private final String CONSOLE_PATTERN = "%d{yyyy-MM-dd HH:mm:ss.SSS} %magenta([%thread]) %highlight([%-3level]) %logger{5} - %msg %n";

    private ConsoleAppender<ILoggingEvent> consoleAppender;

    public void logConfig() {
        loggerContext.reset();
        consoleAppender = getLogAppender();
        createLoggers();
    }

    private void createLoggers() {
        createLogger("root", INFO, true);
        createLogger("jdbc", OFF, false);
        createLogger("jdbc.sqlonly", DEBUG, false);
        createLogger("jdbc.sqltiming", OFF, false);
        createLogger("org.hibernate.SQL", DEBUG, false);
        createLogger("com.example.portone.configuration", DEBUG, false);
        createLogger("com.example.portone.filter", DEBUG, false);
        createLogger("com.example.portone.payment.controller", INFO, true);
        createLogger("com.example.portone.service", DEBUG, true);
    }

    private void createLogger(String loggerName, Level logLevel, Boolean additive) {
        Logger logger = loggerContext.getLogger(loggerName);
        logger.setAdditive(additive);
        logger.setLevel(logLevel);

        logger.addAppender(consoleAppender);
    }

    private ConsoleAppender<ILoggingEvent> getLogAppender() {
        final String appendName = "STDOUT";
        PatternLayoutEncoder consoleLogEncoder = createLogEncoder(CONSOLE_PATTERN);
        ConsoleAppender<ILoggingEvent> logConsoleAppender = createLogAppender(appendName, consoleLogEncoder);
        logConsoleAppender.start();

        return logConsoleAppender;
    }

    private PatternLayoutEncoder createLogEncoder(String pattern) {
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(loggerContext);
        encoder.setPattern(pattern);
        encoder.start();

        return encoder;
    }

    private ConsoleAppender<ILoggingEvent> createLogAppender(String appendName,
                                                             PatternLayoutEncoder consoleLogEncoder) {
        ConsoleAppender<ILoggingEvent> logConsoleAppender = new ConsoleAppender<>();
        logConsoleAppender.setName(appendName);
        logConsoleAppender.setContext(loggerContext);
        logConsoleAppender.setEncoder(consoleLogEncoder);

        return logConsoleAppender;
    }
}
