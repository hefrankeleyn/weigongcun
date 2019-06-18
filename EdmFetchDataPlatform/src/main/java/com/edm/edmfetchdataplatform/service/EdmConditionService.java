package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmCondition;
import com.edm.edmfetchdataplatform.domain.EdmFetchDataCondition;
import com.edm.edmfetchdataplatform.domain.Edmer;

import java.util.List;

public interface EdmConditionService {

    void saveEdmCondition(EdmCondition edmCondition);

    void saveEdmCondition(EdmFetchDataCondition edmFetchDataCondition, Edmer edmer);

    void savEdmCondition(EdmFetchDataCondition edmFetchDataCondition, String userEmail);

    List<EdmCondition> findEdmFetchDataConditionsByEid(Long eid);

    List<EdmCondition> findEdmFetchDataConditionsByUserEmail(String userEmail);

    /**
     * 根据conId查询多个申请项
     * @param conIds
     * @param eid 当前登陆用户的id
     * @return
     */
    List<EdmCondition> findEdmConditionsByConId(Integer[] conIds, Long eid);
}
