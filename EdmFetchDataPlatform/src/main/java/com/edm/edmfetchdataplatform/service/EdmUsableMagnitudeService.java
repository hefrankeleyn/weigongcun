package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmUsableMagnitude;

import java.util.List;

public interface EdmUsableMagnitudeService {


    /**
     * 查询当天 所有的 EdmUsable
     * @return
     */
    List<EdmUsableMagnitude> findCurrentDayEdmUsableMagnitudes();


    List<EdmUsableMagnitude> findTodayEdmUsableMagnitudesAndDescription();
}
