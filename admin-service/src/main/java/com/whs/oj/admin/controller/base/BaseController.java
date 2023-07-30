package com.whs.oj.admin.controller.base;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {

    @Autowired
    public HttpServletRequest request;

}