package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmCondition;
import com.edm.edmfetchdataplatform.domain.EdmFetchDataCondition;
import com.edm.edmfetchdataplatform.domain.Edmer;

import java.util.List;

public interface EdmConditionService {

    void saveEdmCondition(EdmCondition edmCondition);

    void saveEdmCondition(EdmFetchDataCondition edmFetchDataCondition, Edmer edmer);

    void savEdmCondition(EdmFetchDataCondition edmFetchDataCondition, String userEmail);

    List<EdmCondition> findEdmFetchDataConditionsByEid(Integer eid);

    List<EdmCondition> findEdmFetchDataConditionsByUserEmail(String userEmail);

    /**
     * 根据conId 和 eid 查询多个申请项
     * @param conIds
     * @param eid 当前登陆用户的id
     * @return
     */
    List<EdmCondition> findEdmConditionsByConIdsAndEid(Integer[] conIds, Integer eid);


    /**
     * 根据 conIds 查询多个 EdmCondition
     * @param conIds
     * @return
     */
    List<EdmCondition> findEdmConditionsByConIds(Integer[] conIds);

    /**
     * 更新edmCondition
     * @param edmCondition
     */
    void updateEdmCondition(EdmCondition edmCondition);

    /**
     * 更新多个edmCondition
     * @param edmConditions
     */
    void updateEdmConditions(List<EdmCondition> edmConditions);

    /**
     * 根据oid 查询所有的condition
     * @param oid
     * @return
     */
    List<EdmCondition> findEdmConditionsByOid(String oid);
}
