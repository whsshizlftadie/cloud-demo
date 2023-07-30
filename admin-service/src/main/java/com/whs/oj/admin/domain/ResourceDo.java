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
 * @TableName sys_resource
 */
@TableName(value ="sys_resource")
public class ResourceDo extends BaseDO implements Serializable {
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
    private String name;

    /**
     * 
     */
    private String url;

    /**
     * 
     */
    private Integer status;

    /**
     * 
     */
    private Integer sort;

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
    public String getName() {
        return name;
    }

    public ResourceDo setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ResourceDo setUrl(String url) {
        this.url = url;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public ResourceDo setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public ResourceDo setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ResourceDo that = (ResourceDo) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(name, that.name)
                .append(url, that.url)
                .append(status, that.status)
                .append(sort, that.sort)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(url)
                .append(status)
                .append(sort)
                .toHashCode();
    }
}