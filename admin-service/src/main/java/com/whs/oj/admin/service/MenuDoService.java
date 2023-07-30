package com.whs.oj.admin.service;

import com.whs.oj.admin.domain.MenuDo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 86157
* @description 针对表【sys_menu】的数据库操作Service
* @createDate 2023-07-22 13:27:37
*/
public interface MenuDoService extends IService<MenuDo> {

    List<MenuDo> findMenusByUserId(String userId);


}
