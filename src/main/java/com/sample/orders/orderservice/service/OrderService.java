package com.sample.orders.orderservice.service;

import com.sample.orders.orderservice.client.PaymentClient;
import com.sample.orders.orderservice.dto.PaymentRequest;
import com.sample.orders.orderservice.dto.PaymentResponse;
import com.sample.orders.orderservice.entity.Orders;
import com.sample.orders.orderservice.exception.OrderNotCreatedException;
import com.sample.orders.orderservice.exception.OrderNotValidException;
import com.sample.orders.orderservice.repository.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private PaymentClient paymentClient;
    public OrderService(OrderRepository orderRepository , PaymentClient paymentClient) {
        this.orderRepository = orderRepository;
        this.paymentClient = paymentClient;

    }

    public PaymentResponse placeOrder(Orders orderRequest) {

      validateOrder(orderRequest);
        Orders savedOrder = orderRepository.save(orderRequest);
        if (savedOrder == null || savedOrder.getOrderId() == null) {
            throw new OrderNotCreatedException("Failed to create order in database.");
        }

        // 3️⃣ Call PaymentService fiegn client
        PaymentResponse paymentResponse = paymentClient.processPayment(
                new PaymentRequest(savedOrder.getOrderId(), savedOrder.getOrderTotal())
        );

        return paymentResponse;
    }

    private void validateOrder(Orders order) {
        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new OrderNotValidException("Order must have at least one item.");
        }
        if (order.getOrderTotal() == null || order.getOrderTotal().compareTo(BigDecimal.ZERO) <= 0) {
            throw new OrderNotValidException("Order total must be greater than zero.");
        }

    }



}