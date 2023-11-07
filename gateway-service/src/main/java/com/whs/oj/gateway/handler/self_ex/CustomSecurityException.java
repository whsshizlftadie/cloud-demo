package com.whs.oj.gateway.handler.self_ex;

public class CustomSecurityException extends RuntimeException {
    public CustomSecurityException(String message) {
        super(message);
    }
}
