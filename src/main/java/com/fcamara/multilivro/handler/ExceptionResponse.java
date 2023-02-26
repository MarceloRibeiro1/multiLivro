package com.fcamara.multilivro.handler;

import com.fcamara.multilivro.exception.DefaultAbstractException;
import com.fcamara.multilivro.exception.LogLevel;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Getter
public class ExceptionResponse implements Serializable {
    HttpStatus status;
    String date;
    LogLevel logLevel;
    String message;

    public ExceptionResponse(DefaultAbstractException ex) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        this.status = ex.getStatus();
        this.logLevel = ex.getLogLevel();
        this.date = ex.getInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(formatter);
        this.message = ex.getMessage();
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
