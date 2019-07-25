package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.domain.EdmerRoleRelation;
import com.edm.edmfetchdataplatform.domain.Edmer;
import com.edm.edmfetchdataplatform.domain.Role;
import com.edm.edmfetchdataplatform.domain.UserDetailsLogin;
import com.edm.edmfetchdataplatform.domain.status.GroupRole;
import com.edm.edmfetchdataplatform.domain.status.GroupRoleFactory;
import com.edm.edmfetchdataplatform.mapper.EdmerMapper;
import com.edm.edmfetchdataplatform.service.EdmerService;
import com.edm.edmfetchdataplatform.service.RoleService;
import com.edm.edmfetchdataplatform.tools.MyPasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Autowired
    private RoleService roleService;

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
     *
     * @param email
     * @return
     */
    @Override
    public UserDetailsLogin findUserDetailsByEmail(String email) {
        Edmer edmer = edmerMapper.findEdmerByEmail(email);
        if (edmer == null) {
            return null;
        } else {
            return new UserDetailsLogin(edmer);
        }
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
        Role role = roleService.findRoleByRoleName(groupRole.getRoleName());
        EdmerRoleRelation edmerRoleRelation = new EdmerRoleRelation(saveEdmer.getEid(), role.getRid());
        roleService.saveEdmerRoleRelation(edmerRoleRelation);
    }


    /**
     * 查询所有的edmer
     *
     * @return
     */
    @Override
    public List<Edmer> findAllEdmer() {
        List<Edmer> edmers = edmerMapper.findAllEdmer();
        return edmers;
    }


    /**
     * 更新用户的权限
     *
     * @param eid
     * @param rids
     */
    @Override
    @Transactional
    public List<Role> updateEdmerRoles(Integer eid, Integer[] rids) {

        List<Role> roles = new ArrayList<>();
        // 判断用户是否存在
        Edmer edmer = edmerMapper.findEdmerByEid(eid);
        if (edmer != null) {
            // 删除eid与role的关联关系
            roleService.deleteEdmerRoleRelactionsByEid(eid);
            // 遍历rid
            for (Integer rid :
                    rids) {
                // 根据rid 查询 Role
                Role role = roleService.findRoleByRid(rid);
                if (role != null) {
                    EdmerRoleRelation roleRelation = new EdmerRoleRelation(eid, rid);
                    roleService.saveEdmerRoleRelation(roleRelation);
                    roles.add(role);
                }
            }
        }
        return roles;
    }

    /**
     * 根据部门查询edmers
     *
     * @param departments
     * @return
     */
    @Override
    public List<Edmer> findEdmersByDepartments(String[] departments) {
        List<Edmer> edmers = edmerMapper.findEdmersByDepartmentArray(departments);
        return edmers;
    }


    /**
     * 查询某一个部门的所有用户
     *
     * @param department
     * @return
     */
    @Override
    public List<Edmer> findEdmersByOneDepartment(String department) {
        List<Edmer> edmers = edmerMapper.findEdmersByOneDepartment(department);
        return edmers;
    }

    /**
     * 根据权限名称查询用户数据
     *
     * @param roleName
     * @return
     */
    @Override
    public List<Edmer> findEdmerListByRole(String roleName) {
        return edmerMapper.findEdmersByRoleName(roleName);
    }

    /**
     * 查询用户的邮箱
     *
     * @param roleNames
     * @return
     */
    @Override
    public List<String> findEdmerEmailsListByRoles(List<String> roleNames) {
        if (roleNames == null || !roleNames.isEmpty()) {
            return null;
        } else {
            List<Edmer> edmers = edmerMapper.findEdmersByRoleNameList(roleNames);
            List<String> emails = new ArrayList<>();
            if (edmers != null) {
                for (Edmer edmer :
                        edmers) {
                    emails.add(edmer.getEmail());
                }
            }
            return emails;
        }
    }

    /**
     * 修改 edmer
     * @param edmer
     */
    @Override
    public void updateEdmer(Edmer edmer) {
        if (edmer != null && edmer.getEid()!=null) {
            edmerMapper.updateEdmer(edmer);
        }
    }

}
