package com.fcamara.multilivro.handler;

public class CustomException extends RuntimeException {
    private String message;
    public CustomException(String message) {
        super(message);
    }
}
