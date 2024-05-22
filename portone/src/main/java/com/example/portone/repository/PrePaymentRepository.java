package com.example.portone.repository;

import com.example.portone.entity.PrePayment;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrePaymentRepository extends JpaRepository<PrePayment, Long> {
    Optional<PrePayment> findByMerchantUid(String merchantUid);
}
