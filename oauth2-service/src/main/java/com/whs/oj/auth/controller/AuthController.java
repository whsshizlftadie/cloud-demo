package com.whs.oj.auth.controller;


import com.whs.oj.comon.RestResult;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.Principal;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/oauth")
public class AuthController {

    private final TokenEndpoint tokenEndpoint;

    public AuthController(TokenEndpoint tokenEndpoint) {
        this.tokenEndpoint = tokenEndpoint;
    }

    @PostMapping("/token")
    public Object postAccessToken(
            Principal principal,
            @RequestParam Map<String,String> parameters
    )throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken accessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        if (ObjectUtils.isNotEmpty(accessToken.getValue())){
            System.out.println(parameters.get("username")+":"+accessToken.getValue());
        }
        return RestResult.success(accessToken);
    }
}
