package com.whs.oj.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whs.oj.admin.domain.RoleDo;
import com.whs.oj.admin.domain.RoleResourceDo;
import com.whs.oj.admin.domain.UserRoleDo;
import com.whs.oj.admin.domain.vo.RoleVo;
import com.whs.oj.admin.service.ResourceDoService;
import com.whs.oj.admin.service.RoleDoService;
import com.whs.oj.admin.mapper.RoleDoMapper;
import com.whs.oj.admin.service.RoleResourceDoService;
import com.whs.oj.admin.service.UserRoleDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 86157
* @description 针对表【sys_role】的数据库操作Service实现
* @createDate 2023-07-22 13:27:37
*/
@Service

public class RoleDoServiceImpl extends ServiceImpl<RoleDoMapper, RoleDo>
    implements RoleDoService{



    @Autowired
    private RoleResourceDoService roleResourceDoService;

    @Autowired
    private UserRoleDoService userRoleDoService;

    @Transactional
    @Override
    public List<RoleVo> select() {
        List<RoleDo> list = lambdaQuery().list();

        List<RoleVo> roleVoList = list.stream().map(role -> {
            RoleVo roleVo = new RoleVo();
            BeanUtil.copyProperties(role, roleVo);
            List<Long> rIds = roleResourceDoService.lambdaQuery().eq(RoleResourceDo::getRoleId, role.getId()).select(RoleResourceDo::getResourceId).list().stream().map(r -> {
                return r.getResourceId();
            }).collect(Collectors.toList());
            roleVo.setResourceIds(rIds);
            return roleVo;
        }).collect(Collectors.toList());



        return roleVoList;
    }

    @Transactional
    @Override
    public void create(RoleVo roleVo) {
        RoleDo roleDo = new RoleDo();
        BeanUtil.copyProperties(roleVo,roleDo);
        save(roleDo);
        if (CollectionUtils.isNotEmpty(roleVo.getResourceIds())) {
            ArrayList<RoleResourceDo> roleResourceDos = new ArrayList<>();

            for(Long Rid:roleVo.getResourceIds()){
                RoleResourceDo roleResourceDo = new RoleResourceDo();
                roleResourceDo.setResourceId(Rid);
                roleResourceDo.setRoleId(roleDo.getId());
                roleResourceDos.add(roleResourceDo);
            }

            roleResourceDoService.saveBatch(roleResourceDos);
        }
    }

    @Override
    public void update(RoleVo roleVo) {
        RoleDo roleDo = new RoleDo();
        BeanUtil.copyProperties(roleVo,roleDo);
        lambdaUpdate().eq(RoleDo::getId,roleDo.getId()).update(roleDo);
        roleResourceDoService.getBaseMapper()
                .delete(roleResourceDoService.lambdaQuery().eq(RoleResourceDo::getRoleId,roleDo.getId()).getWrapper());
        if(CollectionUtils.isNotEmpty(roleVo.getResourceIds())){
            ArrayList<RoleResourceDo> roleResourceDos = new ArrayList<>();
            for (Long resourceId : roleVo.getResourceIds()) {
                RoleResourceDo roleResourceDo = new RoleResourceDo();
                roleResourceDo.setRoleId(roleDo.getId()).setResourceId(resourceId);
                roleResourceDos.add(roleResourceDo);
            }
            roleResourceDoService.saveBatch(roleResourceDos);
        }
    }

    @Transactional
    @Override
    public void delete(List<Long> roleIds) {
        this.getBaseMapper().delete(lambdaQuery().in(RoleDo::getId,roleIds).getWrapper());

        roleResourceDoService.getBaseMapper()
                .delete(roleResourceDoService.lambdaQuery().in(RoleResourceDo::getRoleId,roleIds).getWrapper());

        userRoleDoService.getBaseMapper()
                .delete(userRoleDoService.lambdaQuery().in(UserRoleDo::getRoleId,roleIds).getWrapper());

    }
}




