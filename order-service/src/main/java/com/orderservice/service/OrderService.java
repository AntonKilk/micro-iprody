package com.orderservice.service;

import com.orderservice.dto.request.PaymentRequest;
import com.orderservice.integration.payment.PaymentClient;
import com.orderservice.model.Order;
import com.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final PaymentClient paymentClient;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public Order save(Order order) {
        Order saved = orderRepository.save(order);
        PaymentRequest req = new PaymentRequest(saved.getId(), saved.getCustomerId(), saved.getTotalAmount());
        paymentClient.createPaymentRequest(req);
        return saved;
    }

    public Optional<Order> update(Long id, Order updated) {
        return orderRepository.findById(id).map(existing -> {
            existing.setCustomerId(updated.getCustomerId());
            existing.setStatus(updated.getStatus());
            existing.setTotalAmount(updated.getTotalAmount());
            return orderRepository.save(existing);
        });
    }

    public boolean delete(Long id) {
        if (!orderRepository.existsById(id)) return false;
        orderRepository.deleteById(id);
        return true;
    }
}
