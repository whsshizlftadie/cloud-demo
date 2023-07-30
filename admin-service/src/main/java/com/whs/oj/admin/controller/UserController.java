package com.whs.oj.admin.controller;

import com.whs.oj.admin.api.Pager;
import com.whs.oj.admin.api.RestResult;
import com.whs.oj.admin.api.dto.UserDTO;
import com.whs.oj.admin.controller.base.BaseController;
import com.whs.oj.admin.domain.req.PageRequest;
import com.whs.oj.admin.domain.req.UserUpdateRequest;
import com.whs.oj.admin.domain.vo.UserVo;
import com.whs.oj.admin.service.UserDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value={"/upms","/user"})
public class UserController extends BaseController {

    @Autowired
    private UserDoService userService;

    @GetMapping("/user")
    public UserDTO loadUserByUsername(@RequestParam("username") String username) {
        return userService.loadUserByUsername(username);
    }
    @GetMapping("/current")
    public RestResult<UserDTO> current(){
        UserDTO userDTO = userService.userInfo();
        return RestResult.success(userDTO);
    }


    @PostMapping("/pageList")
    public RestResult<List<UserVo>> pageList(@RequestBody PageRequest req){

        List<UserVo> userVoList
                = userService.pageList(req);
        Pager<UserVo> userVoPager = new Pager<>();
        userVoPager.setRecords(userVoList);
        userVoPager.setTotal(userVoList.size());

        return RestResult.page(userVoPager);
    }

    @PutMapping
    public RestResult update(@RequestBody UserUpdateRequest req){
        userService.updateUserInfo(req);
        return RestResult.success();
    }


    @PostMapping
    public RestResult create(@RequestBody UserUpdateRequest req){
        userService.create(req);
        return RestResult.success();
    }

    @DeleteMapping("/{userIds}")
    public RestResult deleteBatch(@PathVariable List<Long> userIds){
        userService.deletes(userIds);
        return RestResult.success();
    }


}