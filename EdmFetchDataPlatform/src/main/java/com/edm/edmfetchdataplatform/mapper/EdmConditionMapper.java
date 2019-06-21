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
            "city_opt,limitnum from edm_conditions where 1=1 and oid is null and eid=#{eid}")
    List<EdmCondition> findEdmConditionsByEid(@Param("eid") Long eid);


    /**
     * 根据 多个conId 和eid 查询 EdmCondition
     * 查询多个 EdmCondition
     * @param conId
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
            @Result(column = "oid", property = "oid"),
            @Result(property = "edmer", column = "eid", javaType = List.class,
                    many = @Many(select = "com.edm.edmfetchdataplatform.mapper.EdmerMapper.findEdmerByEid"))})
    @Select({"<script>",
            "select conid,dimension,province_if,provincecodes,province_opt,city_if,citycodes,city_opt,limitnum,oid ",
             "from edm_conditions where 1=1 and eid=#{eid} and conid in ",
            "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"
            })
    List<EdmCondition> findEdmConditionsByConIdsAndEid(@Param("list") Integer[] conId, @Param("eid") Long eid);
    /**
     * 查询多个 EdmCondition
     * @param conId
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
            @Result(column = "oid", property = "oid")
    })
    @Select({"<script>",
            "select conid,dimension,province_if,provincecodes,province_opt,city_if,citycodes,city_opt,limitnum,oid ",
            "from edm_conditions where 1=1 and conid in ",
            "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"
    })
    List<EdmCondition> findEdmConditionsByConIds(@Param("list") Integer[] conId);





    /**
     * 更新提数请求项
     * @param edmCondition
     */
    @Update("UPDATE `edm_conditions` SET `dimension` = #{dimension},`province_if` = #{provinceIf}," +
            "`provincecodes` = #{provinceCodes},`province_opt` = #{provinceOpt},`city_if` = #{cityIf}," +
            "`citycodes` = #{cityCodes},`city_opt` = #{cityOpt},`limitnum` = #{limitNum},`oid` = #{oid} " +
            "WHERE `conid` = #{conId}")
    void updateEdmConditionByConId(EdmCondition edmCondition);
}
