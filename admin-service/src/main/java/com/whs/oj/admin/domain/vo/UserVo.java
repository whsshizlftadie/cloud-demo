package com.whs.oj.admin.domain.vo;

import java.util.List;

public class UserVo {

    private Long id;

    /**
     *
     */
    private String username;


    /**
     *
     */
    private String nickname;

    /**
     * 空间背景图片 门户系统展示
     */
    private String spaceBackground;

    /**
     * 个性签名，门户系统
     */
    private String personalitySignature;

    /**
     *
     */
    private Integer status;

    private List<Long> roleIds;

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public UserVo setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserVo setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserVo setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public UserVo setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getSpaceBackground() {
        return spaceBackground;
    }

    public UserVo setSpaceBackground(String spaceBackground) {
        this.spaceBackground = spaceBackground;
        return this;
    }

    public String getPersonalitySignature() {
        return personalitySignature;
    }

    public UserVo setPersonalitySignature(String personalitySignature) {
        this.personalitySignature = personalitySignature;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public UserVo setStatus(Integer status) {
        this.status = status;
        return this;
    }
}
