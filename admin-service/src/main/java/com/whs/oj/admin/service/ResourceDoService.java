package com.whs.oj.admin.service;

import com.whs.oj.admin.domain.ResourceDo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.whs.oj.admin.domain.req.PageRequest;
import com.whs.oj.admin.domain.vo.ResourceVo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
* @author 86157
* @description 针对表【sys_resource】的数据库操作Service
* @createDate 2023-07-22 13:27:37
*/
public interface ResourceDoService extends IService<ResourceDo> {

    List<ResourceVo> select();

    List<ResourceVo> pageList(PageRequest req);

    void create(ResourceVo resourceVo);

    void update(ResourceVo resourceVo);

    void delete(List<Long> rIds);
}
