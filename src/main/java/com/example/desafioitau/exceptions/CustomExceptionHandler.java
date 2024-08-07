package com.example.desafioitau.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UnprocessableEntityException.class)
    private ResponseEntity<Void> handleUnprocessableEntityException(UnprocessableEntityException exception){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
    }

    @ExceptionHandler(InvalidRequestException.class)
    private ResponseEntity<Void> handleBadRequestException(InvalidRequestException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
