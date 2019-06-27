package com.edm.edmfetchdataplatform.mapper;

import com.edm.edmfetchdataplatform.domain.EdmApplyOrderCheckResult;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

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
}
