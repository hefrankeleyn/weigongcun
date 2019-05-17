package com.edm.edmfetchdataplatform.domain;

/**
 * 角色
 * @Date 2019-05-16
 * @Author lifei
 */
public class Role {
    private Long rid;
    private String roleName;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "rid=" + rid +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
