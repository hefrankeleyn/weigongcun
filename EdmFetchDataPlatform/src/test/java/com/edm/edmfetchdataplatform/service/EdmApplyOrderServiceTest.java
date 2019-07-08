package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.base.EdmPage;
import com.edm.edmfetchdataplatform.domain.EdmApplyOrder;
import com.edm.edmfetchdataplatform.domain.Edmer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @Date 2019-06-20
 * @Author lifei
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EdmApplyOrderServiceTest {

    @Autowired
    private EdmApplyOrderService edmApplyOrderService;

    @Autowired
    private EdmerService edmerService;

    //    @Test
    public void saveEdmApplyOrder() {

        String email = "shuju@wo.cn";
        Integer[] conIds = new Integer[]{1, 2};
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

    @Test
    public void findPageEdmApplyOrderByQuery() {
        String email = "shuju@wo.cn";
        Edmer edmer = edmerService.findEdmerByEmail(email);
        if (edmer != null) {
            System.out.println(edmer.getRoles());
            List<Integer> optOrderStatues = edmApplyOrderService.findOptOrderStatusByRoles(edmer.getRoles());
            System.out.println(optOrderStatues);
        }
    }

    @Test
    public void findEdmApplyOrderByEmailTest() {
        String email = "shuju@wo.cn";
        List<EdmApplyOrder> edmApplyOrders = edmApplyOrderService.findEdmApplyOrdersByEmail(email);
        if (edmApplyOrders != null && !edmApplyOrders.isEmpty()) {
            for (EdmApplyOrder edmApplyOrder :
                    edmApplyOrders) {
                System.out.println(edmApplyOrder);
                System.out.println(edmApplyOrder.getEdmApplyOrderCheckResult());
            }
        }
    }

    @Test
    public void findPageEdmApplyOrdersByEmailTest() {
        String email = "shuju@wo.cnn";
        EdmPage<EdmApplyOrder> pageEdmApplyOrders = edmApplyOrderService.findPageEdmApplyOrdersByEmail(email);
        System.out.println(pageEdmApplyOrders);
    }

}
