package com.example.portone.dto;

import lombok.Getter;

public class PaymentDto {
    @Getter
    public static class PrePayment {
        private String merchant_uid;
        private int totalPrice;
    }

    @Getter
    public static class PostPayment {
        private String merchant_uid;
        private String imp_uid;
    }
}
