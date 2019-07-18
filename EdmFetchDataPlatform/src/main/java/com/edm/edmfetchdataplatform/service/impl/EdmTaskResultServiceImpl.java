package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.domain.EdmApplyOrder;
import com.edm.edmfetchdataplatform.domain.EdmCondition;
import com.edm.edmfetchdataplatform.domain.EdmTaskResult;
import com.edm.edmfetchdataplatform.mapper.EdmTaskResultMapper;
import com.edm.edmfetchdataplatform.service.EdmApplyOrderService;
import com.edm.edmfetchdataplatform.service.EdmTaskResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * EdmTaskResult 的service
 *
 * @Date 2019-07-18
 * @Author lifei
 */
@Service
public class EdmTaskResultServiceImpl implements EdmTaskResultService {

    @Autowired(required = false)
    private EdmTaskResultMapper edmTaskResultMapper;


    @Autowired
    private EdmApplyOrderService edmApplyOrderService;

    /**
     * 保存EdmTaskResult
     *
     * @param edmTaskResult
     */
    @Override
    @Transactional
    public void saveEdmTaskResult(EdmTaskResult edmTaskResult) {
        if (edmTaskResult != null) {
            edmTaskResultMapper.saveEdmTaskResult(edmTaskResult);
        }
    }

    /**
     * 更新EdmTaskResult
     *
     * @param edmTaskResult
     */
    @Override
    @Transactional
    public void updateEdmTaskResult(EdmTaskResult edmTaskResult) {
        if (edmTaskResult != null) {
            edmTaskResultMapper.updateEdmTaskResultByTaskId(edmTaskResult);
        }
    }

    /**
     * 查询所有可用的EdmTaskResult
     *
     * @return
     */
    @Override
    public List<EdmTaskResult> findAllEnableEdmTaskResults() {
        List<EdmTaskResult> edmTaskResults = edmTaskResultMapper.findAllEnableEdmTaskResults();
        return edmTaskResults;
    }

    /**
     * 根据taskId 查询 EdmTaskResult
     *
     * @param taskId
     * @return
     */
    @Override
    public EdmTaskResult findEdmTaskResultByTaskId(Integer taskId) {
        if (taskId != null) {
            EdmTaskResult edmTaskResult = edmTaskResultMapper.findEdmTaskResultByTaskId(taskId);
            return edmTaskResult;
        }
        return null;
    }

    /**
     * 根据conId 查询 EdmTaskResult
     *
     * @param conId
     * @return
     */
    @Override
    public EdmTaskResult findEdmTaskResultByConId(Integer conId) {
        if (conId != null) {
            EdmTaskResult edmTaskResult = edmTaskResultMapper.findEdmTaskResultByConId(conId);
            return edmTaskResult;
        }
        return null;
    }

    /**
     * 根据conids 查询所有的 EdmTaskResult
     *
     * @param conIds
     * @return
     */
    @Override
    public List<EdmTaskResult> findEdmTaskResultsByConIds(Integer[] conIds) {
        if (conIds != null && conIds.length > 0) {
            List<EdmTaskResult> edmTaskResults = edmTaskResultMapper.findEdmTaskResultsByConIds(conIds);
            return edmTaskResults;
        }
        return null;
    }

    /**
     * 根据conIds 查询所有可用的 EdmTaskResult
     *
     * @param conIds
     * @return
     */
    @Override
    public List<EdmTaskResult> findEnableEdmTaskResultsByConIds(Integer[] conIds) {
        if (conIds != null && conIds.length > 0) {
            List<EdmTaskResult> edmTaskResults = edmTaskResultMapper.findEnableEdmTaskResultsByConIds(conIds);
            return edmTaskResults;
        }
        return null;
    }

    /**
     * 根据用户邮箱查询该用户所有可用的EdmTaskResult
     *
     * @param userEmail
     * @return
     */
    @Override
    public List<EdmTaskResult> findCurrentUserEnableEdmTaskResults(String userEmail) {
        // 查询该用户
        List<EdmApplyOrder> edmApplyOrderList = edmApplyOrderService.findEdmApplyOrdersByEmail(userEmail);
        List<Integer> conIds = new ArrayList<>();
        if (edmApplyOrderList != null && !edmApplyOrderList.isEmpty()) {
            for (EdmApplyOrder edmApplyOrder : edmApplyOrderList) {
                List<EdmCondition> edmConditions = edmApplyOrder.getEdmConditions();
                if (edmConditions != null && !edmConditions.isEmpty()) {
                    for (EdmCondition edmCondition :
                            edmConditions) {
                        Integer conId = edmCondition.getConId();
                        conIds.add(conId);
                    }
                }
            }
        }
        if (conIds.size() > 0) {
            Integer[] conIdArray = new Integer[conIds.size()];

            List<EdmTaskResult> edmTaskResultList = edmTaskResultMapper.findEnableEdmTaskResultsByConIds(conIds.toArray(conIdArray));
            return edmTaskResultList;
        }
        return null;
    }

    /**
     * 根据数据编码查询EdmTaskResult
     * @param dataCode
     * @return
     */
    @Override
    public EdmTaskResult findEdmTaskResultByDataCode(String dataCode) {
        if (dataCode!=null && !dataCode.trim().equals("")){
            EdmTaskResult edmTaskResult = edmTaskResultMapper.findEdmTaskResultByDataCode(dataCode);
            if (edmTaskResult!=null){
                return edmTaskResult;
            }
        }
        return null;
    }
}
