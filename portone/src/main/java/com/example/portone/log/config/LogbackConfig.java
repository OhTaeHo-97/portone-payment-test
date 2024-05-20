package com.example.portone.log.config;

import com.example.portone.log.LogbackConsole;
import com.example.portone.log.LogbackRolling;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogbackConfig {
    @Bean
    public LogbackConsole logbackConsole() {
        LogbackConsole logbackConsole = new LogbackConsole();
        logbackConsole.logConfig();
        return logbackConsole;
    }

    @Bean
    public LogbackRolling logbackRolling() {
        LogbackRolling logbackRolling = new LogbackRolling();
        logbackRolling.logConfig();
        return logbackRolling;
    }
}
