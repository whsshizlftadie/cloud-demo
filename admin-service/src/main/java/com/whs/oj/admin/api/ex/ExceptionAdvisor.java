package com.whs.oj.admin.api.ex;

import com.whs.oj.admin.api.RestResult;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;


@ControllerAdvice
public class ExceptionAdvisor {

    //private final Logger log= (Logger) LoggerFactory.getLogger(ExceptionAdvisor.class);

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RestResult<?> validExceptionHandler(MethodArgumentNotValidException e) {
        //log.error(e.getMessage(), e);
        //log.log(Level.FINE, e.getMessage(), e);
        System.out.println(String.format("错误拦截：->{%s}",e.getMessage()));

        return RestResult.failure(HttpStatus.BAD_REQUEST.value(), Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

}