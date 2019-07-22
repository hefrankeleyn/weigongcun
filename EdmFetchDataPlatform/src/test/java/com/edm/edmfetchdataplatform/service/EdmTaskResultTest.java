package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.base.EdmPage;
import com.edm.edmfetchdataplatform.base.query.DataCodeOfEdmOrderQuery;
import com.edm.edmfetchdataplatform.domain.EdmTaskResult;
import com.edm.edmfetchdataplatform.domain.translate.DataCodeOfEdmApplyOrder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 测试分页
     */
    @Test
    public void findPageEdmTaskResultTest(){
        DataCodeOfEdmOrderQuery dataCodeOfEdmOrderQuery = new DataCodeOfEdmOrderQuery();
        List<String> ocIds = new ArrayList<>();
        ocIds.add("73ebac0b4bc64f25a4716e34db930555");
        dataCodeOfEdmOrderQuery.setOcIds(ocIds);
        Integer countNum = edmTaskResultService.countEdmTaskResultByQuery(dataCodeOfEdmOrderQuery);
        System.out.println("countNum: " + countNum);
    }

    @Test
    public void findPageDataCodeOfEdmApplyOrderByDataCodeQueryTest(){
        DataCodeOfEdmOrderQuery dataCodeOfEdmOrderQuery = new DataCodeOfEdmOrderQuery();
//        dataCodeOfEdmOrderQuery.setEid(1);
        EdmPage<DataCodeOfEdmApplyOrder> edmPage =
                edmTaskResultService.findPageDataCodeOfEdmApplyOrderByDataCodeQuery(dataCodeOfEdmOrderQuery);
        System.out.println(edmPage);
    }
}
