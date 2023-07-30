package com.whs.oj.admin.controller;

import com.whs.oj.admin.api.RestResult;
import com.whs.oj.admin.domain.vo.RoleVo;
import com.whs.oj.admin.service.RoleDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleDoService roleDoService;

    @GetMapping("/select")
    public RestResult<List<RoleVo>> select(){
        List<RoleVo> select = roleDoService.select();
        return RestResult.success(select);
    }

    @PostMapping
    public RestResult create(@RequestBody RoleVo roleVo){
        roleDoService.create(roleVo);
        return RestResult.success();
    }

    @PutMapping
    public RestResult update(@RequestBody RoleVo roleVo){
        roleDoService.update(roleVo);
        return RestResult.success();
    }

    @DeleteMapping("/{roleIds}")
    public RestResult delete(@PathVariable List<Long> roleIds){
        roleDoService.delete(roleIds);
        return RestResult.success();
    }
}
