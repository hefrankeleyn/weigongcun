package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmCondition;
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
        List<EdmCondition> edmConditions = edmConditionService.findEdmFetchDataConditionsByEid(1l);
        if (edmConditions != null){
            for (EdmCondition edmCondition:
                    edmConditions) {
                System.out.println(edmCondition);
            }
        }

    }

    @Test
    public void findEdmConditonsByConIdAndEid(){
        Integer[] conIds = new Integer[]{1,2,3};
        List<EdmCondition> edmConditions = edmConditionService.findEdmConditionsByConIdsAndEid(conIds, 1l);
        if (edmConditions != null){
            for (EdmCondition edmCondition:
                    edmConditions) {
                System.out.println(edmCondition);
            }
        }
    }
}
