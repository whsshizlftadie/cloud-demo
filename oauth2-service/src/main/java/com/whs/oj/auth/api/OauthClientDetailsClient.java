package com.whs.oj.auth.api;

import com.whs.oj.auth.service.client_details.OauthClientDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "upms-service" ,contextId = "oauth-client")
public interface OauthClientDetailsClient {

    @GetMapping(value = "/admin/client")
    OauthClientDetails clientDetails(@RequestParam("ClientId") String ClientId);
}
