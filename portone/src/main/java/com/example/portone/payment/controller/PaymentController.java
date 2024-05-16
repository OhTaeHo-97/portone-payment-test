package com.example.portone.payment.controller;

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
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class PaymentController {
    private final PortoneService portoneService;

    @PostMapping("/verifyIamport/{imp_uid}")
    public IamportResponse<Payment> validateIamport(@PathVariable String imp_uid) throws IamportResponseException, IOException {
        return portoneService.verifyPayment(imp_uid);
    }

    @PostMapping("/verifyCertification/{imp_uid}")
    public IamportResponse<Certification> validateIamPortCertification(@PathVariable String imp_uid) throws IamportResponseException, IOException {
        return portoneService.verifyCertification(imp_uid);
    }
}
