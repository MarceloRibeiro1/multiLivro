package com.fcamara.multilivro.exception;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public abstract class DefaultAbstractException extends RuntimeException{
    private HttpStatus status = HttpStatus.BAD_REQUEST;
    private final Instant instant = Instant.now();
    private LogLevel logLevel = LogLevel.ERROR;

    public DefaultAbstractException() {
    }

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
