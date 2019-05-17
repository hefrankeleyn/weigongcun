package com.edm.edmfetchdataplatform.mapper;

import com.edm.edmfetchdataplatform.domain.Role;
import org.apache.ibatis.annotations.*;

/**
 * 权限查询
 * @Date 2019-05-17
 * @Author lifei
 */
@Mapper
public interface RoleMapper {

    @Select("select rid,role_name from edmer_roles where eid = #{eid}")
    @Results(value = {@Result(column = "rid", property = "rid"),
                      @Result(column = "role_name", property = "roleName")})
    Role findRoleByEid(@Param(value = "eid") Long eid);
}
