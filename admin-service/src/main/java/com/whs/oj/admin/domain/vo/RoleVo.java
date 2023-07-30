package com.whs.oj.admin.domain.vo;

import java.util.List;

public class RoleVo {

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

    private List<Long> resourceIds;


    public List<Long> getResourceIds() {
        return resourceIds;
    }

    public RoleVo setResourceIds(List<Long> resourceIds) {
        this.resourceIds = resourceIds;
        return this;
    }

    public Long getId() {
        return id;
    }

    public RoleVo setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return code;
    }

    public RoleVo setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public RoleVo setName(String name) {
        this.name = name;
        return this;
    }
}
