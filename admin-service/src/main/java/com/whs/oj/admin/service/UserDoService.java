package com.whs.oj.admin.service;

import com.whs.oj.admin.api.Pager;
import com.whs.oj.admin.api.dto.UserDTO;
import com.whs.oj.admin.domain.UserDo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.whs.oj.admin.domain.req.PageRequest;
import com.whs.oj.admin.domain.req.UserUpdateRequest;
import com.whs.oj.admin.domain.vo.UserVo;

import java.util.List;

/**
* @author 86157
* @description 针对表【sys_user】的数据库操作Service
* @createDate 2023-07-22 13:27:37
*/
public interface UserDoService extends IService<UserDo> {

    UserDTO loadUserByUsername(String username);

    UserDTO userInfo();

    List<UserVo> pageList(PageRequest req);

    void updateUserInfo(UserUpdateRequest req);

    void create(UserUpdateRequest req);

    void deletes(List<Long> userIds);
}
