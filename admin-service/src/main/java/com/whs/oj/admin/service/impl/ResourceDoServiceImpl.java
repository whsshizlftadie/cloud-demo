package com.whs.oj.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whs.oj.admin.domain.ResourceDo;
import com.whs.oj.admin.domain.RoleResourceDo;
import com.whs.oj.admin.domain.req.PageRequest;
import com.whs.oj.admin.domain.vo.ResourceVo;
import com.whs.oj.admin.service.ResourceDoService;
import com.whs.oj.admin.mapper.ResourceDoMapper;
import com.whs.oj.admin.service.RoleResourceDoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author 86157
* @description 针对表【sys_resource】的数据库操作Service实现
* @createDate 2023-07-22 13:27:37
*/
@Service
public class ResourceDoServiceImpl extends ServiceImpl<ResourceDoMapper, ResourceDo>
    implements ResourceDoService{

    @Autowired
    private RoleResourceDoService roleResourceDoService;

    @Override
    public List<ResourceVo> select() {
        List<ResourceDo> resourceDoList = list();

        List<ResourceVo> collect = resourceDoList.stream().map(r -> {
            ResourceVo resourceVo = new ResourceVo();
            BeanUtil.copyProperties(r, resourceVo);
            return resourceVo;
        }).collect(Collectors.toList());

        return collect;
    }

    @Override
    public List<ResourceVo> pageList(PageRequest req) {
        Page<ResourceDo> page = new Page<>();
        page.setSize(req.getPageSize());
        page.setCurrent(req.getPageNo());

        LambdaQueryChainWrapper<ResourceDo> resourceDoLambdaQueryChainWrapper = lambdaQuery();

        if(StringUtils.isNotEmpty(req.getKeyword())){
            resourceDoLambdaQueryChainWrapper.like(ResourceDo::getUrl,req.getKeyword());
        }

        baseMapper.selectPage(page, resourceDoLambdaQueryChainWrapper.getWrapper());

        List<ResourceVo> resourceVoList = page.getRecords().stream().map(r -> {
            ResourceVo resourceVo = new ResourceVo();
            BeanUtil.copyProperties(r, resourceVo);
            return resourceVo;
        }).collect(Collectors.toList());



        return resourceVoList;
    }

    @Override
    public void create(ResourceVo resourceVo) {
        ResourceDo resourceDo = new ResourceDo();
        BeanUtil.copyProperties(resourceVo,resourceDo);
        save(resourceDo);
    }

    @Override
    public void update(ResourceVo resourceVo) {
        ResourceDo resourceDo = new ResourceDo();
        BeanUtil.copyProperties(resourceVo,resourceDo);
        updateById(resourceDo);
    }

    @Transactional
    @Override
    public void delete(List<Long> rIds) {
        this.getBaseMapper().delete(lambdaQuery().in(ResourceDo::getId,rIds).getWrapper());

        roleResourceDoService
                .getBaseMapper()
                .delete(roleResourceDoService.lambdaQuery().in(RoleResourceDo::getResourceId,rIds).getWrapper());
    }
}




