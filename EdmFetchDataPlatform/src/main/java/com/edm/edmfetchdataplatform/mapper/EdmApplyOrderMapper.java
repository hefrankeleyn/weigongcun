package com.edm.edmfetchdataplatform.mapper;

import com.edm.edmfetchdataplatform.domain.EdmApplyOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

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

}
