package com.whs.oj.admin.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.whs.oj.admin.constant.SecurityConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import java.io.UnsupportedEncodingException;
import java.util.Objects;
import java.net.URLDecoder;

public class UserContext {

    public static String getCurrentUserId() {
        String token =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getRequest().getHeader(SecurityConstants.JWT_PAYLOAD_KEY);
        if (StringUtils.isEmpty(token)) {
            return null;
        }

        try {

            JSONObject jsonObject = JSON.parseObject(URLDecoder.decode(token, "UTF-8"));
            if (Objects.isNull(jsonObject)) {
                return null;
            }

            String userName = jsonObject.getObject("user_name", String.class);

            return userName;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;

    }
}