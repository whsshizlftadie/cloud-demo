package com.whs.oj.gateway.config;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nimbusds.jose.JWSObject;
import com.whs.oj.gateway.constant.SecurityConstants;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class SecurityGlobalFilter implements GlobalFilter, Ordered {


    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();

        // 不是正确的的JWT不做解析处理
        String token = request.getHeaders().getFirst(SecurityConstants.AUTHORIZATION_KEY);
        if (StrUtil.isBlank(token) || !StrUtil.startWithIgnoreCase(token, SecurityConstants.JWT_PREFIX)) {
            String login_token = UUID.randomUUID().toString();
            exchange.getRequest().mutate().header("login_token", login_token);
            redisTemplate.opsForValue().set(login_token, UUID.randomUUID().toString(), 25, TimeUnit.SECONDS);
            exchange = exchange.mutate().request(request).build();
            return chain.filter(exchange);
        }
        // 解析JWT获取jti，以jti为key判断redis的黑名单列表是否存在，存在则拦截访问
        token = StrUtil.replaceIgnoreCase(token, SecurityConstants.JWT_PREFIX, Strings.EMPTY);
        String payload = null;
        try {
            payload = StrUtil.toString(JWSObject.parse(token).getPayload());
            System.out.println(payload);
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }
        try {
            JSONObject jsonObject = JSON.parseObject(payload);
            String userName = jsonObject.getObject("user_name", String.class);
            String sst = "sst:" + userName + UUID.randomUUID().toString();
            System.out.println(userName);
            request = exchange.getRequest().mutate()
                    .header(SecurityConstants.JWT_PAYLOAD_KEY, URLEncoder.encode(payload, "UTF-8"))
                    .header(userName, sst)
                    .build();
            redisTemplate.opsForValue().set(sst, userName, 30, TimeUnit.SECONDS);
            System.out.println(sst);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }
        exchange = exchange.mutate().request(request).build();
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}