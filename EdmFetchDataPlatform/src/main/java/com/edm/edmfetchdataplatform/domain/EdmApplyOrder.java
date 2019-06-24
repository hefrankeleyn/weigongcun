package com.edm.edmfetchdataplatform.domain;

import com.edm.edmfetchdataplatform.domain.status.QunFaTypeFactory;
import com.edm.edmfetchdataplatform.domain.status.UsersSupplementStrategyFactory;
import com.edm.edmfetchdataplatform.tools.MyArrayUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * EDM 申请 流转单
 * @Date 2019-06-17
 * @Author lifei
 */
public class EdmApplyOrder {

    /**
     * 申请编号
     */
    private String oid;

    /**
     * 流转单的名称
     */
    private String orderName;
    /**
     * 流转单的发起人 id
     */
    private Integer eid;

    /**
     * 申请单的组别
     */
    private String edmerDepartment;

    /**
     * edm申请人
     */
    private String edmUserName;
    /**
     * 申请时间
     */
    private Date applyDate;

    /**
     *  群发类型及群发方式
     */
    private String qunFaTypeDescription;
    private Integer[] qunFaTypeStates;

    /**
     * 群发主题及短信内容
     */
    private String qunFaSubjectAndContext;

    /**
     * 排期意向
     */
    private String paiQiYiXiang;

    /**
     * 目标群发省
     */
    private String targetSendProvince;

    /**
     * 用户数据要求
     */
    private String userConditions;

    /**
     * 发送量
     */
    private Integer sendNum;

    /**
     * 投递通道
     */
    private String[] channels;
    private String channelSends;

    /**
     * 目标用户不足时如何处理,标识
     */
    private Integer howSupplementStatus;
    private String howSupplement;

    /**
     * 短信内容
     */
    private String messageContext;

    /**
     * 申请项的Id
     */
    private Integer[] conIds;

    /**
     * 订单的状态
     */
    private Integer orderState;


    /**
     * 群发流转单的附件
     */
    private List<EdmApplyFile> edmApplyFiles;

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getQunFaTypeDescription() {
        return qunFaTypeDescription;
    }

    public void setQunFaTypeDescription(String qunFaTypeDescription) {
        this.qunFaTypeDescription = qunFaTypeDescription;

    }

    public Integer[] getQunFaTypeStates() {
        return qunFaTypeStates;
    }

    public String[] getChannels() {
        return channels;
    }

    public void setChannels(String[] channels) {
        this.channels = channels;
        // 写到rolesSend中
        setChannelSends(MyArrayUtil.arrayToStr(channels));
    }

    public String getChannelSends() {
        return channelSends;
    }

    public void setChannelSends(String channelSends) {
        this.channelSends = channelSends;
    }

    public void setQunFaTypeStates(Integer[] qunFaTypeStates) {
        this.qunFaTypeStates = qunFaTypeStates;
        // 设置群发类型
        if (qunFaTypeStates !=null && qunFaTypeStates.length>0){
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i< qunFaTypeStates.length; i++) {
                sb.append(QunFaTypeFactory.fetchQunFaTypeByTypeState(qunFaTypeStates[i]));
            }
            setQunFaTypeDescription(sb.toString());
        }
    }


    public Integer getHowSupplementStatus() {
        return howSupplementStatus;
    }

    public void setHowSupplementStatus(Integer howSupplementStatus) {
        this.howSupplementStatus = howSupplementStatus;
        // 设置 howSupplement
        setHowSupplement(UsersSupplementStrategyFactory.generateStrategyDescriptionByState(howSupplementStatus));
    }

    public String getHowSupplement() {
        return howSupplement;
    }

    public void setHowSupplement(String howSupplement) {
        this.howSupplement = howSupplement;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getEdmerDepartment() {
        return edmerDepartment;
    }

    public void setEdmerDepartment(String edmerDepartment) {
        this.edmerDepartment = edmerDepartment;
    }

    public String getEdmUserName() {
        return edmUserName;
    }

    public void setEdmUserName(String edmUserName) {
        this.edmUserName = edmUserName;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getQunFaSubjectAndContext() {
        return qunFaSubjectAndContext;
    }

    public void setQunFaSubjectAndContext(String qunFaSubjectAndContext) {
        this.qunFaSubjectAndContext = qunFaSubjectAndContext;
    }

    public String getPaiQiYiXiang() {
        return paiQiYiXiang;
    }

    public void setPaiQiYiXiang(String paiQiYiXiang) {
        this.paiQiYiXiang = paiQiYiXiang;
    }

    public String getTargetSendProvince() {
        return targetSendProvince;
    }

    public void setTargetSendProvince(String targetSendProvince) {
        this.targetSendProvince = targetSendProvince;
    }

    public String getUserConditions() {
        return userConditions;
    }

    public void setUserConditions(String userConditions) {
        this.userConditions = userConditions;
    }

    public Integer getSendNum() {
        return sendNum;
    }

    public void setSendNum(Integer sendNum) {
        this.sendNum = sendNum;
    }

    public String getMessageContext() {
        return messageContext;
    }

    public void setMessageContext(String messageContext) {
        this.messageContext = messageContext;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Integer[] getConIds() {
        return conIds;
    }

    public void setConIds(Integer[] conIds) {
        this.conIds = conIds;
    }

    public List<EdmApplyFile> getEdmApplyFiles() {
        return edmApplyFiles;
    }

    public void setEdmApplyFiles(List<EdmApplyFile> edmApplyFiles) {
        this.edmApplyFiles = edmApplyFiles;
    }

    @Override
    public String toString() {
        return "EdmApplyOrder{" +
                "oid='" + oid + '\'' +
                ", orderName='" + orderName + '\'' +
                ", eid=" + eid +
                ", edmerDepartment='" + edmerDepartment + '\'' +
                ", edmUserName='" + edmUserName + '\'' +
                ", applyDate=" + applyDate +
                ", qunFaTypeDescription='" + qunFaTypeDescription + '\'' +
                ", qunFaTypeStates=" + Arrays.toString(qunFaTypeStates) +
                ", qunFaSubjectAndContext='" + qunFaSubjectAndContext + '\'' +
                ", paiQiYiXiang='" + paiQiYiXiang + '\'' +
                ", targetSendProvince='" + targetSendProvince + '\'' +
                ", userConditions='" + userConditions + '\'' +
                ", sendNum=" + sendNum +
                ", channels=" + Arrays.toString(channels) +
                ", channelSends='" + channelSends + '\'' +
                ", howSupplementStatus=" + howSupplementStatus +
                ", howSupplement='" + howSupplement + '\'' +
                ", messageContext='" + messageContext + '\'' +
                ", conIds=" + Arrays.toString(conIds) +
                ", orderState=" + orderState +
                ", edmApplyFiles=" + edmApplyFiles +
                '}';
    }
}
