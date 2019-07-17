package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmTaskResult;
import com.edm.edmfetchdataplatform.domain.translate.EdmConditionOfOrder;

/**
 * 用于操作hive
 */
public interface HiveService {


    /**
     * 通过操作hive，获取EdmTaskResult
     * @param edmConditionOfOrder
     * @return
     */
    EdmTaskResult optHiveFetchEdmTaskResult(EdmConditionOfOrder edmConditionOfOrder);
}
