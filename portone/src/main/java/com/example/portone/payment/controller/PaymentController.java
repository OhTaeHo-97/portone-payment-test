package com.example.portone.payment.controller;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import java.io.IOException;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class PaymentController {
    @Value("${iamport.key}")
    private String restApiKey;
    @Value("${iamport.secret}")
    private String restApiSecret;

    private IamportClient iamportClient;

    @PostConstruct
    public void init() {
        this.iamportClient = new IamportClient(restApiKey, restApiSecret);
    }

    @PostMapping("/verifyIamport/{imp_uid}")
    public IamportResponse<Payment> validateIamport(@PathVariable String imp_uid) throws IamportResponseException, IOException {
        IamportResponse<Payment> payment = iamportClient.paymentByImpUid(imp_uid);
        Payment paymentData = payment.getResponse();
        log.info("결제 금액 : {}", paymentData.getAmount());
        log.info("결제 요청 응답. 결제 내역 - 주문 번호 : {}", payment.getResponse());
        return payment;
    }
}
