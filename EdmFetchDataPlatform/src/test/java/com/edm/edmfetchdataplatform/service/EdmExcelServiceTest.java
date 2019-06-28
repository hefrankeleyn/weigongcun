package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmApplyOrder;
import com.edm.edmfetchdataplatform.domain.EdmApplyOrderCheckResult;
import com.edm.edmfetchdataplatform.domain.Edmer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试创建excel
 * @Date 2019-06-25
 * @Author lifei
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EdmExcelServiceTest {


    @Autowired
    private EdmExcelService edmExcelService;


    @Test
    public void createExcelTest(){
        EdmApplyOrder edmApplyOrder = new EdmApplyOrder();

        EdmApplyOrderCheckResult edmApplyOrderCheckResult = new EdmApplyOrderCheckResult();




        edmApplyOrder.setOrderName("沃油料13期");

        Edmer edmer = new Edmer();
        edmer.setUsername("小谢");
        edmer.setDepartment("运营组");

        // 申请人及组别
        edmApplyOrder.setEdmer(edmer);
        edmApplyOrder.setQunFaTypeDescription("收入");
        edmApplyOrder.setQunFaSubjectAndContext("节日了，有活动");
        edmApplyOrder.setPaiQiYiXiang("2019年11月1日");
        edmApplyOrder.setTargetSendProvince("北京,天津");
        edmApplyOrder.setUserConditions("高级活跃用户100w");
        edmApplyOrder.setSendNum(1000);
        edmApplyOrder.setChannelSends("516");
        edmApplyOrder.setHowSupplement("不补充");
        edmApplyOrder.setMessageContext("今天心情真好，点这里~");

//        edmExcelService.createEdmApplyExcelOrder(edmApplyOrder, edmApplyOrderCheckResult,"/Users/lifei/Documents/servers/edm_upload_files/2019");

    }
}
