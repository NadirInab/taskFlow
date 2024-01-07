package com.example.flow.aop.handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiExceptionRes> handleIllegalArgException(IllegalArgumentException ex) {
        ApiExceptionRes apiResponse = new ApiExceptionRes(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleIllegalArgException(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleNotValidException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>() ;
        ex.getBindingResult().getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors) ;
    }

}
