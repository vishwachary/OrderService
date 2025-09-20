package com.sample.orders.orderservice.repository;

import com.sample.orders.orderservice.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders , Long> {
}
