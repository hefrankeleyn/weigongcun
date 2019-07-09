package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmerRoleRelation;
import com.edm.edmfetchdataplatform.domain.Edmer;
import com.edm.edmfetchdataplatform.domain.Role;
import com.edm.edmfetchdataplatform.domain.UserDetailsLogin;

import java.util.List;

/**
 * edmer 的service
 */
public interface EdmerService {

    Edmer findEdmerByEid(Integer eid);

    UserDetailsLogin findUserDetailsByUserName(String username);

    UserDetailsLogin findUserDetailsByEmail(String email);

    /**
     * 根据邮箱查询用户
     * @param email
     * @return
     */
    Edmer findEdmerByEmail(String email);

    /**
     * 保存edmer
     * @param edmer
     */
    void saveEdmer(Edmer edmer);

    /**
     * 查询所有的edmer
     * @return
     */
    List<Edmer> findAllEdmer();

    /**
     * 更新 edmer 的权限
     * @param eid
     * @param rids
     * @return 返回修改后的权限
     */
    List<Role> updateEdmerRoles(Integer eid, Integer[] rids);


    /**
     * 根据部门查询edmers
     * @param departments
     * @return
     */
    List<Edmer> findEdmersByDepartments(String[] departments);


    /**
     * 查找一个部门的所有用户
     * @param department
     * @return
     */
    List<Edmer> findEdmersByOneDepartment(String department);

    /**
     * 根据权限查询用户数据
     * @param roleName
     * @return
     */
    List<Edmer> findEdmerListByRole(String roleName);


    /**
     * 根据 用户角色 查询用户邮箱
     * @param roleNames
     * @return
     */
    List<String> findEdmerEmailsListByRoles(List<String> roleNames);
}
