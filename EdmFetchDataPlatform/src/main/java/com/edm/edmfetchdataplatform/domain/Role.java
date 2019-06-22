package com.edm.edmfetchdataplatform.domain;

/**
 * 角色
 * @Date 2019-05-16
 * @Author lifei
 */
public class Role {
    // 角色id
    private Integer rid;
    // 角色名称
    private String roleName;
    // 角色描述
    private String roleDesc;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Override
    public String toString() {
        return "Role{" +
                "rid=" + rid +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                '}';
    }
}
