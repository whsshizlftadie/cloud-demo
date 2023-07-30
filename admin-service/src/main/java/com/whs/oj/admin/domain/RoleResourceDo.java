package com.whs.oj.admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName sys_role_resource
 */
@TableName(value ="sys_role_resource")
public class RoleResourceDo implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */


    /**
     * 
     */
    private Long roleId;

    /**
     * 
     */
    private Long resourceId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        RoleResourceDo that = (RoleResourceDo) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(roleId, that.roleId)
                .append(resourceId, that.resourceId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(roleId)
                .append(resourceId)
                .toHashCode();
    }

    public Long getId() {
        return id;
    }

    public RoleResourceDo setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getRoleId() {
        return roleId;
    }

    public RoleResourceDo setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public RoleResourceDo setResourceId(Long resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}