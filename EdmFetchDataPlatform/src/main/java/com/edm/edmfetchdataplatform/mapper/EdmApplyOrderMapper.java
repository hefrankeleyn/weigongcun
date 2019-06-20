package com.edm.edmfetchdataplatform.mapper;

import com.edm.edmfetchdataplatform.domain.EdmApplyOrder;
import com.edm.edmfetchdataplatform.domain.EdmApplyOrderAndItemRelation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作 edmApplyOrder
 * @Date 2019-06-20
 * @Author lifei
 */
@Mapper
public interface EdmApplyOrderMapper {

    /*
    @Select("select eid,username,password,email,department from edmers where eid = #{eid}")
    Edmer findEdmerByEid(@Param(value = "eid") Long eid);
     */

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
     * 保存 EdmApplyOrderAndItemRelation
     * @param edmApplyOrderAndItemRelation
     */
    @Insert("INSERT INTO edm_order_and_item (oid,conid) VALUES(#{oid},#{conId})")
    void saveEdmOrderAndItem(EdmApplyOrderAndItemRelation edmApplyOrderAndItemRelation);
}
