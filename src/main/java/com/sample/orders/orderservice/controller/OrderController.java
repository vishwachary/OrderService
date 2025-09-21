package com.sample.orders.orderservice.controller;

import com.sample.orders.orderservice.dto.PaymentRequest;
import com.sample.orders.orderservice.dto.PaymentResponse;
import com.sample.orders.orderservice.entity.Orders;
import com.sample.orders.orderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/orders")
public class OrderController {

    private OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody Orders orderRequest) {
        PaymentResponse paymentResponse = orderService.placeOrder(orderRequest);
        return ResponseEntity.ok("✅ Order placed and payment " + paymentResponse.getStatus());
    }
}
