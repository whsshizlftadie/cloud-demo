package com.whs.oj.admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.whs.oj.admin.domain.base.BaseDO;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * 
 * @TableName sys_user
 */
@TableName(value ="sys_user")
public class UserDo extends BaseDO implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

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


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Long id) {
        this.id = id;
    }



    /**
     * 
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 
     */
    public void setStatus(Integer status) {
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserDo userDo = (UserDo) o;

        return new EqualsBuilder()
                .append(id, userDo.id)
                .append(username, userDo.username)
                .append(password, userDo.password)
                .append(nickname, userDo.nickname)
                .append(avatar, userDo.avatar)
                .append(spaceBackground, userDo.spaceBackground)
                .append(personalitySignature, userDo.personalitySignature)
                .append(status, userDo.status)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(username)
                .append(password)
                .append(nickname)
                .append(avatar)
                .append(spaceBackground)
                .append(personalitySignature)
                .append(status)
                .toHashCode();
    }

    public String getSpaceBackground() {
        return spaceBackground;
    }

    public UserDo setSpaceBackground(String spaceBackground) {
        this.spaceBackground = spaceBackground;
        return this;
    }

    public String getPersonalitySignature() {
        return personalitySignature;
    }

    public UserDo setPersonalitySignature(String personalitySignature) {
        this.personalitySignature = personalitySignature;
        return this;
    }

    @Override
    public String toString() {
        return "UserDo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", spaceBackground='" + spaceBackground + '\'' +
                ", personalitySignature='" + personalitySignature + '\'' +
                ", status=" + status +
                '}';
    }
}