package com.edm.edmfetchdataplatform.mapper;

import com.edm.edmfetchdataplatform.domain.Edmer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EdmerMapper {

    /**
     * 根据 eid 查询 Edmer
     * @param eid
     * @return
     */
    @Select("select eid,username,password,email,department from edmers where eid = ${eid}")
    Edmer findEdmerByEid(@Param(value = "eid") Long eid);
}
