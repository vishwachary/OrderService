package com.sample.orders.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request)
    {
        LocalDate timestamp = LocalDate.now();
        ExceptionResponse exceptionReceived=ExceptionResponse.builder()
                .timestamp(timestamp)
                .message(ex.getLocalizedMessage())
                .details(ex.getMessage()).build();
        return new ResponseEntity<>(exceptionReceived, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(OrderNotValidException.class)
    ResponseEntity<ExceptionResponse> handleOrderNotFoundException(OrderNotValidException ex, WebRequest request)
    {
        LocalDate timestamp = LocalDate.now();
        ExceptionResponse exceptionReceived=ExceptionResponse.builder()
                .timestamp(timestamp)
                .message(ex.getLocalizedMessage())
                .details(ex.getMessage()).build();
        return new ResponseEntity<>(exceptionReceived, HttpStatus.NOT_FOUND);
    }
}
