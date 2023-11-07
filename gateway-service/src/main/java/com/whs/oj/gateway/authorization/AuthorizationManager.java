package com.whs.oj.gateway.authorization;

import com.whs.oj.gateway.constant.AuthConstant;
import com.whs.oj.gateway.constant.RedisConstant;
import com.whs.oj.gateway.utils.ConvertUtils;
import com.whs.oj.gateway.utils.UrlPatternUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@ConfigurationProperties(prefix = "security")
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private List<String> ignoreUrls;

    public List<String> getIgnoreUrls() {
        return ignoreUrls;
    }

    public AuthorizationManager setIgnoreUrls(List<String> ignoreUrls) {
        this.ignoreUrls = ignoreUrls;
        return this;
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        URI uri = authorizationContext.getExchange().getRequest().getURI();


        if (skipValid(uri.getPath())) {
            return Mono.just(new AuthorizationDecision(true));
        }
        // 远程获取
        Map<String, Object> urlPermRolesRules = redisTemplate.opsForHash().entries(RedisConstant.RESOURCE_ROLES_MAP);
        List<String> authoritiesFromRedis = new ArrayList<>();

        for (Map.Entry<String, Object> permRole : urlPermRolesRules.entrySet()) {
            String perm = permRole.getKey();

            if (UrlPatternUtils.match(perm, uri.getPath())) {
                System.out.println("<" + perm + ">" + ":" + uri.getPath());
                List<String> roles = ConvertUtils.castList(permRole.getValue(), String.class);
                if (authoritiesFromRedis.containsAll(roles)) {
                    continue;
                }
                authoritiesFromRedis.addAll(roles);
            }
        }

        if (authoritiesFromRedis.size() > 0) {
            System.out.println(authoritiesFromRedis);
        }

        authoritiesFromRedis = authoritiesFromRedis.stream().map(i -> i = AuthConstant.AUTHORITY_PREFIX + i).collect(Collectors.toList());
        return mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authoritiesFromRedis::contains)
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
    }

    /**
     * 跳过校验
     *
     * @param path
     * @return
     */
    private boolean skipValid(String path) {
        for (String skipPath : ignoreUrls) {
            if (UrlPatternUtils.match(skipPath, path)) {
                return true;
            }
        }
        return false;
    }

}