package com.sample.orders.orderservice.exception;

public class OrderNotCreatedException extends RuntimeException{
    public OrderNotCreatedException(String message) {
        super(message);
    }
}
