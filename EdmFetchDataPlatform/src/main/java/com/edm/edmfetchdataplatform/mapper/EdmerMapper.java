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
    @Select("select eid,username,password,email,department,level from edmers where eid = #{eid}")
    @Results(value = {@Result(id = true, column = "eid", property = "eid"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "email", property = "email"),
            @Result(column = "department", property = "department"),
            @Result(column = "level", property = "level"),
            @Result(property = "roles", column = "eid", javaType = List.class,
                    many = @Many(select = "com.edm.edmfetchdataplatform.mapper.RoleMapper.findRoleByEid"))})
    Edmer findEdmerByEid(@Param(value = "eid") Integer eid);


    /**
     * 根据用户名查询 edmer
     * @param username
     * @return
     */
    @Select("select eid,username,password,email,department,level " +
            "from edmers  where username = #{username}")
    @Results(value = {@Result(id = true, column = "eid", property = "eid"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "email", property = "email"),
            @Result(column = "department", property = "department"),
            @Result(column = "level", property = "level"),
            @Result(property = "roles", column = "eid", javaType = List.class,
                    many = @Many(select = "com.edm.edmfetchdataplatform.mapper.RoleMapper.findRoleByEid"))})
    Edmer findEdmerByUserName(@Param(value = "username") String username);


    @Select("select eid,username,password,email,department,level " +
            "from edmers  where email = #{email}")
    @Results(value = {@Result(id = true, column = "eid", property = "eid"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "email", property = "email"),
            @Result(column = "department", property = "department"),
            @Result(column = "level", property = "level"),
            @Result(property = "roles", column = "eid", javaType = List.class,
                    many = @Many(select = "com.edm.edmfetchdataplatform.mapper.RoleMapper.findRoleByEid"))})
    Edmer findEdmerByEmail(@Param(value = "email") String email);

    /**
     * 保存edmer
     * @param edmer
     */
    @Insert("INSERT INTO `edmers` (`username`,`password`,`email`,`department`,`level`) VALUES " +
            "(#{username},#{password},#{email},#{department},#{level})")
    void saveEdmer(Edmer edmer);

    /**
     * 查询所有的edmer
     * @return
     */
    @Select("select eid,username,password,email,department,level from edmers")
    List<Edmer> findAllEdmer();
}
