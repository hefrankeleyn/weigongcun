package com.edm.edmfetchdataplatform.domain;

import java.util.List;

/**
 * 用户信息
 * @Date 2019-05-16
 * @Author lifei
 */
public class Edmer {

    // 唯一的id
    private Integer eid;
    private String username;
    private String password;
    private String email;
    private String department;
    private Integer level;
    private List<Role> roles;

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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
                ", level=" + level +
                ", roles=" + roles +
                '}';
    }
}
