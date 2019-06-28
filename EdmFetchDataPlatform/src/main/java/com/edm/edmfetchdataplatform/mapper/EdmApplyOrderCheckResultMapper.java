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
     * @param edmApplyOrderCheckResult
     */
    @Insert("INSERT INTO `edm_order_result` (`ocid`,`first_checker`,`first_result`,`second_checker`,`second_result`," +
            "`paiqiyixiang`,`third_checker`,`paiqi_result`,`qunfafangan_result`,`third_beizhu`,`shuju_username`," +
            "`shuju_email`,`datacode`,`datares_sheet`,`datausers_des`,`actual_usernum`,`update_time`,`oid`) " +
            "VALUES (#{ocId},#{firstCheckerUserName},#{firstCheckerResult},#{secondCheckerUserName}," +
            "#{secondCheckerResult},#{paiQiResult},#{thirdCheckerUserName},#{thirdCheckerPaiQiResult}," +
            "#{thirdCheckerQunFaFangAnResult},#{thirdCheckBeiZhu},#{shuJuUserName},#{shuJuEmail},#{dataCode}," +
            "#{fetchResultSheetName},#{dataUsersDescription},#{actualUserNum},#{updateTime},#{oid})")
    void saveEdmApplyOrderCheckResult(EdmApplyOrderCheckResult edmApplyOrderCheckResult);


    /**
     * 根据oid查询 EdmApplyOrderCheckResult
     * @param oid
     * @return
     */
    @Select("SELECT `ocid`,`first_checker`,`first_result`,`second_checker`,`second_result`,`paiqiyixiang`," +
            "`third_checker`,`paiqi_result`,`qunfafangan_result`,`third_beizhu`,`shuju_username`,`shuju_email`," +
            "`datacode`,`datares_sheet`,`datausers_des`,`actual_usernum`,`update_time`,`oid` " +
            "FROM `edm_order_result` where oid=#{oid}")
    @Results(value = {@Result(column = "ocid", property = "ocId"),
            @Result(column = "first_checker", property = "firstCheckerUserName"),
            @Result(column = "first_result", property = "firstCheckerResult"),
            @Result(column = "second_checker", property = "secondCheckerUserName"),
            @Result(column = "second_result", property = "secondCheckerResult"),
            @Result(column = "paiqiyixiang", property = "paiQiResult"),
            @Result(column = "third_checker", property = "thirdCheckerUserName"),
            @Result(column = "paiqi_result", property = "thirdCheckerPaiQiResult"),
            @Result(column = "qunfafangan_result", property = "thirdCheckerQunFaFangAnResult"),
            @Result(column = "third_beizhu", property = "thirdCheckBeiZhu"),
            @Result(column = "shuju_username", property = "shuJuUserName"),
            @Result(column = "shuju_email", property = "shuJuEmail"),
            @Result(column = "datacode", property = "dataCode"),
            @Result(column = "datares_sheet", property = "fetchResultSheetName"),
            @Result(column = "datausers_des", property = "dataUsersDescription"),
            @Result(column = "actual_usernum", property = "actualUserNum"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "oid", property = "oid"),
    })
    EdmApplyOrderCheckResult findEdmApplyOrderCheckResultByOid(String oid);


    /**
     * 根据ocId 查询 流转单的结果
     * @param ocId
     * @return
     */
    @Select("SELECT `ocid`,`first_checker`,`first_result`,`second_checker`,`second_result`,`paiqiyixiang`," +
            "`third_checker`,`paiqi_result`,`qunfafangan_result`,`third_beizhu`,`shuju_username`,`shuju_email`," +
            "`datacode`,`datares_sheet`,`datausers_des`,`actual_usernum`,`update_time`,`oid` " +
            "FROM `edm_order_result` where ocid=#{ocId}")
    @Results(value = {@Result(column = "ocid", property = "ocId"),
            @Result(column = "first_checker", property = "firstCheckerUserName"),
            @Result(column = "first_result", property = "firstCheckerResult"),
            @Result(column = "second_checker", property = "secondCheckerUserName"),
            @Result(column = "second_result", property = "secondCheckerResult"),
            @Result(column = "paiqiyixiang", property = "paiQiResult"),
            @Result(column = "third_checker", property = "thirdCheckerUserName"),
            @Result(column = "paiqi_result", property = "thirdCheckerPaiQiResult"),
            @Result(column = "qunfafangan_result", property = "thirdCheckerQunFaFangAnResult"),
            @Result(column = "third_beizhu", property = "thirdCheckBeiZhu"),
            @Result(column = "shuju_username", property = "shuJuUserName"),
            @Result(column = "shuju_email", property = "shuJuEmail"),
            @Result(column = "datacode", property = "dataCode"),
            @Result(column = "datares_sheet", property = "fetchResultSheetName"),
            @Result(column = "datausers_des", property = "dataUsersDescription"),
            @Result(column = "actual_usernum", property = "actualUserNum"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "oid", property = "oid"),
    })
    EdmApplyOrderCheckResult findEdmApplyOrderCheckResultByOcid(String ocId);



}
