package com.whs.oj.admin.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.whs.oj.admin.constant.FiledConstant;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FillMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, FiledConstant.CREATE_TIME, Date.class, new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, FiledConstant.UPDATE_TIME, Date.class, new Date());
    }

}