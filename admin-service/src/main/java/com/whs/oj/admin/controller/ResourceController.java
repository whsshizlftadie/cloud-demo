package com.whs.oj.admin.controller;

import com.sun.corba.se.impl.encoding.IDLJavaSerializationInputStream;
import com.whs.oj.admin.api.Pager;
import com.whs.oj.admin.api.RestResult;
import com.whs.oj.admin.domain.req.PageRequest;
import com.whs.oj.admin.domain.vo.ResourceVo;
import com.whs.oj.admin.service.ResourceDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceDoService resourceDoService;

    @GetMapping("/select")
    public RestResult<List<ResourceVo>> select(){
        List<ResourceVo> select = resourceDoService.select();
        return RestResult.success(select);
    }

    @PostMapping("/listPage")
    public RestResult<List<ResourceVo>> listPage(@RequestBody PageRequest req){
        List<ResourceVo> resourceVos = resourceDoService.pageList(req);

        Pager<ResourceVo> resourceVoPager = new Pager<>();
        resourceVoPager.setTotal(resourceVos.size());
        resourceVoPager.setRecords(resourceVos);

        return RestResult.page(resourceVoPager);
    }

    @PostMapping
    public RestResult create(@RequestBody ResourceVo resourceVo){

        resourceDoService.create(resourceVo);

        return RestResult.success();
    }

    @PutMapping
    public RestResult update(@RequestBody ResourceVo resourceVo){

        resourceDoService.update(resourceVo);

        return RestResult.success();
    }

    @DeleteMapping("/{Ids}")
    public RestResult delete(@PathVariable List<Long> Ids){
        resourceDoService.delete(Ids);
        return  RestResult.success();
    }

}
