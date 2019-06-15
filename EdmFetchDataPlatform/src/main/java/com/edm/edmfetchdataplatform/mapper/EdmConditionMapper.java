package com.edm.edmfetchdataplatform.mapper;

import com.edm.edmfetchdataplatform.domain.EdmCondition;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 提数条件
 */
@Mapper
public interface EdmConditionMapper {

    /**
     * 添加 提数请求项
     * @param edmCondition
     */
    @Insert("INSERT INTO edm_conditions(conid,dimension,province_if,provincecodes,province_opt,city_if,citycodes," +
            "city_opt,limitnum,eid) VALUES(#{conId},#{dimension},#{provinceIf},#{provinceCodes},#{provinceOpt}," +
            "#{cityIf},#{cityCodes},#{cityOpt},#{limitNum},#{edmer.eid})")
    void saveEdmCondition(EdmCondition edmCondition);

    /**
     * 根据Eid查询 EdmCondition
     * @param eid
     * @return
     */
    @Results(value = {@Result(column = "conid", property = "conId"),
                      @Result(column = "dimension", property = "dimension"),
                      @Result(column = "province_if", property = "provinceIf"),
                      @Result(column = "provincecodes", property = "provinceCodes"),
                      @Result(column = "province_opt", property = "provinceOpt"),
                      @Result(column = "city_if", property = "cityIf"),
                      @Result(column = "citycodes", property = "cityCodes"),
                      @Result(column = "city_opt", property = "cityOpt"),
                      @Result(column = "limitnum", property = "limitNum"),
                      @Result(property = "edmer", column = "eid", javaType = List.class,
                              many = @Many(select = "com.edm.edmfetchdataplatform.mapper.EdmerMapper.findEdmerByEid"))})
    @Select("select conid,dimension,province_if,provincecodes,province_opt,city_if,citycodes," +
            "city_opt,limitnum from edm_conditions where eid=#{eid}")
    List<EdmCondition> findEdmConditionsByEid(@Param("eid") Long eid);
}
