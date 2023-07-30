package com.whs.oj.admin.domain.req;

import java.io.Serializable;

public class PageRequest implements Serializable {

    private Long pageNo;
    private Long pageSize;
    private String keyword;

    public Long getPageNo() {
        return pageNo;
    }

    public PageRequest setPageNo(Long pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public PageRequest setPageSize(Long pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public String getKeyword() {
        return keyword;
    }

    public PageRequest setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }
}
