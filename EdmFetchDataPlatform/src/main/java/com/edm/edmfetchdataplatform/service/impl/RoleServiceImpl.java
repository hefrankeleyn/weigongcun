package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.domain.Edmer;
import com.edm.edmfetchdataplatform.domain.EdmerRoleRelation;
import com.edm.edmfetchdataplatform.domain.Role;
import com.edm.edmfetchdataplatform.mapper.RoleMapper;
import com.edm.edmfetchdataplatform.service.EdmerService;
import com.edm.edmfetchdataplatform.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Date 2019-06-23
 * @Author lifei
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired(required = false)
    private RoleMapper roleMapper;


    @Override
    public List<Role> findAllRole() {
        return roleMapper.findAllRole();
    }



    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleMapper.findRoleByRoleName(roleName);
    }

    @Override
    public Role findRoleByRid(Integer rid) {
        return roleMapper.findRoleByRid(rid);
    }

    /**
     * 根据eid删除 edmer 与 role的关联关系
     * @param eid
     */
    @Override
    @Transactional
    public void deleteEdmerRoleRelactionsByEid(Integer eid) {
        roleMapper.deleteEdmerRoleRelactionsByEid(eid);
    }

    @Override
    @Transactional
    public void saveEdmerRoleRelation(EdmerRoleRelation edmerRoleRelation) {
        roleMapper.saveEdmRoleRelation(edmerRoleRelation);
    }

    @Override
    @Transactional
    public void saveEdmerRoleRelationList(List<EdmerRoleRelation> edmerRoleRelationList) {
        if (edmerRoleRelationList != null && !edmerRoleRelationList.isEmpty()){
            for (EdmerRoleRelation edmerRoleRelation :
                    edmerRoleRelationList) {
                roleMapper.saveEdmRoleRelation(edmerRoleRelation);
            }
        }
    }


}
