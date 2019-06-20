package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmApplyOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @Date 2019-06-20
 * @Author lifei
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class EdmApplyOrderServiceTest {

//    @Autowired
    private EdmApplyOrderService edmApplyOrderService;


//    @Test
    public void saveEdmApplyOrder(){

        String email = "shuju@wo.cn";
        Integer[] conIds = new Integer[]{1,2};
        EdmApplyOrder edmApplyOrder = edmApplyOrderService.orderInit(conIds, email);

        edmApplyOrder.setOrderName("沃油料13期");
        edmApplyOrder.setOrderState(0);
        edmApplyOrder.setApplyDate(new Date());
        edmApplyOrder.setQunFaTypeDescription("收入");
        edmApplyOrder.setQunFaSubjectAndContext("节日，活动");
        edmApplyOrder.setPaiQiYiXiang("2019年11月1日");
        edmApplyOrder.setChannelSends("516");
        edmApplyOrder.setHowSupplement("不补充");
        edmApplyOrder.setMessageContext("【沃邮箱团队】权威播报：");





        edmApplyOrderService.saveEdmApplyOrder(email, null, edmApplyOrder);

    }
}
