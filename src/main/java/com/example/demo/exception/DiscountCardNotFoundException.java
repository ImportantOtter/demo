package com.example.demo.exception;

public class DiscountCardNotFoundException extends RuntimeException {

    public DiscountCardNotFoundException(String message) {
        super(message);
    }
}
