package com.whs.oj.gateway.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whs.oj.gateway.authorization.AuthorizationManager;
import com.whs.oj.gateway.constant.AuthConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfig {

    private final AuthorizationManager authorizationManager;


    public ResourceServerConfig(AuthorizationManager authorizationManager) {
        this.authorizationManager = authorizationManager;
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

        http.oauth2ResourceServer().jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter());
        http.authorizeExchange()
                .pathMatchers("/actuator/**").permitAll()
                .anyExchange().access(authorizationManager)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(
                        (exchange, ex) -> {
                            // Create a custom response with data and code
                            ServerHttpResponse response = exchange.getResponse();
                            response.setStatusCode(HttpStatus.UNAUTHORIZED);
                            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

                            Map<String, Object> responseBody = new HashMap<>();
                            responseBody.put("code", HttpStatus.UNAUTHORIZED.value());
                            responseBody.put("message", "Token过期或失效");
                            responseBody.put("data", null); // You can set data as needed

                            ObjectMapper objectMapper = new ObjectMapper();
                            String responseBodyString = null;
                            try {
                                responseBodyString = objectMapper.writeValueAsString(responseBody);
                            } catch (JsonProcessingException e) {
                                e.printStackTrace();
                            }

                            return response.writeWith(Mono.just(response.bufferFactory().wrap(responseBodyString.getBytes())));
                        }
                )
                .accessDeniedHandler(
                        (exchange, ex) -> {
                            // Create a custom response with data and code
                            ServerHttpResponse response = exchange.getResponse();
                            response.setStatusCode(HttpStatus.FORBIDDEN);
                            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

                            Map<String, Object> responseBody = new HashMap<>();
                            responseBody.put("code", HttpStatus.FORBIDDEN.value());
                            responseBody.put("message", "认证失败");
                            responseBody.put("data", null); // You can set data as needed

                            ObjectMapper objectMapper = new ObjectMapper();
                            String responseBodyString = null;
                            try {
                                responseBodyString = objectMapper.writeValueAsString(responseBody);
                            } catch (JsonProcessingException e) {
                                e.printStackTrace();
                            }

                            return response.writeWith(Mono.just(response.bufferFactory().wrap(responseBodyString.getBytes())));
                        }
                )
                .and()
                .cors()
                .and()
                .csrf()
                .disable();
        return http.build();
    }

    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(AuthConstant.AUTHORITY_PREFIX);
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(AuthConstant.AUTHORITY_CLAIM_NAME);
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.addAllowedHeader("*"); // 允许所有请求头
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}