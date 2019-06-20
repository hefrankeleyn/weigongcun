package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmApplyOrder;
import com.edm.edmfetchdataplatform.domain.EdmApplyOrderAndItemRelation;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 保存 EdmApplyOrder
     * @param edmerEmail
     * @param edmFiles
     * @param edmApplyOrder
     */
    void saveEdmApplyOrder(String edmerEmail, MultipartFile[] edmFiles, EdmApplyOrder edmApplyOrder);


    /**
     * 保存 流转单和申请项之间的关系
     * @param edmApplyOrderAndItemRelation
     */
    void saveEdmApplyOrderAndItemRelation(EdmApplyOrderAndItemRelation edmApplyOrderAndItemRelation);

    /**
     * 保存 多个流转单和申请项之间的关系
     * @param edmApplyOrderAndItemRelations
     */
    void saveEdmApplyOrderAndItemRelations(EdmApplyOrderAndItemRelation[] edmApplyOrderAndItemRelations);
}
