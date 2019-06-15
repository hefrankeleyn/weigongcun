package com.edm.edmfetchdataplatform.mapper;

import com.edm.edmfetchdataplatform.domain.EdmCondition;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * 提数条件
 */
@Mapper
public interface EdmConditionMapper {

    @Insert("INSERT INTO edm_conditions(conid,dimension,province_if,provincecodes,province_opt,city_if,citycodes," +
            "city_opt,limitnum,eid) VALUES(#{conId},#{dimension},#{provinceIf},#{provinceCodes},#{provinceOpt}," +
            "#{cityIf},#{cityCodes},#{cityOpt},#{limitNum},#{edmer.eid})")
    void saveEdmCondition(EdmCondition edmCondition);
}
