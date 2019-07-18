package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Date 2019-07-18
 * @Author lifei
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EdmRoleServiceTest {


    @Autowired
    private RoleService roleService;


    @Test
    public void findRoleByRoleName(){
        Role role = roleService.findRoleByRoleName("aaa");
        System.out.println(role);
    }
}
