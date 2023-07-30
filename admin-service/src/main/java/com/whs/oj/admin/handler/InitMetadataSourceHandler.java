package com.whs.oj.admin.handler;

import com.whs.oj.admin.api.Metadata;
import com.whs.oj.admin.constant.RedisConstant;
import com.whs.oj.admin.mapper.ResourceDoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@Component
public class InitMetadataSourceHandler {

    @Autowired
    private ResourceDoMapper resourceMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 全局{URL->ROLE[]}的TreeMap元数据缓存
    private TreeMap<String, List<String>> resourceRolesMap = new TreeMap<>();

    @PostConstruct
    public void initMetadataCache() {
        List<Metadata> list = resourceMapper.findSecurityMetadata();
        for (Metadata item : list) {
            String resourceUrl = item.getResourceUrl();
            List<String> roleList = resourceRolesMap.computeIfAbsent(resourceUrl, k -> new ArrayList<>());
            roleList.add(item.getRoleCode());
        }
        redisTemplate.opsForHash().putAll(RedisConstant.RESOURCE_ROLES_MAP, resourceRolesMap);
    }

}