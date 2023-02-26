package com.fcamara.multilivro.rent.exception;

import com.fcamara.multilivro.exception.DefaultAbstractException;
import com.fcamara.multilivro.exception.LogLevel;
import org.springframework.http.HttpStatus;

public class RentValidationException extends DefaultAbstractException {
    public RentValidationException() {
    }

    public RentValidationException(String message) {
        super(message);
    }

    public RentValidationException(String message, HttpStatus status, LogLevel logLevel) {
        super(message, status, logLevel);
    }

    public RentValidationException(HttpStatus status, LogLevel logLevel) {
        super(status, logLevel);
    }

    public RentValidationException(String message, Throwable cause, HttpStatus status, LogLevel logLevel) {
        super(message, cause, status, logLevel);
    }
}
