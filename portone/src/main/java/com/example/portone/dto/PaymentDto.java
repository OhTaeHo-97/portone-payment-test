package com.example.portone.dto;

import lombok.Getter;

public class PaymentDto {
    @Getter
    public static class PrePayment {
        private String merchant_uid;
        private int totalPrice;
    }
}
