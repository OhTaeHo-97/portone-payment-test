package com.example.portone.service;

import com.example.portone.dto.PaymentDto.PrePayment;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.PrepareData;
import com.siot.IamportRestClient.response.Certification;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import com.siot.IamportRestClient.response.Prepare;
import java.io.IOException;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
public class PortoneService {
    @Value("${iamport.key}")
    private String restApiKey;
    @Value("${iamport.secret}")
    private String restApiSecret;

    private IamportClient iamportClient;

    @PostConstruct
    public void init() {
        this.iamportClient = new IamportClient(restApiKey, restApiSecret);
    }

    public void preparePayment(PrePayment prePayment) throws IamportResponseException, IOException {
        PrepareData prepareData = new PrepareData(prePayment.getMerchant_uid(), BigDecimal.valueOf(prePayment.getTotalPrice()));
        iamportClient.postPrepare(prepareData);
    }

    public IamportResponse<Payment> verifyPayment(String imp_uid) throws IamportResponseException, IOException {
        try {
            IamportResponse<Payment> payment = iamportClient.paymentByImpUid(imp_uid);
            Payment paymentData = payment.getResponse();
            log.info("결제 금액 : {}", paymentData.getAmount());
            log.info("결제 요청 응답. 결제 내역 - 주문 번호 : {}", payment.getResponse());
            return payment;
        } catch (IamportResponseException e) {
            throw new IllegalArgumentException("금액 잘못됨");
        } catch (IOException e) {
            throw new RuntimeException("서버 오류");
        }
    }

    public IamportResponse<Certification> verifyCertification(String imp_uid) throws IamportResponseException, IOException {
        IamportResponse<Certification> certification = iamportClient.certificationByImpUid(imp_uid);
        Certification certificationData = certification.getResponse();
        log.info("이름 : {}", certificationData.getName());
        log.info("성별 : {}", certificationData.getGender());
        log.info("핸드폼 번호 : {}", certificationData.getPhone());
        log.info("생년월일 : {}", certificationData.getBirth());

        return certification;
    }
}
