package com.fcamara.multilivro.exception;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public abstract class DefaultAbstractException extends RuntimeException{
    HttpStatus status = HttpStatus.BAD_REQUEST;
    Instant instant = Instant.now();
    LogLevel logLevel = LogLevel.ERROR;

    public DefaultAbstractException(String message) {
        super(message);
    }

    public DefaultAbstractException(String message, HttpStatus status, LogLevel logLevel) {
        super(message);
        this.status = status;
        this.logLevel = logLevel;
    }

    public DefaultAbstractException(HttpStatus status, LogLevel logLevel) {
        this.status = status;
        this.logLevel = logLevel;
    }

    public DefaultAbstractException(String message, Throwable cause, HttpStatus status, LogLevel logLevel) {
        super(message, cause);
        this.status = status;
        this.logLevel = logLevel;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Instant getInstant() {
        return instant;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }
}
