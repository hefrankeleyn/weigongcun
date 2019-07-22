package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.base.EdmPage;
import com.edm.edmfetchdataplatform.base.query.DataCodeOfEdmOrderQuery;
import com.edm.edmfetchdataplatform.domain.EdmApplyOrder;
import com.edm.edmfetchdataplatform.domain.EdmCondition;
import com.edm.edmfetchdataplatform.domain.EdmTaskResult;
import com.edm.edmfetchdataplatform.domain.status.ExamineProgressState;
import com.edm.edmfetchdataplatform.domain.translate.DataCodeOfEdmApplyOrder;
import com.edm.edmfetchdataplatform.mapper.EdmTaskResultMapper;
import com.edm.edmfetchdataplatform.service.EdmApplyOrderService;
import com.edm.edmfetchdataplatform.service.EdmTaskResultService;
import com.edm.edmfetchdataplatform.tools.MyStrUtil;
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
     *
     * @param dataCode
     * @return
     */
    @Override
    public EdmTaskResult findEdmTaskResultByDataCode(String dataCode) {
        if (dataCode != null && !dataCode.trim().equals("")) {
            EdmTaskResult edmTaskResult = edmTaskResultMapper.findEdmTaskResultByDataCode(dataCode);
            if (edmTaskResult != null) {
                return edmTaskResult;
            }
        }
        return null;
    }

    /**
     * 根据oid和dataCode 查询 DataCodeOfEdmApplyOrder
     *
     * @param oid
     * @param dataCode
     * @return
     */
    @Override
    public DataCodeOfEdmApplyOrder findDataCodeOfEdmApplyOrderByOidAndDataCode(String oid, String dataCode) {
        if (!MyStrUtil.isEmptyOrNull(oid) && !MyStrUtil.isEmptyOrNull(dataCode)) {
            EdmApplyOrder edmApplyOrder = edmApplyOrderService.findEdmApplyOrderByOid(oid);
            EdmTaskResult edmTaskResult = findEdmTaskResultByDataCode(dataCode);
            DataCodeOfEdmApplyOrder dataCodeOfEdmApplyOrder =
                    new DataCodeOfEdmApplyOrder(edmApplyOrder, edmTaskResult);
            return dataCodeOfEdmApplyOrder;
        }
        return null;
    }

    /**
     * 查询ocIds 中可用的EdmTaskResult记录条数
     *
     * @param dataCodeOfEdmOrderQuery
     * @return
     */
    @Override
    public Integer countEdmTaskResultByQuery(DataCodeOfEdmOrderQuery dataCodeOfEdmOrderQuery) {
        return edmTaskResultMapper.countEdmTaskResultByOcIds(dataCodeOfEdmOrderQuery);
    }

    /**
     * 分页查询数据编码列表，根据条件查询一页数据编码列表
     *
     * @param dataCodeOfEdmOrderQuery
     * @return
     */
    @Override
    public EdmPage<DataCodeOfEdmApplyOrder> findPageDataCodeOfEdmApplyOrderByDataCodeQuery(DataCodeOfEdmOrderQuery dataCodeOfEdmOrderQuery) {
        // 如果eid不等于空，查询该用户下所有流转成功的流转单
        if (dataCodeOfEdmOrderQuery.getEid() != null) {
            Integer[] orderStatusArray = new Integer[]{ExamineProgressState.DATA_GROUP_EXAMINE_SUCCESS.getStatus(),
                    ExamineProgressState.DATA_GROUP_EXAMINE_FAIL.getStatus()};
            List<EdmApplyOrder> edmApplyOrderList = edmApplyOrderService.findEdmApplyOrderByEidAndOrderStatusArray(dataCodeOfEdmOrderQuery.getEid(), orderStatusArray);
            if (edmApplyOrderList != null && !edmApplyOrderList.isEmpty()) {
                List<String> coIds = new ArrayList<>();
                for (int i = 0; i < edmApplyOrderList.size(); i++) {
                    coIds.add(edmApplyOrderList.get(i).getEdmApplyOrderCheckResult().getOcId());
                }
                dataCodeOfEdmOrderQuery.setOcIds(coIds);
            }
        }
        // 查询总的记录条数
        int totalNum = countEdmTaskResultByQuery(dataCodeOfEdmOrderQuery);

        EdmPage<DataCodeOfEdmApplyOrder> edmPage = new EdmPage<>(totalNum, dataCodeOfEdmOrderQuery.getCurrentPage(),
                dataCodeOfEdmOrderQuery.getPageSize());
        List<EdmTaskResult> edmTaskResults = edmTaskResultMapper.findPageEdmTaskResultByQuery(dataCodeOfEdmOrderQuery,
                edmPage.getCurrentPageFirstItemNum(), edmPage.getPageSize());

        if (edmTaskResults != null && !edmTaskResults.isEmpty()) {
            List<DataCodeOfEdmApplyOrder> dataCodeOfEdmApplyOrderList = new ArrayList<>();
            for (EdmTaskResult edmTaskResult : edmTaskResults) {
                EdmApplyOrder edmApplyOrder = edmApplyOrderService.findEdmApplyOrderByOcid(edmTaskResult.getOcId());
                dataCodeOfEdmApplyOrderList.add(new DataCodeOfEdmApplyOrder(edmApplyOrder, edmTaskResult));
            }
            edmPage.setPageObjList(dataCodeOfEdmApplyOrderList);
        } else {
            edmPage.setPageObjList(null);
        }
        return edmPage;
    }

}
