package com.edm.edmfetchdataplatform.domain;

import com.edm.edmfetchdataplatform.domain.status.QunFaTypeFactory;
import com.edm.edmfetchdataplatform.domain.status.UsersSupplementStrategyFactory;
import com.edm.edmfetchdataplatform.tools.MyArrayUtil;

import java.util.Arrays;
import java.util.Date;

/**
 * EDM 申请单
 * @Date 2019-06-17
 * @Author lifei
 */
public class EdmApplyOrder {

    /**
     * 申请编号
     */
    private String oid;

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
    private String quanFaTypeDescription;
    private Integer[] quanFaTypeStates;

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
    private String[] sendRoles;
    private String rolesSend;

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
     * 附件
     */
    private String[] edmFilesName;

    /**
     * 附件路径
     */
    private String[] edmFilesPath;

    /**
     * 申请项的Id
     */
    private Integer[] conIds;

    /**
     * 订单的状态
     */
    private Integer orderState;

    public String getQuanFaTypeDescription() {
        return quanFaTypeDescription;
    }

    public void setQuanFaTypeDescription(String quanFaTypeDescription) {
        this.quanFaTypeDescription = quanFaTypeDescription;

    }

    public Integer[] getQuanFaTypeStates() {
        return quanFaTypeStates;
    }

    public String[] getSendRoles() {
        return sendRoles;
    }

    public void setSendRoles(String[] sendRoles) {
        this.sendRoles = sendRoles;
        // 写到rolesSend中
        setRolesSend(MyArrayUtil.arrayToStr(sendRoles));
    }

    public String getRolesSend() {
        return rolesSend;
    }

    public void setRolesSend(String rolesSend) {
        this.rolesSend = rolesSend;
    }

    public void setQuanFaTypeStates(Integer[] quanFaTypeStates) {
        this.quanFaTypeStates = quanFaTypeStates;
        // 设置群发类型
        if (quanFaTypeStates !=null && quanFaTypeStates.length>0){
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<quanFaTypeStates.length; i++) {
                sb.append(QunFaTypeFactory.fetchQunFaTypeByTypeState(quanFaTypeStates[i]));
            }
            setQuanFaTypeDescription(sb.toString());
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

    public String[] getEdmFilesName() {
        return edmFilesName;
    }

    public void setEdmFilesName(String[] edmFilesName) {
        this.edmFilesName = edmFilesName;
    }

    public String[] getEdmFilesPath() {
        return edmFilesPath;
    }

    public void setEdmFilesPath(String[] edmFilesPath) {
        this.edmFilesPath = edmFilesPath;
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

    @Override
    public String toString() {
        return "EdmApplyOrder{" +
                "oid='" + oid + '\'' +
                ", edmerDepartment='" + edmerDepartment + '\'' +
                ", edmUserName='" + edmUserName + '\'' +
                ", applyDate=" + applyDate +
                ", quanFaTypeDescription='" + quanFaTypeDescription + '\'' +
                ", quanFaTypeStates=" + Arrays.toString(quanFaTypeStates) +
                ", qunFaSubjectAndContext='" + qunFaSubjectAndContext + '\'' +
                ", paiQiYiXiang='" + paiQiYiXiang + '\'' +
                ", targetSendProvince='" + targetSendProvince + '\'' +
                ", userConditions='" + userConditions + '\'' +
                ", sendNum=" + sendNum +
                ", sendRoles=" + Arrays.toString(sendRoles) +
                ", rolesSend='" + rolesSend + '\'' +
                ", howSupplementStatus=" + howSupplementStatus +
                ", howSupplement='" + howSupplement + '\'' +
                ", messageContext='" + messageContext + '\'' +
                ", edmFilesName=" + Arrays.toString(edmFilesName) +
                ", edmFilesPath=" + Arrays.toString(edmFilesPath) +
                ", conIds=" + Arrays.toString(conIds) +
                ", orderState=" + orderState +
                '}';
    }
}
