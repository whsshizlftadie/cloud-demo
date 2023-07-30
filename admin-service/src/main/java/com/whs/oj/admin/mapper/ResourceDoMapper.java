package com.whs.oj.admin.mapper;

import com.whs.oj.admin.api.Metadata;
import com.whs.oj.admin.domain.ResourceDo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 86157
* @description 针对表【sys_resource】的数据库操作Mapper
* @createDate 2023-07-22 13:27:37
* @Entity com.whs.oj.admin.domain.ResourceDo
*/
public interface ResourceDoMapper extends BaseMapper<ResourceDo> {

    List<Metadata> findSecurityMetadata();

}




