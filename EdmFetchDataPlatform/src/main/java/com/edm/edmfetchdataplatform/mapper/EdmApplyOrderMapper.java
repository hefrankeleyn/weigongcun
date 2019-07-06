package com.edm.edmfetchdataplatform.mapper;

import com.edm.edmfetchdataplatform.base.query.EdmApplyOrderQuery;
import com.edm.edmfetchdataplatform.domain.EdmApplyOrder;
import com.edm.edmfetchdataplatform.domain.EdmApplyOrderCheckResult;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 操作 edmApplyOrder
 *
 * @Date 2019-06-20
 * @Author lifei
 */
@Mapper
public interface EdmApplyOrderMapper {

    /**
     * 保存 edmApplyOrder
     *
     * @param edmApplyOrder
     */
    @Insert("INSERT INTO edm_apply_examine_order (`oid`,`order_name`,`eid`,`apply_date`,`qunfa_type_description`," +
            "`qunfa_subject_and_context`,`paiqi_yixiang`,`target_send_province`,`user_conditions`,`send_num`," +
            "`channel_sends`,`how_supplement`,`message_context`,`order_state`,`ocid`) " +
            "VALUES (#{oid},#{orderName},#{edmer.eid},#{applyDate},#{qunFaTypeDescription}," +
            "#{qunFaSubjectAndContext},#{paiQiYiXiang},#{targetSendProvince},#{userConditions}," +
            "#{sendNum},#{channelSends},#{howSupplement},#{messageContext},#{orderState},#{edmApplyOrderCheckResult.ocId})")
    void saveEdmApplyOrder(EdmApplyOrder edmApplyOrder);


    /**
     * 根据订单号查询EdmApplyOrder
     *
     * @param oid
     * @return
     */
    @Select("SELECT `oid`,`order_name`,`eid`,`apply_date`,`qunfa_type_description`,`qunfa_subject_and_context`," +
            "`paiqi_yixiang`,`target_send_province`,`user_conditions`,`send_num`,`channel_sends`,`how_supplement`," +
            "`message_context`,`order_state`,`ocid` FROM `edm_apply_examine_order` " +
            "where 1=1 and oid=#{oid}")
    @Results(value = {@Result(column = "oid", property = "oid"),
            @Result(column = "order_name", property = "orderName"),
            @Result(column = "apply_date", property = "applyDate", javaType = java.util.Date.class),
            @Result(column = "qunfa_type_description", property = "qunFaTypeDescription"),
            @Result(column = "qunfa_subject_and_context", property = "qunFaSubjectAndContext"),
            @Result(column = "paiqi_yixiang", property = "paiQiYiXiang"),
            @Result(column = "target_send_province", property = "targetSendProvince"),
            @Result(column = "user_conditions", property = "userConditions"),
            @Result(column = "send_num", property = "sendNum"),
            @Result(column = "channel_sends", property = "channelSends"),
            @Result(column = "how_supplement", property = "howSupplement"),
            @Result(column = "message_context", property = "messageContext"),
            @Result(column = "order_state", property = "orderState"),
            @Result(property = "edmApplyFiles", column = "oid", javaType = List.class,
                    many = @Many(select = "com.edm.edmfetchdataplatform.mapper.EdmApplyFileMapper.findEdmOrderFilesByOid")),
            @Result(property = "edmer", column = "eid",
                    one = @One(select = "com.edm.edmfetchdataplatform.mapper.EdmerMapper.findEdmerByEid")),
            @Result(property = "edmApplyOrderCheckResult", column = "ocid",
                    one = @One(select = "com.edm.edmfetchdataplatform.mapper.EdmApplyOrderCheckResultMapper.findEdmApplyOrderCheckResultByOcid"))
    })
    EdmApplyOrder findEdmApplyOrderByOid(String oid);


    /**
     * 根据用户id查询 该用户申请的流转单
     *
     * @param eid
     * @return
     */
    @Select("SELECT `oid`,`order_name`,`eid`,`apply_date`,`qunfa_type_description`,`qunfa_subject_and_context`," +
            "`paiqi_yixiang`,`target_send_province`,`user_conditions`,`send_num`,`channel_sends`,`how_supplement`," +
            "`message_context`,`order_state`,`ocid` FROM `edm_apply_examine_order` " +
            "where 1=1 and eid=#{eid} order by apply_date desc")
    @Results(value = {@Result(column = "oid", property = "oid"),
            @Result(column = "order_name", property = "orderName"),
            @Result(column = "apply_date", property = "applyDate", javaType = java.util.Date.class),
            @Result(column = "qunfa_type_description", property = "qunFaTypeDescription"),
            @Result(column = "qunfa_subject_and_context", property = "qunFaSubjectAndContext"),
            @Result(column = "paiqi_yixiang", property = "paiQiYiXiang"),
            @Result(column = "target_send_province", property = "targetSendProvince"),
            @Result(column = "user_conditions", property = "userConditions"),
            @Result(column = "send_num", property = "sendNum"),
            @Result(column = "channel_sends", property = "channelSends"),
            @Result(column = "how_supplement", property = "howSupplement"),
            @Result(column = "message_context", property = "messageContext"),
            @Result(column = "order_state", property = "orderState"),
            @Result(property = "edmApplyFiles", column = "oid", javaType = List.class,
                    many = @Many(select = "com.edm.edmfetchdataplatform.mapper.EdmApplyFileMapper.findEdmOrderFilesByOid")),
            @Result(property = "edmer", column = "eid",
                    one = @One(select = "com.edm.edmfetchdataplatform.mapper.EdmerMapper.findEdmerByEid")),
            @Result(property = "edmApplyOrderCheckResult", column = "ocid",
                    one = @One(select = "com.edm.edmfetchdataplatform.mapper.EdmApplyOrderCheckResultMapper.findEdmApplyOrderCheckResultByOcid"))
    })
    List<EdmApplyOrder> findEdmApplyOrdersByEid(Integer eid);


    /**
     * 查询一页EdmApplyOrder
     *
     * @param eid            申请者id
     * @param beginItemIndex 开始的索引, 从0 开始，最大为总条数减去1
     * @param pageSize       一页的条数
     * @return
     */
    @Select("SELECT `oid`,`order_name`,`eid`,`apply_date`,`qunfa_type_description`,`qunfa_subject_and_context`," +
            "`paiqi_yixiang`,`target_send_province`,`user_conditions`,`send_num`,`channel_sends`,`how_supplement`," +
            "`message_context`,`order_state`,`ocid` FROM `edm_apply_examine_order` " +
            "where 1=1 and eid=#{eid} order by apply_date desc limit #{beginItemIndex},#{pageSize}")
    @Results(value = {@Result(column = "oid", property = "oid"),
            @Result(column = "order_name", property = "orderName"),
            @Result(column = "apply_date", property = "applyDate", javaType = java.util.Date.class),
            @Result(column = "qunfa_type_description", property = "qunFaTypeDescription"),
            @Result(column = "qunfa_subject_and_context", property = "qunFaSubjectAndContext"),
            @Result(column = "paiqi_yixiang", property = "paiQiYiXiang"),
            @Result(column = "target_send_province", property = "targetSendProvince"),
            @Result(column = "user_conditions", property = "userConditions"),
            @Result(column = "send_num", property = "sendNum"),
            @Result(column = "channel_sends", property = "channelSends"),
            @Result(column = "how_supplement", property = "howSupplement"),
            @Result(column = "message_context", property = "messageContext"),
            @Result(column = "order_state", property = "orderState"),
            @Result(property = "edmApplyFiles", column = "oid", javaType = List.class,
                    many = @Many(select = "com.edm.edmfetchdataplatform.mapper.EdmApplyFileMapper.findEdmOrderFilesByOid")),
            @Result(property = "edmer", column = "eid",
                    one = @One(select = "com.edm.edmfetchdataplatform.mapper.EdmerMapper.findEdmerByEid")),
            @Result(property = "edmApplyOrderCheckResult", column = "ocid",
                    one = @One(select = "com.edm.edmfetchdataplatform.mapper.EdmApplyOrderCheckResultMapper.findEdmApplyOrderCheckResultByOcid"))
    })
    List<EdmApplyOrder> findPageEdmApplyOrdersByEid(Integer eid, Integer beginItemIndex, Integer pageSize);

    /**
     * 查询eid的条数
     *
     * @param eid
     * @return
     */
    @Select("SELECT count(oid) FROM `edm_apply_examine_order` " +
            "where 1=1 and eid=#{eid}")
    Integer countEdmApplyOrdersByEid(Integer eid);


    /**
     * 根据流转的状态查询流转单的数量
     *
     * @param edmApplyOrderQuery
     * @return
     */
    @Select({"<script>",
            "SELECT count(oid) FROM `edm_apply_examine_order` ",
            "where 1=1 ",
            "<if test='eid != null'>",
            " and eid=#{eid}",
            "</if>",
            "<if test='orderStates != null'>",
            "and order_state in ",
            "<foreach item='item' index='index' collection='orderStates' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</if>",
            "</script>"})
    Integer countEdmApplyOrdersByEdmApplyOrderQuery(EdmApplyOrderQuery edmApplyOrderQuery);

    /**
     * 根据流转单的状态查询流转单列表
     *
     * @param edmApplyOrderQuery
     * @return
     */
    @Select({"<script>",
            "SELECT `oid`,`order_name`,`eid`,`apply_date`,`qunfa_type_description`,`qunfa_subject_and_context`," +
                    "`paiqi_yixiang`,`target_send_province`,`user_conditions`,`send_num`,`channel_sends`,`how_supplement`," +
                    "`message_context`,`order_state`,`ocid` FROM `edm_apply_examine_order` ",
            "where 1=1 ",
            "<if test='edmApplyOrderQuery.eid != null'>",
            " and eid=#{edmApplyOrderQuery.eid}",
            "</if>",
            "<if test='edmApplyOrderQuery.orderStates != null'>",
            "and order_state in ",
            "<foreach item='item' index='index' collection='edmApplyOrderQuery.orderStates' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</if>",
            " order by apply_date desc limit #{beginItemIndex},#{pageSize}",
            "</script>"})

    @Results(value = {@Result(column = "oid", property = "oid"),
            @Result(column = "order_name", property = "orderName"),
            @Result(column = "apply_date", property = "applyDate", javaType = java.util.Date.class),
            @Result(column = "qunfa_type_description", property = "qunFaTypeDescription"),
            @Result(column = "qunfa_subject_and_context", property = "qunFaSubjectAndContext"),
            @Result(column = "paiqi_yixiang", property = "paiQiYiXiang"),
            @Result(column = "target_send_province", property = "targetSendProvince"),
            @Result(column = "user_conditions", property = "userConditions"),
            @Result(column = "send_num", property = "sendNum"),
            @Result(column = "channel_sends", property = "channelSends"),
            @Result(column = "how_supplement", property = "howSupplement"),
            @Result(column = "message_context", property = "messageContext"),
            @Result(column = "order_state", property = "orderState"),
            @Result(property = "edmApplyFiles", column = "oid", javaType = List.class,
                    many = @Many(select = "com.edm.edmfetchdataplatform.mapper.EdmApplyFileMapper.findEdmOrderFilesByOid")),
            @Result(property = "edmer", column = "eid",
                    one = @One(select = "com.edm.edmfetchdataplatform.mapper.EdmerMapper.findEdmerByEid")),
            @Result(property = "edmApplyOrderCheckResult", column = "ocid",
                    one = @One(select = "com.edm.edmfetchdataplatform.mapper.EdmApplyOrderCheckResultMapper.findEdmApplyOrderCheckResultByOcid"))
    })
    List<EdmApplyOrder> findPageEdmApplyOrdersByEdmApplyOrderQuery(EdmApplyOrderQuery edmApplyOrderQuery, Integer beginItemIndex, Integer pageSize);


}
