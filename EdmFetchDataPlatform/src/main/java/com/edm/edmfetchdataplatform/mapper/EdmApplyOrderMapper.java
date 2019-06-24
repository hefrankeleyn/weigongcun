package com.edm.edmfetchdataplatform.mapper;

import com.edm.edmfetchdataplatform.domain.EdmApplyOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 操作 edmApplyOrder
 * @Date 2019-06-20
 * @Author lifei
 */
@Mapper
public interface EdmApplyOrderMapper {

    /**
     * 保存 edmApplyOrder
     * @param edmApplyOrder
     */
    @Insert("INSERT INTO edm_apply_examine_order (`oid`,`order_name`,`eid`,`apply_date`,`qunfa_type_description`," +
            "`qunfa_subject_and_context`,`paiqi_yixiang`,`target_send_province`,`user_conditions`,`send_num`," +
            "`channel_sends`,`how_supplement`,`message_context`,`order_state`) " +
            "VALUES (#{oid},#{orderName},#{eid},#{applyDate},#{qunFaTypeDescription}," +
            "#{qunFaSubjectAndContext},#{paiQiYiXiang},#{targetSendProvince},#{userConditions}," +
            "#{sendNum},#{channelSends},#{howSupplement},#{messageContext},#{orderState})")
    void saveEdmApplyOrder(EdmApplyOrder edmApplyOrder);


    /**
     * 根据订单号查询EdmApplyOrder
     * @param oid
     * @return
     */
    @Select("SELECT `oid`,`order_name`,`eid`,`apply_date`,`qunfa_type_description`,`qunfa_subject_and_context`," +
            "`paiqi_yixiang`,`target_send_province`,`user_conditions`,`send_num`,`channel_sends`,`how_supplement`," +
            "`message_context`,`order_state` FROM `edm_apply_examine_order` " +
            "where 1=1 and oid='#{oid}'")
    @Results(value = {@Result(column = "oid", property = "oid"),
            @Result(column = "order_name", property = "orderName"),
            @Result(column = "eid", property = "eid"),
            @Result(column = "apply_date", property = "applyDate", javaType = java.util.Date.class),
            @Result(column = "qunfa_type_description", property = "qunFaTypeDescription"),
            @Result(column = "qunfa_subject_and_context", property = "qunFaSubjectAndContext"),
            @Result(column = "paiqi_yixiang", property = "paiQiYiXiang"),
            @Result(column = "target_send_province", property = "targetSendProvince"),
            @Result(column = "user_conditions", property = "userConditions"),
            @Result(column = "send_num", property = "sendNum"),
            @Result(column = "channel_sends", property = "channelSends"),
            @Result(column = "how_supplement", property = "howSupplement"),
            @Result(property = "edmApplyFiles", column = "oid", javaType = List.class,
                    many = @Many(select = "com.edm.edmfetchdataplatform.mapper.EdmApplyFileMapper.findEdmOrderFilesByOid"))
    })
    EdmApplyOrder findEdmApplyOrderByOid(String oid);


}
