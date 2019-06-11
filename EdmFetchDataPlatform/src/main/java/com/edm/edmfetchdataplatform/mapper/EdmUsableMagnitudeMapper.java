package com.edm.edmfetchdataplatform.mapper;

import com.edm.edmfetchdataplatform.domain.EdmUsableMagnitude;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Date 2019-05-16
 * @Author lifei
 */
@Mapper
public interface EdmUsableMagnitudeMapper {

    @Select("select target,magnitude,dt from edm_usable_magnitude where dt = ${dt}")
    List<EdmUsableMagnitude> findEdmUsableMagnitudesByDt(@Param("dt") String dt);

    @Select("select t1.target,t1.magnitude,t1.dt,t2.description from edm_usable_magnitude t1 " +
            "inner join edm_target_description t2 " +
            "on t1.target=t2.target where t1.dt=${dt} order by t1.target")
    List<EdmUsableMagnitude> findEdmUsableMagnitudesAndTargetDescriptionByDt(@Param("dt") String dt);
}
