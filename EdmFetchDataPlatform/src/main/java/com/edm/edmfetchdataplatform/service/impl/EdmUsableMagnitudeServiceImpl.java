package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.domain.EdmUsableMagnitude;
import com.edm.edmfetchdataplatform.mapper.EdmUsableMagnitudeMapper;
import com.edm.edmfetchdataplatform.service.EdmUsableMagnitudeService;
import com.edm.edmfetchdataplatform.tools.DateTransitionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Date 2019-05-16
 * @Author lifei
 */
@Service
public class EdmUsableMagnitudeServiceImpl implements EdmUsableMagnitudeService {

    @Autowired(required = false)
    private EdmUsableMagnitudeMapper edmUsableMagnitudeMapper;

    @Override
    public List<EdmUsableMagnitude> findCurrentDayEdmUsableMagnitudes() {

        return edmUsableMagnitudeMapper.findEdmUsableMagnitudesByDt(DateTransitionUtil.toDateStr(new Date()));
    }
}
