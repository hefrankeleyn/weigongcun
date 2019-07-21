package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmTaskResult;
import com.edm.edmfetchdataplatform.domain.translate.DataCodeOfEdmApplyOrder;

import java.util.List;

/**
 * 操作EdmTaskResult 的service
 */
public interface EdmTaskResultService {

    /**
     * 保存EdmTaskResult
     * @param edmTaskResult
     */
    void saveEdmTaskResult(EdmTaskResult edmTaskResult);

    /**
     * 更新EdmTaskResult
     * @param edmTaskResult
     */
    void updateEdmTaskResult(EdmTaskResult edmTaskResult);

    /**
     * 查询所有可用的EdmTaskResult
     * @return
     */
    List<EdmTaskResult> findAllEnableEdmTaskResults();

    /**
     * 根据 taskId 查询 EdmTaskResult
     * @param taskId
     * @return
     */
    EdmTaskResult findEdmTaskResultByTaskId(Integer taskId);

    /**
     * 根据 EdmCondition 的 id 查询 EdmTaskResult
     * @param conId
     * @return
     */
    EdmTaskResult findEdmTaskResultByConId(Integer conId);

    /**
     * 根据 多个 EdmConditions 的id 查询对应的 EdmTaskResult
     * @param conIds
     * @return
     */
    List<EdmTaskResult> findEdmTaskResultsByConIds(Integer[] conIds);


    /**
     * 根据conIds 查询 所有 可用的 EdmTaskResult
     * @param conIds
     * @return
     */
    List<EdmTaskResult> findEnableEdmTaskResultsByConIds(Integer[] conIds);

    /**
     * 根据用户邮箱，查询当前用户的所有可用的EdmTaskResult
     * @param userEmail
     * @return
     */
    List<EdmTaskResult> findCurrentUserEnableEdmTaskResults(String userEmail);


    /**
     * 根据数据编码查询 EdmTaskResult
     * @param dataCode
     * @return
     */
    EdmTaskResult findEdmTaskResultByDataCode(String dataCode);

    /**
     * 根据oid和数据编码获取 DataCodeOfEdmApplyOrder
     * @param oid
     * @param dataCode
     * @return
     */
    DataCodeOfEdmApplyOrder findDataCodeOfEdmApplyOrderByOidAndDataCode(String oid, String dataCode);
}
