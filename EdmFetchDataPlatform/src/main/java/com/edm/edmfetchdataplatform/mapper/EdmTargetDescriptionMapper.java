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

    /**
     * 查询所有的用户维度
     * @return
     */
    @Select("select target,description from edm_target_description group by target,description order by target")
    List<EdmTargetDescription> findAllEdmTargetDescription();

    /**
     * 根据维度id查询维度描述
     * @param target
     * @return
     */
    @Select("select description from edm_target_description where target=#{target} group by description")
    String findEdmDimensionDescriptionByTarget(String target);
}
