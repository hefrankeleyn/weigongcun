package com.edm.edmfetchdataplatform.dao;

import com.edm.edmfetchdataplatform.base.query.EdmApplyOrderQuery;
import com.edm.edmfetchdataplatform.domain.EdmApplyOrder;
import com.edm.edmfetchdataplatform.mapper.EdmApplyOrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2019-07-04
 * @Author lifei
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EdmApplyOrderMapperTest {

    @Autowired(required = false)
    private EdmApplyOrderMapper edmApplyOrderMapper;

    /**
     * 测试分页，根据条件查询
     */
    @Test
    public void countEdmApplyOrdersByQuery(){
        EdmApplyOrderQuery edmApplyOrderQuery = new EdmApplyOrderQuery();
//        edmApplyOrderQuery.setEid(1);
        List<Integer> orderStates = new ArrayList<>();
        orderStates.add(0);
        edmApplyOrderQuery.setOrderStates(orderStates);

        Integer totalNum = edmApplyOrderMapper.countEdmApplyOrdersByEdmApplyOrderQuery(edmApplyOrderQuery);
        System.out.println("totalNum: " + totalNum);
    }

    @Test
    public void findPageEdmApplyOrdersByEdmApplyOrderQuery(){
        EdmApplyOrderQuery edmApplyOrderQuery = new EdmApplyOrderQuery();
//        edmApplyOrderQuery.setEid(1);
        List<Integer> orderStates = new ArrayList<>();
        orderStates.add(0);
        List<EdmApplyOrder> edmApplyOrders = edmApplyOrderMapper.findPageEdmApplyOrdersByEdmApplyOrderQuery(edmApplyOrderQuery, 1, 2);
        if (edmApplyOrders!=null && !edmApplyOrders.isEmpty()){
            for (EdmApplyOrder edmApplyOrder: edmApplyOrders){
                System.out.println(edmApplyOrder);
            }
        }
    }
}
