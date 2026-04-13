package com.stellar.crm.orderservice.repository;

import com.stellar.crm.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
