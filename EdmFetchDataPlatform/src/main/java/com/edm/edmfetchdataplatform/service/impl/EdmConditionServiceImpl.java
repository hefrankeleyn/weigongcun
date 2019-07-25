package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.domain.*;
import com.edm.edmfetchdataplatform.mapper.EdmConditionMapper;
import com.edm.edmfetchdataplatform.service.*;
import com.edm.edmfetchdataplatform.tools.MyArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Date 2019-06-15
 * @Author lifei
 */
@Service
public class EdmConditionServiceImpl implements EdmConditionService {

    @Autowired(required = false)
    private EdmConditionMapper edmConditionMapper;

    @Autowired(required = false)
    private EdmerService edmerService;

    @Autowired(required = false)
    private EdmZoneService edmZoneService;

    @Autowired(required = false)
    private EdmTargetDescriptionService edmTargetDescriptionService;

    @Autowired
    private QunFaBusinessService qunFaBusinessService;

    /**
     * 保存提数项
     *
     * @param edmCondition
     */
    @Override
    @Transactional
    public void saveEdmCondition(EdmCondition edmCondition) {
        edmConditionMapper.saveEdmCondition(edmCondition);
    }

    /**
     * 保存提数项目
     *
     * @param edmFetchDataCondition
     * @param edmer
     */
    @Override
    @Transactional
    public void saveEdmCondition(EdmFetchDataCondition edmFetchDataCondition, Edmer edmer) {
        EdmCondition edmCondition = new EdmCondition(edmFetchDataCondition, edmer);
        edmCondition.setCreateDate(new Date());
        edmConditionMapper.saveEdmCondition(edmCondition);
    }

    @Override
    @Transactional
    public void saveEdmCondition(EdmFetchDataCondition edmFetchDataCondition, String userEmail) {
        Edmer edmer = edmerService.findEdmerByEmail(userEmail);
        // 根据businessType 查询 QunFaBusiness
        if (edmFetchDataCondition != null && edmFetchDataCondition.getQunFaBusiness() != null) {
            Integer businessType = edmFetchDataCondition.getQunFaBusiness().getBusinessType();
            QunFaBusiness qunFaBusiness = qunFaBusinessService.findQunFaBusinessByBusinessType(businessType);
            edmFetchDataCondition.setQunFaBusiness(qunFaBusiness);
        }
        saveEdmCondition(edmFetchDataCondition, edmer);
    }

    /**
     * 根据 登陆用户的eid获取所有的 EdmFetchDataCondition
     *
     * @param eid
     * @return
     */
    @Override
    public List<EdmCondition> findEdmFetchDataConditionsByEid(Integer eid) {
        List<EdmCondition> edmConditions = edmConditionMapper.findEdmConditionsByEid(eid);
        if (edmConditions == null || edmConditions.isEmpty()) {
            return null;
        } else {
            // 补充省份和城市信息
            setProvinceAndCityInfo(edmConditions);
            return edmConditions;
        }
    }

    /**
     * 设置省份和城市信息
     *
     * @param edmConditions
     */
    private void setProvinceAndCityInfo(List<EdmCondition> edmConditions) {
        // 查询省份名称 和 城市名称
        for (EdmCondition edmCondition :
                edmConditions) {
            String[] provinceNames = edmZoneService.findProvinceNamesByProvinceCodes(MyArrayUtil.strToArray(edmCondition.getProvinceCodes()));
            edmCondition.setProvinceNames(MyArrayUtil.arrayToStr(provinceNames));

            //查询城市名称
            String[] cityNames = edmZoneService.findCityNamesByCityCodes(MyArrayUtil.strToArray(edmCondition.getCityCodes()));
            edmCondition.setCityNames(MyArrayUtil.arrayToStr(cityNames));

            // 查询维度名称
            String[] dimensions = MyArrayUtil.strToArray(edmCondition.getDimensions());
            List<EdmTargetDescription> edmTargetDescriptions = edmTargetDescriptionService.findEdmTargetDescriptionsByTargets(dimensions);
            if (edmTargetDescriptions != null && !edmTargetDescriptions.isEmpty()) {
                String[] descriptions = new String[edmTargetDescriptions.size()];
                for (int i = 0; i < edmTargetDescriptions.size(); i++) {
                    descriptions[i] = edmTargetDescriptions.get(i).getDescription();
                }
                edmCondition.setDescriptions(MyArrayUtil.arrayToStr(descriptions));
            }
        }
    }

    @Override
    public List<EdmCondition> findEdmFetchDataConditionsByUserEmail(String userEmail) {
        Edmer edmer = edmerService.findEdmerByEmail(userEmail);
        return findEdmFetchDataConditionsByEid(edmer.getEid());
    }

    @Override
    public List<EdmCondition> findEdmConditionsByConIdsAndEid(Integer[] conIds, Integer eid) {
        if (conIds == null || conIds.length == 0) {
            return null;
        } else {
            List<EdmCondition> edmConditions = edmConditionMapper.findEdmConditionsByConIdsAndEid(conIds, eid);
            // 补充省份和城市信息
            setProvinceAndCityInfo(edmConditions);
            return edmConditions;
        }
    }

    @Override
    public List<EdmCondition> findEdmConditionsByConIds(Integer[] conIds) {
        if (conIds == null || conIds.length == 0) {
            return null;
        } else {
            List<EdmCondition> edmConditions = edmConditionMapper.findEdmConditionsByConIds(conIds);
            // 补充省份和城市信息
            setProvinceAndCityInfo(edmConditions);
            return edmConditions;
        }
    }

    /**
     * 更新 edmCondition
     *
     * @param edmCondition
     */
    @Override
    @Transactional
    public void updateEdmCondition(EdmCondition edmCondition) {
        if (edmCondition != null) {
            edmConditionMapper.updateEdmConditionByConId(edmCondition);
        }
    }

    /**
     * 更新多个edmCondition
     *
     * @param edmConditions
     */
    @Override
    @Transactional
    public void updateEdmConditions(List<EdmCondition> edmConditions) {
        if (edmConditions != null && !edmConditions.isEmpty()) {
            for (EdmCondition edmCondition :
                    edmConditions) {
                edmConditionMapper.updateEdmConditionByConId(edmCondition);
            }
        }
    }

    /**
     * 根据oid查询 EdmCondition
     *
     * @param oid
     * @return
     */
    @Override
    public List<EdmCondition> findEdmConditionsByOid(String oid) {
        List<EdmCondition> edmConditions = edmConditionMapper.findEdmConditionsByOid(oid);
        return edmConditions;
    }

    /**
     * 根据 conId 删除EdmCondition
     *
     * @param conId
     */
    @Override
    public void deleteEdmConditionByConId(Integer conId) {
        if (conId != null) {
            edmConditionMapper.deleteEdmConditionByConId(conId);
        }
    }


}
