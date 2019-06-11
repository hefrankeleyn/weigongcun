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
}
