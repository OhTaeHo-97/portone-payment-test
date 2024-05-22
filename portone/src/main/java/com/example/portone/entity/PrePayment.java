package com.example.portone.entity;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class PrePayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prePaymentId;
    @NotNull
    private String merchantUid;
    private BigDecimal amount = BigDecimal.ZERO;

    public PrePayment(String merchantUid, BigDecimal amount) {
        this.merchantUid = merchantUid;
        this.amount = amount;
    }
}
