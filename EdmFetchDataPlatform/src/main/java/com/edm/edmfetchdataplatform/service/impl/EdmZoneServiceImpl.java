package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.domain.EdmZone;
import com.edm.edmfetchdataplatform.mapper.EdmZoneMapper;
import com.edm.edmfetchdataplatform.service.EdmZoneService;
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
}
