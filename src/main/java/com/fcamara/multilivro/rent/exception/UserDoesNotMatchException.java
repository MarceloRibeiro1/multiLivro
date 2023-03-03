package com.fcamara.multilivro.rent.exception;

import com.fcamara.multilivro.exception.LogLevel;
import org.springframework.http.HttpStatus;

public class UserDoesNotMatchException extends RentValidationException {
    public UserDoesNotMatchException(String message, HttpStatus status, LogLevel logLevel) {
        super(message, status, logLevel);
    }
}
