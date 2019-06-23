package com.edm.edmfetchdataplatform.mapper;

import com.edm.edmfetchdataplatform.domain.EdmerRoleRelation;
import com.edm.edmfetchdataplatform.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 权限查询
 * @Date 2019-05-17
 * @Author lifei
 */
@Mapper
public interface RoleMapper {

    @Select("select t1.rid,t1.role_name,t1.role_desc from edm_roles t1 inner join edmer_role_relation t2 " +
            "where 1=1 and t1.rid=t2.rid and t2.eid = #{eid}")
    @Results(value = {@Result(column = "rid", property = "rid"),
                      @Result(column = "role_name", property = "roleName"),
                      @Result(column = "role_desc", property = "roleDesc")
                     })
    List<Role> findRoleByEid(@Param(value = "eid") Integer eid);


    @Insert("INSERT INTO `edmer_role_relation` (`eid`,`rid`) VALUES (#{eid},#{rid})")
    void saveEdmRoleRelation(EdmerRoleRelation edmerRoleRelation);

    /**
     * 根据权限名称查询 Role
     * @param roleName
     * @return
     */
    @Select("select rid,role_name,role_desc from edm_roles where role_name=#{roleName}")
    @Results(value = {@Result(column = "rid", property = "rid"),
            @Result(column = "role_name", property = "roleName"),
            @Result(column = "role_desc", property = "roleDesc")
    })
    Role findRoleByRoleName(String roleName);

    /**
     * 根据rid查询Role
     * @param rid
     * @return
     */
    @Select("select rid,role_name,role_desc from edm_roles where rid=#{rid}")
    @Results(value = {@Result(column = "rid", property = "rid"),
            @Result(column = "role_name", property = "roleName"),
            @Result(column = "role_desc", property = "roleDesc")
    })
    Role findRoleByRid(Integer rid);

    /**
     * 查询所有的权限
     * @return
     */
    @Select("select rid,role_name,role_desc from edm_roles")
    @Results(value = {@Result(column = "rid", property = "rid"),
            @Result(column = "role_name", property = "roleName"),
            @Result(column = "role_desc", property = "roleDesc")
    })
    List<Role> findAllRole();

    /**
     * 根据 删除edmerRoleRelaction
     * @param eid
     */
    @Delete("DELETE FROM `edmer_role_relation` WHERE eid=#{eid}")
    void deleteEdmerRoleRelactionsByEid(Integer eid);
}
