package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmCondition;

/**
 * 用于操作hive
 */
public interface HiveService {


    /**
     * 根据 EdmCondition 创造数据编码
     * @param edmCondition
     * @return
     */
    String createDataCodeByEdmCondition(EdmCondition edmCondition);
}
