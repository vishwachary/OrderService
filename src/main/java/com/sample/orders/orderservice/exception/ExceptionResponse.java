package com.sample.orders.orderservice.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class ExceptionResponse {

       private LocalDate timestamp;
      private String message;
      private String details;
}
