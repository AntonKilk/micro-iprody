package com.paymentservice.service;

import com.paymentservice.model.Payment;
import com.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> findById(Long id) {
        return paymentRepository.findById(id);
    }

    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Optional<Payment> update(Long id, Payment updated) {
        return paymentRepository.findById(id).map(existing -> {
            existing.setOrderId(updated.getOrderId());
            existing.setAmount(updated.getAmount());
            existing.setStatus(updated.getStatus());
            return paymentRepository.save(existing);
        });
    }

    public boolean delete(Long id) {
        if (!paymentRepository.existsById(id)) return false;
        paymentRepository.deleteById(id);
        return true;
    }
}
