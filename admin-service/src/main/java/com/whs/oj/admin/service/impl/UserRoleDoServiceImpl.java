package com.whs.oj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whs.oj.admin.api.dto.UserDTO;
import com.whs.oj.admin.domain.UserDo;
import com.whs.oj.admin.domain.UserRoleDo;
import com.whs.oj.admin.mapper.RoleDoMapper;
import com.whs.oj.admin.service.UserRoleDoService;
import com.whs.oj.admin.mapper.UserRoleDoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 86157
* @description 针对表【sys_user_role】的数据库操作Service实现
* @createDate 2023-07-22 13:27:37
*/
@Service
public class UserRoleDoServiceImpl extends ServiceImpl<UserRoleDoMapper, UserRoleDo>
    implements UserRoleDoService{



}




