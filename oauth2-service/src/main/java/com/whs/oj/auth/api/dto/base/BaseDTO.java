package com.whs.oj.auth.api.dto.base;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseDTO implements Serializable {

    /**
     * 主键ID
     */
    private String id;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人ID
     */
    private String createUser;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 修改人ID
     */
    private String updateUser;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BaseDTO baseDTO = (BaseDTO) o;

        return new EqualsBuilder()
                .append(id, baseDTO.id)
                .append(createTime, baseDTO.createTime)
                .append(createUser, baseDTO.createUser)
                .append(updateTime, baseDTO.updateTime)
                .append(updateUser, baseDTO.updateUser)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(createTime)
                .append(createUser)
                .append(updateTime)
                .append(updateUser)
                .toHashCode();
    }

    public String getId() {
        return id;
    }

    public BaseDTO setId(String id) {
        this.id = id;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public BaseDTO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getCreateUser() {
        return createUser;
    }

    public BaseDTO setCreateUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public BaseDTO setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public BaseDTO setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }
}