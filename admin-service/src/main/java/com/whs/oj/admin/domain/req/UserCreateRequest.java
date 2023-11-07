package com.whs.oj.admin.domain.req;

public class UserCreateRequest extends UserUpdateRequest {

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
