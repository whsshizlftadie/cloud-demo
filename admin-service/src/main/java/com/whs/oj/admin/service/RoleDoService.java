package com.whs.oj.admin.service;

import com.whs.oj.admin.domain.RoleDo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.whs.oj.admin.domain.vo.RoleVo;

import java.util.List;

/**
* @author 86157
* @description 针对表【sys_role】的数据库操作Service
* @createDate 2023-07-22 13:27:37
*/
public interface RoleDoService extends IService<RoleDo> {
    List<RoleVo> select();

    void create(RoleVo roleVo);

    void update(RoleVo roleVo);

    void delete(List<Long> roleIds);
}
