package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.domain.EdmZone;
import com.edm.edmfetchdataplatform.mapper.EdmZoneMapper;
import com.edm.edmfetchdataplatform.service.EdmZoneService;
import com.edm.edmfetchdataplatform.tools.MyStrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Date 2019-06-11
 * @Author lifei
 */
@Service
public class EdmZoneServiceImpl implements EdmZoneService {

    @Autowired(required = false)
    private EdmZoneMapper edmZoneMapper;


    @Override
    public List<EdmZone> findAllProvinces() {
        return edmZoneMapper.findAllProvince();
    }

    @Override
    public List<EdmZone> findCitiesByProvinceCode(String provincecode) {
        return edmZoneMapper.findCitysByProvinceCode(provincecode);
    }

    @Override
    public String[] findProvinceNamesByProvinceCodes(String[] provinceCodes) {
        if(provinceCodes == null || provinceCodes.length == 0){
            return null;
        }
        return edmZoneMapper.findProvinceNameByProvinceCodes(provinceCodes);
    }

    /**
     * 只查询省份和省份代码信息
     * @param provinceCodes
     * @return
     */
    @Override
    public List<EdmZone> findEdmZoneOnlyWithProvinceNameAndProvinceCodeByProvinceCodes(String[] provinceCodes) {
        if (provinceCodes!=null && provinceCodes.length>0){
            List<EdmZone> edmZones = edmZoneMapper.findProvinceNameAndCodesByProvinceCodes(provinceCodes);
            return edmZones;
        }
        return null;
    }

    @Override
    public String[] findCityNamesByCityCodes(String[] cityCodes) {
        if(cityCodes == null || cityCodes.length == 0){
            return null;
        }
        return edmZoneMapper.findCityNameByCityCodes(cityCodes);
    }

    /**
     * 根据省份代码查询省份名称
     * @param provinceCode
     * @return
     */
    @Override
    public String findProvinceNameByProvinceCode(String provinceCode) {
        if (!MyStrUtil.isEmptyOrNull(provinceCode)) {
            return edmZoneMapper.findProvinceNameByProvinceCode(provinceCode);
        }
        return null;
    }
}
