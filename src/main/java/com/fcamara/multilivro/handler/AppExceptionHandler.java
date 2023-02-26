package com.fcamara.multilivro.handler;

import com.fcamara.multilivro.exception.DefaultAbstractException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(DefaultAbstractException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(DefaultAbstractException basicException){
        ExceptionResponse response = new ExceptionResponse(basicException);
        return new ResponseEntity<>(response, basicException.getStatus());
    }
}
