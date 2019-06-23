package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmerRoleRelation;
import com.edm.edmfetchdataplatform.domain.Role;

import java.util.List;

/**
 * 操作权限的 service
 */
public interface RoleService {

    /**
     * 查询所有的权限
     * @return
     */
    List<Role> findAllRole();

    /**
     * 保存edm 与 role之间的关联关系
     * @param edmerRoleRelation
     */
    void saveEdmerRoleRelation(EdmerRoleRelation edmerRoleRelation);

    /**
     * 保存多个edm和role之间的关系
     * @param edmerRoleRelationList
     */
    void saveEdmerRoleRelationList(List<EdmerRoleRelation> edmerRoleRelationList);


    /**
     * 根据 roleName 查询 Role
     * @param roleName
     * @return
     */
    Role findRoleByRoleName(String roleName);

    /**
     * 根据rid 查询 role
     * @param rid
     * @return
     */
    Role findRoleByRid(Integer rid);

    /**
     * 根据 eid 删除所有的 EdmerRoleRelaction
     * @param eid
     */
    void deleteEdmerRoleRelactionsByEid(Integer eid);

}
