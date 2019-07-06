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
                    many = @Many(select = "com.edm.edmfetchdataplatform.mapper.RoleMapper.findRoleByEid"))
    })
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


    /**
     * 查找某一个部门的所有用户
     * @param department
     * @return
     */
    @Select("select eid,username,password,email,department,level from edmers where department=#{department}")
    List<Edmer> findEdmersByOneDepartment(String department);



    /**
     * 根据部门查找edmers
     * @param departments  多个部门
     * @return
     */
    @Select({"<script>",
            "select eid,username,password,email,department,level from edmers ",
            "where department in ",
            "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"})
    List<Edmer> findEdmersByDepartmentArray(@Param("list") String[] departments);

    /**
     * 根据用户权限名称查询用户
     * @param roleName
     * @return
     */
    @Select({"<script>",
            "SELECT b1.`eid`, b1.`username`, b1.`password`, b1.`email`, b1.`department`, b1.`level` ",
            "FROM `edmers` b1 ",
            "inner join (",
            "select t2.eid from edmer_role_relation t2 inner join edm_roles t3 ",
            "on t2.rid = t3.rid ",
            "where 1=1 ",
            "<if test='roleName != null'>",
            "and t3.role_name=#{roleName}",
            "</if>",
            ")b2 on b1.eid=b2.eid",
            "</script>"})
    @Results(value = {@Result(id = true, column = "eid", property = "eid"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "email", property = "email"),
            @Result(column = "department", property = "department"),
            @Result(column = "level", property = "level"),
            @Result(property = "roles", column = "eid", javaType = List.class,
                    many = @Many(select = "com.edm.edmfetchdataplatform.mapper.RoleMapper.findRoleByEid"))
    })
    List<Edmer> findEdmersByRoleName(@Param("roleName") String roleName);
}
