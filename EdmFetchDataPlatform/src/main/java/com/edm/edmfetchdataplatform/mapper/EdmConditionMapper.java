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
    @Insert("INSERT INTO edm_conditions(conid,bus_type,dimensions,province_if,provincecodes,province_opt,city_if,citycodes," +
            "city_opt,data_codes,limitnum,create_date,oid,eid) VALUES(#{conId},#{qunFaBusiness.businessType},#{dimensions},#{provinceIf},#{provinceCodes},#{provinceOpt}," +
            "#{cityIf},#{cityCodes},#{cityOpt},#{dataCodes},#{limitNum},#{createDate},#{oid},#{edmer.eid})")
    void saveEdmCondition(EdmCondition edmCondition);

    /**
     * 根据Eid查询 oid 为null 的 EdmCondition
     * @param eid
     * @return
     */
    @Results(value = {@Result(column = "conid", property = "conId"),
                      @Result(column = "dimensions", property = "dimensions"),
                      @Result(column = "province_if", property = "provinceIf"),
                      @Result(column = "provincecodes", property = "provinceCodes"),
                      @Result(column = "province_opt", property = "provinceOpt"),
                      @Result(column = "city_if", property = "cityIf"),
                      @Result(column = "citycodes", property = "cityCodes"),
                      @Result(column = "city_opt", property = "cityOpt"),
                      @Result(column = "data_codes", property = "dataCodes"),
                      @Result(column = "limitnum", property = "limitNum"),
                      @Result(column = "create_date", property = "createDate"),
                      @Result(column = "oid", property = "oid"),
                      @Result(property = "edmer", column = "eid",
                              one = @One(select = "com.edm.edmfetchdataplatform.mapper.EdmerMapper.findEdmerByEid")),
                      @Result(property = "qunFaBusiness", column = "bus_type",
                                        one = @One(select = "com.edm.edmfetchdataplatform.mapper.QunFaBusinessMapper.findEnalbeQunFaBusinessByBusType"))
    })
    @Select("select conid,bus_type,dimensions,province_if,provincecodes,province_opt,city_if,citycodes," +
            "city_opt,data_codes,limitnum,create_date,oid,eid from edm_conditions where 1=1 and oid is null and eid=#{eid} " +
            "order by create_date")
    List<EdmCondition> findEdmConditionsByEid(@Param("eid") Integer eid);


    /**
     * 根据 多个conId 和eid 查询 EdmCondition
     * 查询多个 EdmCondition
     * @param conId
     * @return
     */
    @Results(value = {@Result(column = "conid", property = "conId"),
            @Result(column = "dimensions", property = "dimensions"),
            @Result(column = "province_if", property = "provinceIf"),
            @Result(column = "provincecodes", property = "provinceCodes"),
            @Result(column = "province_opt", property = "provinceOpt"),
            @Result(column = "city_if", property = "cityIf"),
            @Result(column = "citycodes", property = "cityCodes"),
            @Result(column = "city_opt", property = "cityOpt"),
            @Result(column = "data_codes", property = "dataCodes"),
            @Result(column = "limitnum", property = "limitNum"),
            @Result(column = "create_date", property = "createDate"),
            @Result(column = "oid", property = "oid"),
            @Result(property = "edmer", column = "eid",
                    one = @One(select = "com.edm.edmfetchdataplatform.mapper.EdmerMapper.findEdmerByEid")),
            @Result(property = "qunFaBusiness", column = "bus_type",
                    one = @One(select = "com.edm.edmfetchdataplatform.mapper.QunFaBusinessMapper.findEnalbeQunFaBusinessByBusType"))
    })
    @Select({"<script>",
            "select conid,bus_type,dimensions,province_if,provincecodes,province_opt,city_if,citycodes,city_opt,data_codes,limitnum,create_date,oid,eid ",
             "from edm_conditions where 1=1 and eid=#{eid} and conid in ",
            "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            " order by create_date",
            "</script>"
            })
    List<EdmCondition> findEdmConditionsByConIdsAndEid(@Param("list") Integer[] conId, @Param("eid") Integer eid);
    /**
     * 查询多个 EdmCondition
     * @param conId
     * @return
     */
    @Results(value = {@Result(column = "conid", property = "conId"),
            @Result(column = "dimensions", property = "dimensions"),
            @Result(column = "province_if", property = "provinceIf"),
            @Result(column = "provincecodes", property = "provinceCodes"),
            @Result(column = "province_opt", property = "provinceOpt"),
            @Result(column = "city_if", property = "cityIf"),
            @Result(column = "citycodes", property = "cityCodes"),
            @Result(column = "city_opt", property = "cityOpt"),
            @Result(column = "data_codes", property = "dataCodes"),
            @Result(column = "limitnum", property = "limitNum"),
            @Result(column = "create_date", property = "createDate"),
            @Result(column = "oid", property = "oid"),
            @Result(property = "edmer", column = "eid",
                    one = @One(select = "com.edm.edmfetchdataplatform.mapper.EdmerMapper.findEdmerByEid")),
            @Result(property = "qunFaBusiness", column = "bus_type",
                    one = @One(select = "com.edm.edmfetchdataplatform.mapper.QunFaBusinessMapper.findEnalbeQunFaBusinessByBusType"))
    })
    @Select({"<script>",
            "select conid,bus_type,dimensions,province_if,provincecodes,province_opt,city_if,citycodes,city_opt,data_codes,limitnum,create_date,oid,eid ",
            "from edm_conditions where 1=1 and conid in ",
            "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            " order by create_date",
            "</script>"
    })
    List<EdmCondition> findEdmConditionsByConIds(@Param("list") Integer[] conId);


    /**
     * 根据oid查询对应的 所有 EdmCondition
     * @param oid
     * @return
     */
    @Results(value = {@Result(column = "conid", property = "conId"),
            @Result(column = "dimensions", property = "dimensions"),
            @Result(column = "province_if", property = "provinceIf"),
            @Result(column = "provincecodes", property = "provinceCodes"),
            @Result(column = "province_opt", property = "provinceOpt"),
            @Result(column = "city_if", property = "cityIf"),
            @Result(column = "citycodes", property = "cityCodes"),
            @Result(column = "city_opt", property = "cityOpt"),
            @Result(column = "data_codes", property = "dataCodes"),
            @Result(column = "limitnum", property = "limitNum"),
            @Result(column = "create_date", property = "createDate"),
            @Result(column = "oid", property = "oid"),
            @Result(property = "edmer", column = "eid",
                    one = @One(select = "com.edm.edmfetchdataplatform.mapper.EdmerMapper.findEdmerByEid")),
            @Result(property = "qunFaBusiness", column = "bus_type",
                    one = @One(select = "com.edm.edmfetchdataplatform.mapper.QunFaBusinessMapper.findEnalbeQunFaBusinessByBusType"))
    })
    @Select("select conid,bus_type,dimensions,province_if,provincecodes,province_opt,city_if,citycodes," +
            "city_opt,data_codes,limitnum,create_date,oid,eid from edm_conditions where 1=1 and oid=#{oid} order by create_date")
    List<EdmCondition> findEdmConditionsByOid(String oid);




    /**
     * 更新提数请求项
     * @param edmCondition
     */
    @Update("UPDATE `edm_conditions` SET dimensions = #{dimensions},province_if = #{provinceIf}," +
            "provincecodes = #{provinceCodes},province_opt = #{provinceOpt},city_if = #{cityIf}," +
            "citycodes = #{cityCodes},city_opt = #{cityOpt},data_codes = #{dataCodes},limitnum = #{limitNum},oid = #{oid} " +
            "WHERE conid = #{conId}")
    void updateEdmConditionByConId(EdmCondition edmCondition);
}
