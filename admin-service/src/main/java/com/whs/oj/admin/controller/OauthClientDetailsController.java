package com.whs.oj.admin.controller;

import com.whs.oj.admin.domain.OauthClientDetails;
import com.whs.oj.admin.service.OauthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class OauthClientDetailsController {

    @Autowired
    private OauthClientDetailsService oauthClientDetailsService;

    @GetMapping("/client")
    public OauthClientDetails clientDetails(@RequestParam String ClientId){
        OauthClientDetails clientDetails = oauthClientDetailsService.getById(ClientId);
        return clientDetails;
    }

}
