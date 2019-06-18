package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmApplyOrder;

/**
 *  edm 申请的 订单提交
 */
public interface EdmApplyOrderService {

    /**
     * 申请订单初始化
     * @param conId 订单id
     * @param email 用户 邮箱
     * @return
     */
    EdmApplyOrder orderInit(Integer[] conId, String email);
}
