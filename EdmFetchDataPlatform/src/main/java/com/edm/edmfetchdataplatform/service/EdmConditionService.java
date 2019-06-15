package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmCondition;
import com.edm.edmfetchdataplatform.domain.EdmFetchDataCondition;
import com.edm.edmfetchdataplatform.domain.Edmer;

import java.util.List;

public interface EdmConditionService {

    void saveEdmCondition(EdmCondition edmCondition);

    void saveEdmCondition(EdmFetchDataCondition edmFetchDataCondition, Edmer edmer);

    List<EdmFetchDataCondition> findEdmFetchDataConditionsByEid(Long eid);
}
