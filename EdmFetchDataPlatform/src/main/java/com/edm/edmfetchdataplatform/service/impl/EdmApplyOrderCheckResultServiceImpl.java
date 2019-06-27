package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.domain.EdmApplyOrderCheckResult;
import com.edm.edmfetchdataplatform.mapper.EdmApplyOrderCheckResultMapper;
import com.edm.edmfetchdataplatform.service.EdmApplyOrderCheckResultService;
import com.edm.edmfetchdataplatform.tools.MyIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 流转单的结果
 * @Date 2019-06-27
 * @Author lifei
 */
@Service
public class EdmApplyOrderCheckResultServiceImpl implements EdmApplyOrderCheckResultService {

    @Autowired(required = false)
    private EdmApplyOrderCheckResultMapper edmApplyOrderCheckResultMapper;

    /**
     * 保存流转单的结果
     * @param edmApplyOrderCheckResult
     */
    @Override
    public void saveEdmApplyOrderCheckResult(EdmApplyOrderCheckResult edmApplyOrderCheckResult) {
        if (edmApplyOrderCheckResult!=null){
            if (edmApplyOrderCheckResult.getOcId() == null){
                String ocId = MyIdGenerator.createUUID();
                edmApplyOrderCheckResult.setOcId(ocId);
            }
            edmApplyOrderCheckResultMapper.saveEdmApplyOrderCheckResult(edmApplyOrderCheckResult);
        }
    }
}