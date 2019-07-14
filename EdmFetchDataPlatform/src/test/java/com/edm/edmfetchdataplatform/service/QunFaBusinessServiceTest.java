package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.QunFaBusiness;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Date 2019-07-14
 * @Author lifei
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QunFaBusinessServiceTest {

    @Autowired
    private QunFaBusinessService qunFaBusinessService;

    @Test
    public void findAllEnableQunFaBusinessTest(){
        List<QunFaBusiness> qunFaBusinessList = qunFaBusinessService.findAllEnableQunFaBusiness();
        if (qunFaBusinessList!=null && !qunFaBusinessList.isEmpty()){
            for (QunFaBusiness qunFaBusiness :
                    qunFaBusinessList) {
                System.out.println(qunFaBusiness);
            }
        }
    }

    @Test
    public void findQunFaBusinessByBusTypeTest(){
        QunFaBusiness qunFaBusiness = qunFaBusinessService.findQunFaBusinessByBusinessType(2);
        System.out.println(qunFaBusiness);
    }
}
