package com.edm.edmfetchdataplatform.mapper;

import com.edm.edmfetchdataplatform.domain.EdmTargetDescription;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Date 2019-06-03
 * @Author lifei
 */
@Mapper
public interface EdmTargetDescriptionMapper {

    @Select("select target,description from edm_target_description group by target,description order by target")
    List<EdmTargetDescription> findAllEdmTargetDescription();
}
