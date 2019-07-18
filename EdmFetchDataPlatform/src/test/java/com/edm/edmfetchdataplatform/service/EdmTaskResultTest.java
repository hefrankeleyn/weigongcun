package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmTaskResult;
import org.junit.Assert;
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
public class EdmTaskResultTest {

    @Autowired
    private EdmTaskResultService edmTaskResultService;

    @Test
    public void shouldNotNull(){
        Assert.assertNotNull(edmTaskResultService);
    }


    @Test
    public void findEdmTaskResultByDataCodeTest(){
        EdmTaskResult edmTaskResult = edmTaskResultService.findEdmTaskResultByDataCode("aaa");
        System.out.println(edmTaskResult);
    }
}
