package com.edm.edmfetchdataplatform.mapper;

import com.edm.edmfetchdataplatform.domain.EdmApplyOrderCheckResult;
import org.apache.ibatis.annotations.*;

/**
 * @Date 2019-06-27
 * @Author lifei
 */
@Mapper
public interface EdmApplyOrderCheckResultMapper {

    /**
     * 保存流转单的审核结果
     *
     * @param edmApplyOrderCheckResult
     */
    @Insert("INSERT INTO `edm_order_result` (`ocid`,`first_checker`,`firstcheck_status`,`second_checker`,`secondcheck_status`," +
            "`paiqi_result`,`third_checker`,`paiqicheck_status`,`fangancheck_status`,`third_beizhu`,`shuju_username`," +
            "`shuju_email`,`datacode`,`datares_sheet`,`datausers_des`,`actual_usernum`,`end_beizhu`,`update_time`,`oid`) " +
            "VALUES (#{ocId},#{firstCheckerUserName},#{applyGroupCheckStatus},#{secondCheckerUserName}," +
            "#{capacityCheckStatus},#{paiQiResult},#{thirdCheckerUserName},#{paiQiQueRenStatus}," +
            "#{qunFaFangAnQueRenStatus},#{thirdCheckBeiZhu},#{shuJuUserName},#{shuJuEmail},#{dataCode}," +
            "#{fetchResultSheetName},#{dataUsersDescription},#{actualUserNum},#{endBeiZhu},#{updateTime},#{oid})")
    void saveEdmApplyOrderCheckResult(EdmApplyOrderCheckResult edmApplyOrderCheckResult);


    /**
     * 修改流转单的审核结果
     *
     * @param edmApplyOrderCheckResult
     */
    @Update({"<script>",
            "UPDATE `edm_order_result` ",
            "<set>",
            "<if test='firstCheckerUserName != null'>",
            "`first_checker` = #{firstCheckerUserName},",
            "</if>",
            "<if test='applyGroupCheckStatus != null'>",
            "`firstcheck_status` = #{applyGroupCheckStatus},",
            "</if>",
            "<if test='secondCheckerUserName != null'>",
            "`second_checker` = #{secondCheckerUserName},",
            "</if>",
            "<if test='capacityCheckStatus != null'>",
            "`secondcheck_status` = #{capacityCheckStatus},",
            "</if>",
            "<if test='paiQiResult != null'>",
            "`paiqi_result` = #{paiQiResult},",
            "</if>",
            "<if test='thirdCheckerUserName != null'>",
            "`third_checker` = #{thirdCheckerUserName},",
            "</if>",
            "<if test='paiQiQueRenStatus != null'>",
            "`paiqicheck_status` = #{paiQiQueRenStatus},",
            "</if>",
            "<if test='qunFaFangAnQueRenStatus != null'>",
            "`fangancheck_status` = #{qunFaFangAnQueRenStatus},",
            "</if>",
            "<if test='thirdCheckBeiZhu != null'>",
            "`third_beizhu` = #{thirdCheckBeiZhu},",
            "</if>",
            "<if test='shuJuUserName != null'>",
            "`shuju_username` = #{shuJuUserName},",
            "</if>",
            "<if test='shuJuEmail != null'>",
            "`shuju_email` = #{shuJuEmail},",
            "</if>",
            "<if test='dataCode != null'>",
            "`datacode` = #{dataCode},",
            "</if>",
            "<if test='fetchResultSheetName != null'>",
            "`datares_sheet` = #{fetchResultSheetName},",
            "</if>",
            "<if test='dataUsersDescription != null'>",
            "`datausers_des` = #{dataUsersDescription},",
            "</if>",
            "<if test='actualUserNum != null'>",
            "`actual_usernum` = #{actualUserNum},",
            "</if>",
            "<if test='endBeiZhu != null'>",
            "`end_beizhu` = #{endBeiZhu},",
            "</if>",
            "<if test='updateTime != null'>",
            "`update_time` = #{updateTime},",
            "</if>",
            "</set>",
            "WHERE `oid` = #{oid};",
            "</script>"})
    void updateEdmApplyOrderCheckResult(EdmApplyOrderCheckResult edmApplyOrderCheckResult);


    /**
     * 根据oid查询 EdmApplyOrderCheckResult
     *
     * @param oid
     * @return
     */
    @Select("SELECT `ocid`,`first_checker`,`firstcheck_status`,`second_checker`,`secondcheck_status`,`paiqi_result`," +
            "`third_checker`,`paiqicheck_status`,`fangancheck_status`,`third_beizhu`,`shuju_username`,`shuju_email`," +
            "`datacode`,`datares_sheet`,`datausers_des`,`actual_usernum`,`end_beizhu`,`update_time`,`oid` " +
            "FROM `edm_order_result` where oid=#{oid}")
    @Results(value = {@Result(column = "ocid", property = "ocId"),
            @Result(column = "first_checker", property = "firstCheckerUserName"),
            @Result(column = "firstcheck_status", property = "applyGroupCheckStatus"),
            @Result(column = "second_checker", property = "secondCheckerUserName"),
            @Result(column = "secondcheck_status", property = "capacityCheckStatus"),
            @Result(column = "paiqi_result", property = "paiQiResult"),
            @Result(column = "third_checker", property = "thirdCheckerUserName"),
            @Result(column = "paiqicheck_status", property = "paiQiQueRenStatus"),
            @Result(column = "fangancheck_status", property = "qunFaFangAnQueRenStatus"),
            @Result(column = "third_beizhu", property = "thirdCheckBeiZhu"),
            @Result(column = "shuju_username", property = "shuJuUserName"),
            @Result(column = "shuju_email", property = "shuJuEmail"),
            @Result(column = "datacode", property = "dataCode"),
            @Result(column = "datares_sheet", property = "fetchResultSheetName"),
            @Result(column = "datausers_des", property = "dataUsersDescription"),
            @Result(column = "actual_usernum", property = "actualUserNum"),
            @Result(column = "end_beizhu", property = "endBeiZhu"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "oid", property = "oid")
    })
    EdmApplyOrderCheckResult findEdmApplyOrderCheckResultByOid(String oid);


    /**
     * 根据ocId 查询 流转单的结果
     *
     * @param ocId
     * @return
     */
    @Select("SELECT `ocid`,`first_checker`,`firstcheck_status`,`second_checker`,`secondcheck_status`,`paiqi_result`," +
            "`third_checker`,`paiqicheck_status`,`fangancheck_status`,`third_beizhu`,`shuju_username`,`shuju_email`," +
            "`datacode`,`datares_sheet`,`datausers_des`,`actual_usernum`,`end_beizhu`,`update_time`,`oid` " +
            "FROM `edm_order_result` where ocid=#{ocId}")
    @Results(value = {@Result(column = "ocid", property = "ocId"),
            @Result(column = "first_checker", property = "firstCheckerUserName"),
            @Result(column = "firstcheck_status", property = "applyGroupCheckStatus"),
            @Result(column = "second_checker", property = "secondCheckerUserName"),
            @Result(column = "secondcheck_status", property = "capacityCheckStatus"),
            @Result(column = "paiqi_result", property = "paiQiResult"),
            @Result(column = "third_checker", property = "thirdCheckerUserName"),
            @Result(column = "paiqicheck_status", property = "paiQiQueRenStatus"),
            @Result(column = "fangancheck_status", property = "qunFaFangAnQueRenStatus"),
            @Result(column = "third_beizhu", property = "thirdCheckBeiZhu"),
            @Result(column = "shuju_username", property = "shuJuUserName"),
            @Result(column = "shuju_email", property = "shuJuEmail"),
            @Result(column = "datacode", property = "dataCode"),
            @Result(column = "datares_sheet", property = "fetchResultSheetName"),
            @Result(column = "datausers_des", property = "dataUsersDescription"),
            @Result(column = "actual_usernum", property = "actualUserNum"),
            @Result(column = "end_beizhu", property = "endBeiZhu"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "oid", property = "oid")
    })
    EdmApplyOrderCheckResult findEdmApplyOrderCheckResultByOcid(String ocId);


}
