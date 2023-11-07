package com.whs.oj.auth.handler;

import com.whs.oj.comon.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public RestResult handlerException(Exception ex){
        return RestResult.failure(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public RestResult handleAccessDeniedException(AccessDeniedException ex){
        return RestResult.failure(HttpStatus.UNAUTHORIZED.value(),"LOJ:"+ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public RestResult RuntimeExceptionHandler(RuntimeException ex){
        return RestResult.failure(501,ex.getMessage());
    }
}
