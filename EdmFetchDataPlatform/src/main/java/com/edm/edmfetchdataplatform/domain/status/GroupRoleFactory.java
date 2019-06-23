package com.edm.edmfetchdataplatform.domain.status;

/**
 * @Date 2019-06-23
 * @Author lifei
 */
public class GroupRoleFactory {

    /**
     * 获取 默认组别的权限
     * @param department
     * @return
     */
    public static GroupRole fetchGroupRole(String department){
        if (GroupRole.ROLE_OPERATION.getDepartment().equals(department)){
            return GroupRole.ROLE_OPERATION;
        }else if (GroupRole.ROLE_APPLY.getDepartment().equals(department)){
            return GroupRole.ROLE_APPLY;
        }else if (GroupRole.ROLE_CAPACITY.getDepartment().equals(department)){
            return GroupRole.ROLE_CAPACITY;
        }else if (GroupRole.ROLE_CUSTOMER_SERVICE.getDepartment().equals(department)){
            return GroupRole.ROLE_CUSTOMER_SERVICE;
        }else {
            return GroupRole.ROLE_SHUJU;
        }
    }
}
