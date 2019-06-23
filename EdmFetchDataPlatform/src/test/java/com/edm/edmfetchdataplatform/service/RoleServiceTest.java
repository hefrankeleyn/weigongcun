package com.edm.edmfetchdataplatform.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Date 2019-06-23
 * @Author lifei
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void deleteEdmRoleByEid(){
        roleService.deleteEdmerRoleRelactionsByEid(2);
    }
}
