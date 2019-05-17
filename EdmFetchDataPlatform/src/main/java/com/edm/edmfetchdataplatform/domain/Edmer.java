package com.edm.edmfetchdataplatform.domain;

import javax.persistence.*;
import java.util.List;

/**
 * 用户信息
 * @Date 2019-05-16
 * @Author lifei
 */
public class Edmer {

    // 唯一的id
    private Long eid;
    private String username;
    private String password;
    private String email;
    private String department;
    private List<Role> roles;

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Edmer{" +
                "eid=" + eid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", roles=" + roles +
                '}';
    }
}
