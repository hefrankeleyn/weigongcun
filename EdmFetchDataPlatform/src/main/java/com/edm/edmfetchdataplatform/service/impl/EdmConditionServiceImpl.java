package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.domain.EdmCondition;
import com.edm.edmfetchdataplatform.domain.EdmFetchDataCondition;
import com.edm.edmfetchdataplatform.domain.Edmer;
import com.edm.edmfetchdataplatform.mapper.EdmConditionMapper;
import com.edm.edmfetchdataplatform.service.EdmConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Date 2019-06-15
 * @Author lifei
 */
@Service
public class EdmConditionServiceImpl implements EdmConditionService {

    @Autowired(required = false)
    private EdmConditionMapper edmConditionMapper;

    /**
     * 保存提数项
     * @param edmCondition
     */
    @Override
    public void saveEdmCondition(EdmCondition edmCondition) {
        edmConditionMapper.saveEdmCondition(edmCondition);
    }

    /**
     * 保存提数项目
     * @param edmFetchDataCondition
     * @param edmer
     */
    @Override
    public void saveEdmCondition(EdmFetchDataCondition edmFetchDataCondition, Edmer edmer) {
        edmConditionMapper.saveEdmCondition(new EdmCondition(edmFetchDataCondition, edmer));
    }
}
