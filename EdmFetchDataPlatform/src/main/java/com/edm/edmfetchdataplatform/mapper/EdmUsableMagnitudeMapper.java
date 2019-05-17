package com.edm.edmfetchdataplatform.mapper;

import com.edm.edmfetchdataplatform.domain.EdmUsableMagnitude;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * @Date 2019-05-16
 * @Author lifei
 */
@Mapper
public interface EdmUsableMagnitudeMapper {

    @Select("select target,magnitude,dt from edm_usable_magnitude where dt = ${dt}")
    List<EdmUsableMagnitude> findEdmUsableMagnitudesByDt(@Param("dt") String dt);
}
