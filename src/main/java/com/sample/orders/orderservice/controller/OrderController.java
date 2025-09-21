package com.sample.orders.orderservice.controller;

import com.sample.orders.orderservice.dto.PaymentRequest;
import com.sample.orders.orderservice.dto.PaymentResponse;
import com.sample.orders.orderservice.entity.Orders;
import com.sample.orders.orderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shop")
public class OrderController {

    private OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public ResponseEntity<String> placeOrder(@RequestBody Orders orderRequest) {
        PaymentResponse paymentResponse = orderService.placeOrder(orderRequest);
        return ResponseEntity.ok("✅ Order placed and payment " + paymentResponse.getStatus());
    }
}
