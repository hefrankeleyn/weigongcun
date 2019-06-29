package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.base.BaseQuery;
import com.edm.edmfetchdataplatform.domain.EdmApplyOrder;
import com.edm.edmfetchdataplatform.base.EdmPage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
     * 根据 流转单id 查询 EdmApplyOrder
     * @param oid
     * @return
     */
    EdmApplyOrder findEdmApplyOrderByOid(String oid);


    /**
     * 根据邮箱查询当前用户发起的流转单
     * @param email
     * @return
     */
    List<EdmApplyOrder> findEdmApplyOrdersByEmail(String email);

    /**
     * 根据用户id 查询该用户申请的流转单
     * @param eid
     * @return
     */
    List<EdmApplyOrder> findEdmApplyOrdersByEid(Integer eid);


    /**
     * 查询一页 EdmApplyOrder
     * @param baseQuery
     * @return
     */
    EdmPage<EdmApplyOrder> findPageEdmApplyOrdersByQuery(BaseQuery baseQuery);

    /**
     * 查询邮箱用户的 一页EdmApplyOrder
     * @param email
     * @return
     */
    EdmPage<EdmApplyOrder> findPageEdmApplyOrdersByEmail(String email);



}
