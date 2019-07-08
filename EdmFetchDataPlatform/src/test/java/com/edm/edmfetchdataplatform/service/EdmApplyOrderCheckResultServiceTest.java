package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmApplyOrderCheckResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Date 2019-06-28
 * @Author lifei
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EdmApplyOrderCheckResultServiceTest {

    @Autowired
    private EdmApplyOrderCheckResultService edmApplyOrderCheckResultService;

    /**
     * 测试，根据 oid 查询 EdmApplyOrderCheckResult
     */
    @Test
    public void findEdmApplyOrderResultByOid(){
        String oid = "c0e3808b8238432abcecf0b291da2b2a";

        EdmApplyOrderCheckResult edmApplyOrderCheckResult = edmApplyOrderCheckResultService.findEdmApplyOrderCheckResultByOid(oid);
        System.out.println(edmApplyOrderCheckResult);
    }

    @Test
    public void findEdmApplyOrderResultByocId(){
        String ocId = "a97b88c834234940807ac41052975071";

        EdmApplyOrderCheckResult edmApplyOrderCheckResult = edmApplyOrderCheckResultService.findEdmApplyOrderCheckResultOcid(ocId);
        System.out.println(edmApplyOrderCheckResult);
    }


}
