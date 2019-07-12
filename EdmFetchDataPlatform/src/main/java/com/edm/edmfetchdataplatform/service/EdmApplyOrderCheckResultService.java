package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.base.query.EdmApplyOrderResultQuery;
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


    /**
     * 修改 edmApplyOrderResultQuery
     * @param edmApplyOrderResultQuery
     */
    void updateEdmApplyOrderCheckResult(EdmApplyOrderResultQuery edmApplyOrderResultQuery);

    /**
     * 根据oid查询EdmApplyOrderCheckResult
     * @param oid
     * @return
     */
    EdmApplyOrderCheckResult findEdmApplyOrderCheckResultByOid(String oid);


    /**
     * 根据ocid 查询 EdmApplyOrderCheckResult
     * @param ocid
     * @return
     */
    EdmApplyOrderCheckResult findEdmApplyOrderCheckResultOcid(String ocid);

}
