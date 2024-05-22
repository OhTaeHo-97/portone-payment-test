package com.example.portone.payment.controller;

import com.example.portone.dto.PaymentDto.PostPayment;
import com.example.portone.dto.PaymentDto.PrePayment;
import com.example.portone.service.PortoneService;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.Certification;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class PaymentController {
    private final PortoneService portoneService;

    @PostMapping("/payment/prepare")
    public void preparePayment(@RequestBody PrePayment prePayment) throws IamportResponseException, IOException {
        portoneService.preparePayment(prePayment);
    }

    @PostMapping("/verifyIamport/{imp_uid}")
    public IamportResponse<Payment> validateIamport(@PathVariable String imp_uid) throws IamportResponseException, IOException {
        return portoneService.verifyPayment(imp_uid);
    }

    @PostMapping("/payment/validate")
    public Payment validatePayment(@RequestBody PostPayment postPayment) throws IamportResponseException, IOException {
        return portoneService.validatePayment(postPayment);
    }

    @PostMapping("/verifyCertification/{imp_uid}")
    public IamportResponse<Certification> validateIamPortCertification(@PathVariable String imp_uid) throws IamportResponseException, IOException {
        log.trace("trace: imp_uid : {}", imp_uid);
        log.debug("debug: imp_uid : {}", imp_uid);
        log.info("info: imp_uid : {}", imp_uid);
        log.warn("warn: imp_uid : {}", imp_uid);
        log.error("error: imp_uid : {}", imp_uid);
        return portoneService.verifyCertification(imp_uid);
    }
}
