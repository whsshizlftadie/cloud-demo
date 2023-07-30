package com.whs.oj.admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.whs.oj.admin.domain.base.BaseDO;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName sys_role_menu
 */
@TableName(value ="sys_role_menu")
public class RoleMenuDo extends BaseDO implements Serializable {
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
    private Long menuId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public RoleMenuDo setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getRoleId() {
        return roleId;
    }

    public RoleMenuDo setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    public Long getMenuId() {
        return menuId;
    }

    public RoleMenuDo setMenuId(Long menuId) {
        this.menuId = menuId;
        return this;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        RoleMenuDo that = (RoleMenuDo) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(roleId, that.roleId)
                .append(menuId, that.menuId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(roleId)
                .append(menuId)
                .toHashCode();
    }
}