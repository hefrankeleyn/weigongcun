package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmFetchDataCondition;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Date 2019-06-15
 * @Author lifei
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EdmConditionServiceTest {

    @Autowired
    private EdmConditionService edmConditionService;

    @Test
    public void shouldNotNull(){
        Assert.assertNotNull(edmConditionService);
    }

    @Test
    public void findEdmConditiondsByEid(){
        List<EdmFetchDataCondition> edmFetchDataConditions = edmConditionService.findEdmFetchDataConditionsByEid(1l);
        for (EdmFetchDataCondition edmFetchDataCondition:
             edmFetchDataConditions) {
            System.out.println(edmFetchDataCondition);
        }
    }
}
