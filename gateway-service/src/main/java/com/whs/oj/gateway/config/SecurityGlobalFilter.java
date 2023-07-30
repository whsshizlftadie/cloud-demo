package com.whs.oj.gateway.config;

import cn.hutool.core.util.StrUtil;
import com.nimbusds.jose.JWSObject;
import com.whs.oj.gateway.constant.SecurityConstants;
import org.apache.logging.log4j.util.Strings;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.List;

@Component
public class SecurityGlobalFilter implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // 不是正确的的JWT不做解析处理
        String token = request.getHeaders().getFirst(SecurityConstants.AUTHORIZATION_KEY);
        if (StrUtil.isBlank(token) || !StrUtil.startWithIgnoreCase(token, SecurityConstants.JWT_PREFIX)) {
            return chain.filter(exchange);
        }
        // 解析JWT获取jti，以jti为key判断redis的黑名单列表是否存在，存在则拦截访问
        token = StrUtil.replaceIgnoreCase(token, SecurityConstants.JWT_PREFIX, Strings.EMPTY);
        String payload = null;
        try {
            payload = StrUtil.toString(JWSObject.parse(token).getPayload());
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }
        try {
            request = exchange.getRequest().mutate()
                    .header(SecurityConstants.JWT_PAYLOAD_KEY, URLEncoder.encode(payload, "UTF-8"))
                    .build();
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