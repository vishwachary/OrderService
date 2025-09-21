package com.sample.orders.orderservice.exception;

public class OrderNotValidException extends RuntimeException{
    public OrderNotValidException(String message) {
        super(message);
    }
}
