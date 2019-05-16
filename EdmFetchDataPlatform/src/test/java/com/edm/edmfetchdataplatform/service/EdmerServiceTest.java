package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.Edmer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
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
}
