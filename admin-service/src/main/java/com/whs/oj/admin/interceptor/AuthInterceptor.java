package com.whs.oj.admin.interceptor;

import com.whs.oj.admin.api.ex.AuthKeyException;
import com.whs.oj.admin.utils.UserContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String login_is = request.getHeader("login_token");
        if (!StringUtils.isEmpty(login_is)) {
            Object o = redisTemplate.opsForValue().get(login_is);
            if (!Objects.isNull(o)) {
//                redisTemplate.delete(login_is);
                return true;
            } else {
                return false;
            }
        }

        String userId = UserContext.getCurrentUserId();
        String sst = null;
        try {
            sst = request.getHeader(userId);
        } catch (Exception e) {
            throw new AuthKeyException("请求头缺少或异常");
        }
        if (StringUtils.isEmpty(sst)) {
            throw new AuthKeyException("请求头缺少或异常");
        }

        String sst_value = (String) redisTemplate.opsForValue().get(sst);

        if (StringUtils.isNotEmpty(sst_value)) {
            redisTemplate.delete(sst);
            if (sst_value.equals(userId)) {
                return true;
            } else {
                throw new AuthKeyException("key不正确");
            }
        } else {
            redisTemplate.delete(sst);
            throw new AuthKeyException("key为空请以正确的方式访问");
        }

    }
}
