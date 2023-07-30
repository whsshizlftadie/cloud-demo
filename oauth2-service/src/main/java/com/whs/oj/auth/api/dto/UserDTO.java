package com.whs.oj.auth.api.dto;

import com.whs.oj.auth.api.dto.base.BaseDTO;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public class UserDTO extends BaseDTO {

    /**
     *
     */
    private String username;
    /**
     *
     */
    private String password;
    /**
     *
     */
    private String nickname;
    /**
     *
     */
    private String avatar;
    /**
     *
     */
    private Integer status;
    /**
     *
     */
    private List<String> roles;

    public String getUsername() {
        return username;
    }

    public UserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public UserDTO setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public UserDTO setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public UserDTO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public List<String> getRoles() {
        return roles;
    }

    public UserDTO setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserDTO userDTO = (UserDTO) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(username, userDTO.username)
                .append(password, userDTO.password)
                .append(nickname, userDTO.nickname)
                .append(avatar, userDTO.avatar)
                .append(status, userDTO.status)
                .append(roles, userDTO.roles)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(username)
                .append(password)
                .append(nickname)
                .append(avatar)
                .append(status)
                .append(roles)
                .toHashCode();
    }
}