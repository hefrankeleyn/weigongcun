package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmerRoleRelation;
import com.edm.edmfetchdataplatform.domain.Edmer;
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
     * 保存edm 和 role之间的关系
     * @param edmerRoleRelation
     */
    void saveEdmerRoleRelation(EdmerRoleRelation edmerRoleRelation);

    /**
     * 保存多个edm和role之间的关系
     * @param edmerRoleRelationList
     */
    void saveEdmerRoleRelationList(List<EdmerRoleRelation> edmerRoleRelationList);

    /**
     * 查询所有的edmer
     * @return
     */
    List<Edmer> findAllEdmer();
}
