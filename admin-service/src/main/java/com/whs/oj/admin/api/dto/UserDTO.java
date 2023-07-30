package com.whs.oj.admin.api.dto;

import com.whs.oj.admin.domain.UserDo;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class UserDTO extends UserDo {

    private List<String> roles;

    public UserDTO() {

    }

    public UserDTO(UserDo user) {
        if (user != null) {
            BeanUtils.copyProperties(user, this);
        }
    }

    public List<String> getRoles() {
        return roles;
    }

    public UserDTO setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserDTO userDTO = (UserDTO) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(roles, userDTO.roles)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(roles)
                .toHashCode();
    }
}