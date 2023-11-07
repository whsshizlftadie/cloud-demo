package com.whs.oj.gateway.handler;

import com.whs.oj.comon.RestResult;
import com.whs.oj.gateway.handler.self_ex.CustomSecurityException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomSecurityException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public RestResult handleCustomSecurityException(CustomSecurityException ex) {
        return RestResult.failure(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public RestResult handleIllegalArgumentException(IllegalArgumentException ex){
        return RestResult.failure(501,ex.getMessage());
    }

}