package com.example.flow.aop.handler;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
@Data
public class ApiExceptionRes {
    private HttpStatus status ;
    private String message ;
    private final ZonedDateTime time ;


    public ApiExceptionRes(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        time = ZonedDateTime.now() ;
    }
}
