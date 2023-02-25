package com.fcamara.multilivro.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler({CustomException.class})
    public ResponseEntity<CustomException> handleCustomException(CustomException customException){
        return new ResponseEntity<>(customException, HttpStatus.BAD_REQUEST);
    }
}
