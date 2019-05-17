package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.Edmer;
import com.edm.edmfetchdataplatform.domain.UserDetailsLogin;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试集成MyBatis是否成功
 * @Date 2019-05-16
 * @Author lifei
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EdmerServiceTest {

    @Autowired
    private EdmerService edmerService;

    @Test
    public void shouldNotNull(){
        Assert.assertNotNull(edmerService);
    }

    @Test
    public void findEdmByEidTest(){
        Edmer edmer = edmerService.findEdmerByEid(1l);
        System.out.println(edmer);
    }

    /**
     * 测试
     */
    @Test
    public void findEdmByUserNameTest(){
        UserDetailsLogin userDetailsLogin = edmerService.findUserDetailsByUserName("edm");
        System.out.println(userDetailsLogin);
    }
}
