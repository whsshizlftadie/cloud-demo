package com.whs.oj.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whs.oj.admin.domain.MenuDo;
import com.whs.oj.admin.service.MenuDoService;
import com.whs.oj.admin.mapper.MenuDoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 86157
* @description 针对表【sys_menu】的数据库操作Service实现
* @createDate 2023-07-22 13:27:37
*/
@Service
public class MenuDoServiceImpl extends ServiceImpl<MenuDoMapper, MenuDo>
    implements MenuDoService{

    @Autowired
    private MenuDoMapper menuMapper;

    @Override
    public List<MenuDo> findMenusByUserId(String userId) {
        return menuMapper.findMenusByUserId(userId);
    }

}




