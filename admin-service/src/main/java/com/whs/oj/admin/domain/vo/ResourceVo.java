package com.whs.oj.admin.domain.vo;

public class ResourceVo {

    private Long id;

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

    private Integer sort;

    public Integer getSort() {
        return sort;
    }

    public ResourceVo setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ResourceVo setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ResourceVo setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ResourceVo setUrl(String url) {
        this.url = url;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public ResourceVo setStatus(Integer status) {
        this.status = status;
        return this;
    }
}
