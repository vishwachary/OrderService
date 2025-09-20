package com.sample.orders.orderservice.repository;

import com.sample.orders.orderservice.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItem ,Long> {
}
