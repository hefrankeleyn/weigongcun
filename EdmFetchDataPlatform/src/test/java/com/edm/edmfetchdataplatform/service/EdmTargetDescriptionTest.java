package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmTargetDescription;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

/**
 * @Date 2019-06-05
 * @Author lifei
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EdmTargetDescriptionTest {

    @Autowired
    private EdmTargetDescriptionService edmTargetDescriptionService;

    @Test
    public void shouldNotNull(){
        Assert.assertNotNull(edmTargetDescriptionService);
    }

    @Test
    public void findAllTargetDescriptionTest(){
        List<EdmTargetDescription> edmTargetDescriptions = edmTargetDescriptionService.findAllEdmTargetDescription();
        if (edmTargetDescriptions != null){
            for (EdmTargetDescription edmtargetDescription :
                    edmTargetDescriptions) {
                System.out.println(edmtargetDescription);
            }
        }
    }

    @Test
    public void findDescriptionByTarget(){
        String description = edmTargetDescriptionService.findDescriptionByTarget("lg01");
        System.out.println(description);

    }

    @Test
    public void findDescriptionsByTargets(){
        String[] targets = new String[]{"lg01","lg02"};
        List<EdmTargetDescription> edmTargetDescriptions = edmTargetDescriptionService.findEdmTargetDescriptionsByTargets(targets);
        if (edmTargetDescriptions != null){
            for (EdmTargetDescription edmTargetDescription :
                    edmTargetDescriptions) {
                System.out.println(edmTargetDescription);
            }
        }
    }

}
