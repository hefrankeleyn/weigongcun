package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.domain.EdmApplyOrder;
import com.edm.edmfetchdataplatform.domain.EdmCondition;
import com.edm.edmfetchdataplatform.domain.EdmTargetDescription;
import com.edm.edmfetchdataplatform.domain.Edmer;
import com.edm.edmfetchdataplatform.domain.status.IncludeState;
import com.edm.edmfetchdataplatform.service.EdmApplyOrderService;
import com.edm.edmfetchdataplatform.service.EdmConditionService;
import com.edm.edmfetchdataplatform.service.EdmTargetDescriptionService;
import com.edm.edmfetchdataplatform.service.EdmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date 2019-06-18
 * @Author lifei
 */
@Service
public class EdmApplyOrderServiceImpl implements EdmApplyOrderService {


    @Autowired
    private EdmConditionService edmConditionService;

    @Autowired
    private EdmerService edmerService;

    @Autowired
    private EdmTargetDescriptionService edmTargetDescriptionService;

    /**
     * 对申请的订单进行初始化
     *
     * @param conId
     * @return
     */
    @Override
    public EdmApplyOrder orderInit(Integer[] conId, String email) {
        // 获取用户
        Edmer edmer = edmerService.findEdmerByEmail(email);
        // 获取订单项
        List<EdmCondition> edmConditions = edmConditionService.findEdmConditionsByConId(conId, edmer.getEid());
        // 组合成订单
        EdmApplyOrder edmApplyOrder = initEdmApplyOrder(edmConditions, edmer);
        return edmApplyOrder;
    }

    /**
     * 对EdmApplyOrder 进行初始化
     *
     * @param edmConditions
     * @return
     */
    private EdmApplyOrder initEdmApplyOrder(List<EdmCondition> edmConditions, Edmer edmer) {

        if (edmConditions == null || edmConditions.size() == 0){
            return null;
        }
        // 查询数据目标描述
        String[] targets = new String[edmConditions.size()];
        for (int i = 0; i < edmConditions.size(); i++) {
            targets[i] = edmConditions.get(i).getDimension();
        }
        // 查询 EdmConditions
        List<EdmTargetDescription> edmTargetDescriptions = edmTargetDescriptionService.findEdmTargetDescriptionsByTargets(targets);


        Map<String, String> targetDescription = new HashMap<>();
        for (int i = 0; i < edmTargetDescriptions.size(); i++) {
            targetDescription.put(edmTargetDescriptions.get(i).getTarget(), edmTargetDescriptions.get(i).getDescription());
        }

        EdmApplyOrder edmApplyOrder = new EdmApplyOrder();
        // 拼接组别、和用户名
        edmApplyOrder.setEdmerDepartment(edmer.getDepartment());
        edmApplyOrder.setEdmUserName(edmer.getUsername());
        // 拼接目标群发省份
        StringBuilder qunfaProvinceAndCityConditions = new StringBuilder();
        // 用户数据要求
        StringBuilder usersDataCondition = new StringBuilder();
        int sendNum = 0;
        for (int i = 0; i < edmConditions.size(); i++) {
            // 判断省份条件是否生效
            EdmCondition edmCondition = edmConditions.get(i);
            qunfaProvinceAndCityConditions.append("目标用户" + (i + 1) + ": ");

            if (excludeIf(edmCondition.getProvinceIf()) && excludeIf(edmCondition.getCityIf())) {
                qunfaProvinceAndCityConditions.append("全国。");
            } else {
                // 判断省份
                setQunFaProvinceValue(edmCondition.getProvinceOpt(), edmCondition.getProvinceNames(), qunfaProvinceAndCityConditions);
                // 判断城市
                setQunFaProvinceValue(edmCondition.getCityOpt(), edmCondition.getCityNames(), qunfaProvinceAndCityConditions);
            }
            // 添加换行符
            qunfaProvinceAndCityConditions.append("\n");

            //
            usersDataCondition.append("目标用户" + (i + 1) + ": ");

            // 根据
            usersDataCondition.append(targetDescription.get(edmCondition.getDimension()));
            usersDataCondition.append(", 提取 " + edmCondition.getLimitNum() + ";");
            usersDataCondition.append("\n");

            sendNum += edmCondition.getLimitNum();


        }
        // 群发省份
        edmApplyOrder.setTargetSendProvince(qunfaProvinceAndCityConditions.toString());
        edmApplyOrder.setUserConditions(usersDataCondition.toString());

        edmApplyOrder.setSendNum(sendNum);

        return edmApplyOrder;
    }

    /**
     * 判断是否不包含
     *
     * @param state
     * @return 如果不包含返回 true
     */
    private boolean excludeIf(Integer state) {
        return (state == null || state == IncludeState.EXCLUDE.getState());
    }

    /**
     * 拼接目标群发省份
     *
     * @param state
     * @param desc
     * @param sb
     */
    private void setQunFaProvinceValue(Integer state, String desc, StringBuilder sb) {
        if (state == IncludeState.INCLUDE.getState()) {
            sb.append("取 " + desc + "。");
        } else if (state == IncludeState.EXCLUDE.getState()) {
            sb.append("排除 " + desc + "。");
        }
    }
}
