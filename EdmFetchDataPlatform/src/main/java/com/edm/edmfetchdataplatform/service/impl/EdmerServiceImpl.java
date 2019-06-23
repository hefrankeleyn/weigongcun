package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.domain.EdmerRoleRelation;
import com.edm.edmfetchdataplatform.domain.Edmer;
import com.edm.edmfetchdataplatform.domain.Role;
import com.edm.edmfetchdataplatform.domain.UserDetailsLogin;
import com.edm.edmfetchdataplatform.domain.status.GroupRole;
import com.edm.edmfetchdataplatform.domain.status.GroupRoleFactory;
import com.edm.edmfetchdataplatform.mapper.EdmerMapper;
import com.edm.edmfetchdataplatform.mapper.RoleMapper;
import com.edm.edmfetchdataplatform.service.EdmerService;
import com.edm.edmfetchdataplatform.tools.MyPasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Date 2019-05-16
 * @Author lifei
 */
@Service
public class EdmerServiceImpl implements EdmerService {

    /**
     * 将 属性required 设置 false， Spring会尝试执行自动装配，
     * 如果没有匹配到bean，Spring将bean处于未装配状态，但此时
     * 未装配属性的状态有可能会出现NullPointException
     */
    @Autowired(required = false)
    private EdmerMapper edmerMapper;

    @Autowired(required = false)
    private RoleMapper roleMapper;

    @Override
    public Edmer findEdmerByEid(Integer eid) {
        return edmerMapper.findEdmerByEid(eid);
    }

    @Override
    public UserDetailsLogin findUserDetailsByUserName(String username) {

        Edmer edmer = edmerMapper.findEdmerByUserName(username);
        return new UserDetailsLogin(edmer);
    }

    /**
     * 根据邮箱查询
     * @param email
     * @return
     */
    @Override
    public UserDetailsLogin findUserDetailsByEmail(String email) {
        Edmer edmer = edmerMapper.findEdmerByEmail(email);
        return new UserDetailsLogin(edmer);
    }

    @Override
    public Edmer findEdmerByEmail(String email) {
        return edmerMapper.findEdmerByEmail(email);
    }

    @Override
    @Transactional
    public void saveEdmer(Edmer edmer) {
        // 将密码进行转码
        edmer.setPassword(MyPasswordUtil.passwordEncord(edmer.getPassword()));
        edmerMapper.saveEdmer(edmer);

        Edmer saveEdmer = edmerMapper.findEdmerByEmail(edmer.getEmail());
        // 赋予相应组别的权限
        GroupRole groupRole = GroupRoleFactory.fetchGroupRole(edmer.getDepartment());
        Role role = roleMapper.findRoleByRoleName(groupRole.getRoleName());
        EdmerRoleRelation edmerRoleRelation = new EdmerRoleRelation(saveEdmer.getEid(), role.getRid());
        roleMapper.saveEdmRoleRelation(edmerRoleRelation);
    }

    /**
     * 保存多个
     * @param edmerRoleRelation
     */
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

    /**
     * 查询所有的edmer
     * @return
     */
    @Override
    public List<Edmer> findAllEdmer() {
        List<Edmer> edmers = edmerMapper.findAllEdmer();
        return edmers;
    }
}
