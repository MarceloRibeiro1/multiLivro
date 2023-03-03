package com.fcamara.multilivro.rent.exception;

import com.fcamara.multilivro.exception.DefaultAbstractException;
import com.fcamara.multilivro.exception.LogLevel;
import org.springframework.http.HttpStatus;

public class DuplicateRentEntryException extends DefaultAbstractException {
    public DuplicateRentEntryException(String message, HttpStatus status, LogLevel logLevel) {
        super(message, status, logLevel);
    }
}
