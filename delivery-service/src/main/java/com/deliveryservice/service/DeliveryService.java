package com.deliveryservice.service;

import com.deliveryservice.model.Delivery;
import com.deliveryservice.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public List<Delivery> findAll() {
        return deliveryRepository.findAll();
    }

    public Optional<Delivery> findById(Long id) {
        return deliveryRepository.findById(id);
    }

    public Delivery save(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    public Optional<Delivery> update(Long id, Delivery updated) {
        return deliveryRepository.findById(id).map(existing -> {
            existing.setOrderId(updated.getOrderId());
            existing.setAddress(updated.getAddress());
            existing.setStatus(updated.getStatus());
            return deliveryRepository.save(existing);
        });
    }

    public boolean delete(Long id) {
        if (!deliveryRepository.existsById(id)) return false;
        deliveryRepository.deleteById(id);
        return true;
    }
}
