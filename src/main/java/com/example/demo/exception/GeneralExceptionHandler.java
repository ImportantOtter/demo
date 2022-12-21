package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFound(ProductNotFoundException ex, WebRequest request) {

        return new ResponseEntity<Object>(new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DiscountCardNotFoundException.class)
    public ResponseEntity<Object> handleDiscountCardNotFound(DiscountCardNotFoundException ex, WebRequest request) {

        return new ResponseEntity<Object>(new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }
}

