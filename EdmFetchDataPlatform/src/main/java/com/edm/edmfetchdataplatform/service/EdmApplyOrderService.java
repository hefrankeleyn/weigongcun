package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.base.query.EdmApplyOrderQuery;
import com.edm.edmfetchdataplatform.domain.EdmApplyOrder;
import com.edm.edmfetchdataplatform.base.EdmPage;
import com.edm.edmfetchdataplatform.domain.Role;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
     * 修改流转单的状态
     * @param edmApplyOrder
     */
    void updateEdmApplyOrderStatus(EdmApplyOrder edmApplyOrder);


    /**
     * 根据 流转单id 查询 EdmApplyOrder
     * @param oid
     * @return
     */
    EdmApplyOrder findEdmApplyOrderByOid(String oid);

    /**
     * 根据OcId 查询EdmApplyOrder
     * @param ocid
     * @return
     */
    EdmApplyOrder findEdmApplyOrderByOcid(String ocid);


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
     * @param edmApplyOrderQuery
     * @return
     */
    EdmPage<EdmApplyOrder> findPageEdmApplyOrdersByQuery(EdmApplyOrderQuery edmApplyOrderQuery);

    /**
     * 查询邮箱用户的 一页EdmApplyOrder
     * @param email
     * @return
     */
    EdmPage<EdmApplyOrder> findPageEdmApplyOrdersByEmail(String email);


    /**
     * 根据oid 查询流转单excel的 file文件
     * @param oid
     * @return
     */
    File getEdmApplyOrderExcelByOid(String oid);


    /**
     * 根据角色，获取可操作订单的状态
     * @param roles
     * @return
     */
    List<Integer> findOptOrderStatusByRoles(List<Role> roles);


    /**
     * 根据查询条件统计数量
     * @param edmApplyOrderQuery
     * @return
     */
    Integer countEdmApplyOrderByBEdmApplyOrderQuery(EdmApplyOrderQuery edmApplyOrderQuery);


    /**
     * 查询一页 EdmPage
     * @param edmApplyOrderQuery
     * @return
     */
    EdmPage<EdmApplyOrder> findPageEdmApplyOrdersByEdmApplyOrderQuery(EdmApplyOrderQuery edmApplyOrderQuery);


    /**
     * 根据eid和订单状态，查询所有的EdmApplyOrder
     * @param eid
     * @param orderStatusArray
     * @return
     */
    List<EdmApplyOrder> findEdmApplyOrderByEidAndOrderStatusArray(Integer eid, Integer[] orderStatusArray);


    /**
     * 执行提数操作
     * @description
     * 1、根据oid查询OrderState；
     * 2、判断OrderState是否审核完成；
     * 3、如果审核完成将状态修改为提取中；
     * 4、进行提数操作
     */
    void executeFetchDataOption(String oid);




}
