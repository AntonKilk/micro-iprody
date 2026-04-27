package com.stellar.crm.deliveryservice.repository;

import com.stellar.crm.deliveryservice.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
