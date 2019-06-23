package com.edm.edmfetchdataplatform.domain.status;

/**
 * @Date 2019-06-23
 * @Author lifei
 */
public enum  GroupRole {

    ROLE_OPERATION("运营组","ROLE_OPERATION"),
    ROLE_APPLY("申请组","ROLE_APPLY"),
    ROLE_CAPACITY("能力组","ROLE_CAPACITY"),
    ROLE_CUSTOMER_SERVICE("客服组","ROLE_CUSTOMER_SERVICE"),
    ROLE_SHUJU("数据组","ROLE_SHUJU"),
    ;
    private String department;
    private String roleName;

    GroupRole(String department, String roleName) {
        this.department = department;
        this.roleName = roleName;
    }

    public String getDepartment() {
        return department;
    }

    public String getRoleName() {
        return roleName;
    }


}
