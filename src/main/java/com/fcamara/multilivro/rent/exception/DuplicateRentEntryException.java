package com.fcamara.multilivro.rent.exception;

import com.fcamara.multilivro.exception.LogLevel;
import org.springframework.http.HttpStatus;

import java.time.Instant;

public class DuplicateRentEntryException extends RentValidationException {
    private HttpStatus status;
    private final Instant instant = Instant.now();
    private LogLevel logLevel;
    private String message;

    public DuplicateRentEntryException(String message, HttpStatus status, LogLevel logLevel) {
        super(message, status, logLevel);
    }
}
