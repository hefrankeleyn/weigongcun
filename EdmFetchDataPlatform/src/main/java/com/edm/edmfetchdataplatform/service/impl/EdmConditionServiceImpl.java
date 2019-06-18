package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.domain.EdmCondition;
import com.edm.edmfetchdataplatform.domain.EdmFetchDataCondition;
import com.edm.edmfetchdataplatform.domain.Edmer;
import com.edm.edmfetchdataplatform.mapper.EdmConditionMapper;
import com.edm.edmfetchdataplatform.service.EdmConditionService;
import com.edm.edmfetchdataplatform.service.EdmTargetDescriptionService;
import com.edm.edmfetchdataplatform.service.EdmZoneService;
import com.edm.edmfetchdataplatform.service.EdmerService;
import com.edm.edmfetchdataplatform.tools.MyArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void savEdmCondition(EdmFetchDataCondition edmFetchDataCondition, String userEmail) {
        Edmer edmer = edmerService.findEdmerByEmail(userEmail);
        saveEdmCondition(edmFetchDataCondition, edmer);
    }

    /**
     * 根据 登陆用户的eid获取所有的 EdmFetchDataCondition
     * @param eid
     * @return
     */
    @Override
    public List<EdmCondition> findEdmFetchDataConditionsByEid(Long eid) {
        List<EdmCondition> edmConditions = edmConditionMapper.findEdmConditionsByEid(eid);


        if(edmConditions == null || edmConditions.isEmpty()){
            return null;
        }else {
            // 补充省份和城市信息
            setProvinceAndCityInfo(edmConditions);
            return edmConditions;
        }
    }

    /**
     * 设置省份和城市信息
     * @param edmConditions
     */
    private void setProvinceAndCityInfo(List<EdmCondition> edmConditions){
        // 查询省份名称 和 城市名称
        for (EdmCondition edmCondition:
                edmConditions) {
            String[] provinceNames = edmZoneService.findProvinceNamesByProvinceCodes(MyArrayUtil.strToArray(edmCondition.getProvinceCodes()));
            edmCondition.setProvinceNames(MyArrayUtil.arrayToStr(provinceNames));

            //查询城市名称
            String[] cityNames = edmZoneService.findCityNamesByCityCodes(MyArrayUtil.strToArray(edmCondition.getCityCodes()));
            edmCondition.setCityNames(MyArrayUtil.arrayToStr(cityNames));

            // 查询维度名称
            String description = edmTargetDescriptionService.findDescriptionByTarget(edmCondition.getDimension());
            edmCondition.setDescription(description);
        }
    }

    @Override
    public List<EdmCondition> findEdmFetchDataConditionsByUserEmail(String userEmail) {
        Edmer edmer = edmerService.findEdmerByEmail(userEmail);
        return findEdmFetchDataConditionsByEid(edmer.getEid());
    }

    @Override
    public List<EdmCondition> findEdmConditionsByConId(Integer[] conIds, Long eid) {
        if(conIds== null || conIds.length==0){
            return null;
        }else {
            List<EdmCondition> edmConditions = edmConditionMapper.findEdmConditionsByConId(conIds, eid);
            // 补充省份和城市信息
            setProvinceAndCityInfo(edmConditions);
            return edmConditions;
        }
    }
}
