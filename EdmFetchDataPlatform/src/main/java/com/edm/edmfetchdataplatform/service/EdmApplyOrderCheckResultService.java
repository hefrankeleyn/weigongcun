package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmApplyOrderCheckResult;

/**
 * edm 群发流转单 结果
 */
public interface EdmApplyOrderCheckResultService {

    /**
     * 保存 流转单的结果
     * @param edmApplyOrderCheckResult
     */
    void saveEdmApplyOrderCheckResult(EdmApplyOrderCheckResult edmApplyOrderCheckResult);
}
