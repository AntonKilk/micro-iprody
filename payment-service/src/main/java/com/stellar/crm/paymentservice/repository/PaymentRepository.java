package com.stellar.crm.paymentservice.repository;

import com.stellar.crm.paymentservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
