package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.domain.Edmer;
import com.edm.edmfetchdataplatform.domain.UserDetailsLogin;
import com.edm.edmfetchdataplatform.mapper.EdmerMapper;
import com.edm.edmfetchdataplatform.service.EdmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Edmer findEdmerByEid(Long eid) {
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
}
