package com.whs.oj.auth.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.whs.oj.auth.api")
public class OpenFeignConfig {
}