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
 * @TableName sys_role
 */
@TableName(value ="sys_role")
public class RoleDo extends BaseDO implements Serializable {
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
    private String code;

    /**
     * 
     */
    private String name;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    public Long getId() {
        return id;
    }

    public RoleDo setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return code;
    }

    public RoleDo setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public RoleDo setName(String name) {
        this.name = name;
        return this;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        RoleDo roleDo = (RoleDo) o;

        return new EqualsBuilder()
                .append(id, roleDo.id)
                .append(code, roleDo.code)
                .append(name, roleDo.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(code)
                .append(name)
                .toHashCode();
    }
}