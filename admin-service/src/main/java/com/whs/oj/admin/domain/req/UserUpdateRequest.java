package com.whs.oj.admin.domain.req;

import java.util.List;

public class UserUpdateRequest {

    private Long id;

    private String username;

    private String nickname;

    private Integer status;

    private List<Long> roleIds;

    public Long getId() {
        return id;
    }

    public UserUpdateRequest setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserUpdateRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public UserUpdateRequest setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public UserUpdateRequest setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public UserUpdateRequest setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
        return this;
    }
}
