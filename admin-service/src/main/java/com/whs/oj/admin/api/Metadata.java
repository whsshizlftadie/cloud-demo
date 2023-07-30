package com.whs.oj.admin.api;

import java.io.Serializable;

public class Metadata implements Serializable {

    /**
     * 接口URL
     */
    String resourceUrl;
    /**
     * URL对应的ROLE
     */
    String roleCode;

    public String getResourceUrl() {
        return resourceUrl;
    }

    public Metadata setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
        return this;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public Metadata setRoleCode(String roleCode) {
        this.roleCode = roleCode;
        return this;
    }
}