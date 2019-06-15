package com.edm.edmfetchdataplatform.mapper;

import com.edm.edmfetchdataplatform.domain.EdmZone;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Date 2019-06-11
 * @Author lifei
 */
@Mapper
public interface EdmZoneMapper {

    /**
     * 查询所有的省份数据
     * @return
     */
    @Select("select provincecode,provincename from edm_zone group by provincecode,provincename order by provincecode")
    List<EdmZone> findAllProvince();

    /**
     * 根据省份代码查询所有的城市
     * @param provincecode
     * @return
     */
    @Select("select provincecode,provincename,citycode,cityname from edm_zone " +
            "where provincecode=${provincecode} " +
            "group by provincecode,provincename,citycode,cityname")
    List<EdmZone> findCitysByProvinceCode(@Param("provincecode") String provincecode);

    /**
     * 根据省份代码查询省份名称
     * @param provinceCode
     * @return
     */
    @Select({"<script>",
            "SELECT provincename FROM edm_zone  WHERE provincecode IN",
            "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "group by provincename",
            "</script>"})
    String[] findProvinceNameByProvinceCodes(@Param("list") String[] provinceCode);

    /**
     * 根据城市代码查询城市名称
     * @param CityCode
     * @return
     */
    @Select({"<script>",
            "SELECT cityname FROM edm_zone  WHERE citycode IN",
            "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "group by cityname",
            "</script>"})
    String[] findCityNameByCityCodes(@Param("list") String[] CityCode);



}
