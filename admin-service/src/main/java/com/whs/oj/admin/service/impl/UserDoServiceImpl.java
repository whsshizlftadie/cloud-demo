package com.whs.oj.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whs.oj.admin.api.Pager;
import com.whs.oj.admin.api.dto.UserDTO;
import com.whs.oj.admin.domain.UserDo;
import com.whs.oj.admin.domain.UserRoleDo;
import com.whs.oj.admin.domain.req.PageRequest;
import com.whs.oj.admin.domain.req.UserUpdateRequest;
import com.whs.oj.admin.domain.vo.UserVo;
import com.whs.oj.admin.mapper.RoleDoMapper;
import com.whs.oj.admin.mapper.UserRoleDoMapper;
import com.whs.oj.admin.service.UserDoService;
import com.whs.oj.admin.mapper.UserDoMapper;
import com.whs.oj.admin.service.UserRoleDoService;
import com.whs.oj.admin.utils.UserContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 86157
* @description 针对表【sys_user】的数据库操作Service实现
* @createDate 2023-07-22 13:27:37
*/
@Service
public class UserDoServiceImpl extends ServiceImpl<UserDoMapper, UserDo>
    implements UserDoService{

    @Autowired
    private RoleDoMapper roleMapper;

    @Autowired
    private UserRoleDoMapper userRoleDoMapper;

    @Autowired
    private UserRoleDoService userRoleDoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserDTO loadUserByUsername(String username) {
        UserDo user = lambdaQuery().eq(UserDo::getUsername, username).eq(UserDo::getStatus, 1).one();
        if (user == null) {
            return null;
        }
        List<String> roles = roleMapper.findRolesByUserId(user.getId());
        UserDTO userDTO = new UserDTO(user);
        userDTO.setRoles(roles);
        return userDTO;
    }

    @Override
    public UserDTO userInfo() {
        String userName = UserContext.getCurrentUserId();
        UserDo user = lambdaQuery().eq(UserDo::getUsername, userName).select().one();
        UserDTO userDTO = new UserDTO();
        BeanUtil.copyProperties(user,userDTO);
        return userDTO;
    }

    @Transactional
    @Override
    public List<UserVo> pageList(PageRequest req) {

        Page<UserDo> userVoPage = new Page<>();
        userVoPage.setCurrent(req.getPageNo());
        userVoPage.setSize(req.getPageSize());

        LambdaQueryChainWrapper<UserDo> queryChainWrapper=lambdaQuery();

        if(StringUtils.isNotEmpty(req.getKeyword())){
            queryChainWrapper.like(UserDo::getUsername,req.getKeyword());
        }
        baseMapper.selectPage(userVoPage,queryChainWrapper.getWrapper());
        List<UserVo> userVoList = userVoPage.getRecords().stream().
                map(i -> {
                    UserVo userVo = new UserVo();
                    BeanUtil.copyProperties(i, userVo);
                    return userVo;
                }).collect(Collectors.toList());

        LambdaQueryWrapper<UserRoleDo> userRoleDoLambdaQueryWrapper=new LambdaQueryWrapper<>();
        for (UserVo userVo:userVoList){
            userRoleDoLambdaQueryWrapper.eq(UserRoleDo::getUserId,userVo.getId()).select(UserRoleDo::getRoleId);

            List<Long> roleIds = userRoleDoMapper.selectList(userRoleDoLambdaQueryWrapper).stream().map(i -> {
                return i.getRoleId();
            }).collect(Collectors.toList());

            userVo.setRoleIds(roleIds);
            userRoleDoLambdaQueryWrapper.clear();
        }
        return userVoList;
    }

    @Transactional
    @Override
    public void updateUserInfo(UserUpdateRequest req) {
        UserDo userDo = new UserDo();
        BeanUtil.copyProperties(req,userDo);
        lambdaUpdate().eq(UserDo::getId,req.getId()).update(userDo);
        userRoleDoService
                .getBaseMapper()
                .delete(userRoleDoService.lambdaQuery().eq(UserRoleDo::getUserId,req.getId()).getWrapper());
        if(CollectionUtil.isNotEmpty(req.getRoleIds())){
            ArrayList<UserRoleDo> userRoleDos = new ArrayList<>();
            for(Long roleId:req.getRoleIds()){
                UserRoleDo userRoleDo = new UserRoleDo();
                userRoleDo.setRoleId(roleId);
                userRoleDo.setUserId(req.getId());
                userRoleDos.add(userRoleDo);
            }
            userRoleDoService.saveBatch(userRoleDos);
        }

    }

    @Override
    @Transactional
    public void create(UserUpdateRequest req) {
        UserDo userDo = new UserDo();
        BeanUtil.copyProperties(req,userDo);
        userDo.setPassword(passwordEncoder.encode("123456"));
        save(userDo);

        if(CollectionUtil.isNotEmpty(req.getRoleIds())){
            ArrayList<UserRoleDo> userRoleDos = new ArrayList<>();
            for(Long roleId:req.getRoleIds()){
                UserRoleDo userRoleDo = new UserRoleDo();
                userRoleDo.setRoleId(roleId);
                userRoleDo.setUserId(userDo.getId());
                userRoleDos.add(userRoleDo);
            }
            userRoleDoService.saveBatch(userRoleDos);
        }

    }

    @Override
    @Transactional
    public void deletes(List<Long> userIds) {
        this.getBaseMapper().delete(lambdaQuery().in(UserDo::getId,userIds).getWrapper());

        userRoleDoService
                .getBaseMapper().delete(userRoleDoService.lambdaQuery().in(UserRoleDo::getUserId,userIds).getWrapper());
    }

}




