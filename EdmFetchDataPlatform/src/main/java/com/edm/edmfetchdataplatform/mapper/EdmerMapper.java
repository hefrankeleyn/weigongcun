package com.edm.edmfetchdataplatform.mapper;

import com.edm.edmfetchdataplatform.domain.Edmer;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EdmerMapper {

    /**
     * 根据 eid 查询 Edmer
     * @param eid
     * @return
     */
    @Select("select eid,username,password,email,department from edmers where eid = #{eid}")
    Edmer findEdmerByEid(@Param(value = "eid") Long eid);

    /**
     * 根据用户名查询 edmer
     * @param username
     * @return
     */
    @Select("select eid,username,password,email,department " +
            "from edmers  where username = #{username}")
    @Results(value = {@Result(id = true, column = "eid", property = "eid"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "email", property = "email"),
            @Result(column = "department", property = "department"),
            @Result(property = "roles", column = "eid", javaType = List.class,
                    many = @Many(select = "com.edm.edmfetchdataplatform.mapper.RoleMapper.findRoleByEid"))})
    Edmer findEdmerByUserName(@Param(value = "username") String username);


    @Select("select eid,username,password,email,department " +
            "from edmers  where email = #{email}")
    @Results(value = {@Result(id = true, column = "eid", property = "eid"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "email", property = "email"),
            @Result(column = "department", property = "department"),
            @Result(property = "roles", column = "eid", javaType = List.class,
                    many = @Many(select = "com.edm.edmfetchdataplatform.mapper.RoleMapper.findRoleByEid"))})
    Edmer findEdmerByEmail(@Param(value = "email") String email);
}
