package com.sample.orders.orderservice.client;

import com.sample.orders.orderservice.dto.PaymentRequest;
import com.sample.orders.orderservice.dto.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service", url = "http://localhost:8084")
public interface PaymentClient {
    @PostMapping("/api/v1/payments/process")
    PaymentResponse processPayment(@RequestBody PaymentRequest request);
}

