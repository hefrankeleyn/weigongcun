package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmZone;

import java.util.List;

public interface EdmZoneService {

    /**
     * 查询所有的省份数据
     * @return
     */
    List<EdmZone> findAllProvinces();

    /**
     * 根据省份代码查询城市信息
     * @param provincecode
     * @return
     */
    List<EdmZone> findCitiesByProvinceCode(String provincecode);

    /**
     * 根据省份代码查询省份名称
     * @param provinceCodes
     * @return
     */
    String[] findProvinceNamesByProvinceCodes(String[] provinceCodes);

    /**
     * 根据城市代码查询城市名称
     * @param cityCodes
     * @return
     */
    String[] findCityNamesByCityCodes(String[] cityCodes);

}
