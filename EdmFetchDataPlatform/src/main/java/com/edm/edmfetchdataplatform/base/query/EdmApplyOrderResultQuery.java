package com.edm.edmfetchdataplatform.base.query;

/**
 * @Date 2019-07-07
 * @Author lifei
 */
public class EdmApplyOrderResultQuery {

    // 流转单 id
    private String oid;

    // 流转单的状态
    private Integer orderState;

    // 申请组组长的姓名
    private String firstCheckerUserName;
    private String firstCheckerEmail;

    /**
     *
     * 能力组审核，审核人的姓名
     */
    private String secondCheckerUserName;

    private String secondCheckerEmail;

    // 能力组审核的状态
    private Integer capacityCheckStatue;

    // 排期结果
    private String paiQiResult;

    /**
     * 客服组核查的状态，客服组的姓名
     */
    private String thirdCheckerUserName;
    private String thirdCheckerEmail;

    // 排期确认的结果
    private Integer paiQiQueRenStatue;

    // 群发方案确认
    private Integer qunFaFangAnQueRenState;

    /**
     * 第三位核查者，对排期的备注
     */
    private String thirdCheckBeiZhu;

    /**
     * 数据组
     */
    private String shuJuUserName;
    private String shuJuEmail;

    /**
     * 数据编码
     */
    private String dataCodes;

    /**
     * 用户属性说明
     */
    private String dataUsersDescription;

    /**
     * 实际的用户量
     */
    private Integer actualUserNum;

    /**
     * 最后的备注
     */
    private String endBeiZhu;

    /**
     * 下一位收件人的邮箱
     */
    private String emailTo;

    /**
     * 下一位收件人的姓名
     */
    private String emailToUserName;


    /**
     * 当前正在审核的审核人的邮箱
     */
    private String currentUserEmail;
    /**
     * 当前正在审核的审核人的姓名
     */
    private String currentUserName;


    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public String getFirstCheckerUserName() {
        return firstCheckerUserName;
    }

    public void setFirstCheckerUserName(String firstCheckerUserName) {
        this.firstCheckerUserName = firstCheckerUserName;
    }

    public Integer getCapacityCheckStatue() {
        return capacityCheckStatue;
    }

    public void setCapacityCheckStatue(Integer capacityCheckStatue) {
        this.capacityCheckStatue = capacityCheckStatue;
    }

    public String getPaiQiResult() {
        return paiQiResult;
    }

    public void setPaiQiResult(String paiQiResult) {
        this.paiQiResult = paiQiResult;
    }

    public Integer getPaiQiQueRenStatue() {
        return paiQiQueRenStatue;
    }

    public void setPaiQiQueRenStatue(Integer paiQiQueRenStatue) {
        this.paiQiQueRenStatue = paiQiQueRenStatue;
    }

    public Integer getQunFaFangAnQueRenState() {
        return qunFaFangAnQueRenState;
    }

    public void setQunFaFangAnQueRenState(Integer qunFaFangAnQueRenState) {
        this.qunFaFangAnQueRenState = qunFaFangAnQueRenState;
    }

    public String getThirdCheckBeiZhu() {
        return thirdCheckBeiZhu;
    }

    public void setThirdCheckBeiZhu(String thirdCheckBeiZhu) {
        this.thirdCheckBeiZhu = thirdCheckBeiZhu;
    }

    public String getDataCodes() {
        return dataCodes;
    }

    public void setDataCodes(String dataCodes) {
        this.dataCodes = dataCodes;
    }

    public String getDataUsersDescription() {
        return dataUsersDescription;
    }

    public void setDataUsersDescription(String dataUsersDescription) {
        this.dataUsersDescription = dataUsersDescription;
    }

    public Integer getActualUserNum() {
        return actualUserNum;
    }

    public void setActualUserNum(Integer actualUserNum) {
        this.actualUserNum = actualUserNum;
    }

    public String getEndBeiZhu() {
        return endBeiZhu;
    }

    public void setEndBeiZhu(String endBeiZhu) {
        this.endBeiZhu = endBeiZhu;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getEmailToUserName() {
        return emailToUserName;
    }

    public void setEmailToUserName(String emailToUserName) {
        this.emailToUserName = emailToUserName;
    }

    public String getCurrentUserEmail() {
        return currentUserEmail;
    }

    public void setCurrentUserEmail(String currentUserEmail) {
        this.currentUserEmail = currentUserEmail;
    }

    public String getCurrentUserName() {
        return currentUserName;
    }

    public void setCurrentUserName(String currentUserName) {
        this.currentUserName = currentUserName;
    }

    public String getFirstCheckerEmail() {
        return firstCheckerEmail;
    }

    public void setFirstCheckerEmail(String firstCheckerEmail) {
        this.firstCheckerEmail = firstCheckerEmail;
    }

    public String getSecondCheckerUserName() {
        return secondCheckerUserName;
    }

    public void setSecondCheckerUserName(String secondCheckerUserName) {
        this.secondCheckerUserName = secondCheckerUserName;
    }

    public String getSecondCheckerEmail() {
        return secondCheckerEmail;
    }

    public void setSecondCheckerEmail(String secondCheckerEmail) {
        this.secondCheckerEmail = secondCheckerEmail;
    }

    public String getThirdCheckerUserName() {
        return thirdCheckerUserName;
    }

    public void setThirdCheckerUserName(String thirdCheckerUserName) {
        this.thirdCheckerUserName = thirdCheckerUserName;
    }

    public String getThirdCheckerEmail() {
        return thirdCheckerEmail;
    }

    public void setThirdCheckerEmail(String thirdCheckerEmail) {
        this.thirdCheckerEmail = thirdCheckerEmail;
    }

    public String getShuJuUserName() {
        return shuJuUserName;
    }

    public void setShuJuUserName(String shuJuUserName) {
        this.shuJuUserName = shuJuUserName;
    }

    public String getShuJuEmail() {
        return shuJuEmail;
    }

    public void setShuJuEmail(String shuJuEmail) {
        this.shuJuEmail = shuJuEmail;
    }

    @Override
    public String toString() {
        return "EdmApplyOrderResultQuery{" +
                "oid='" + oid + '\'' +
                ", orderState=" + orderState +
                ", firstCheckerUserName='" + firstCheckerUserName + '\'' +
                ", firstCheckerEmail='" + firstCheckerEmail + '\'' +
                ", secondCheckerUserName='" + secondCheckerUserName + '\'' +
                ", secondCheckerEmail='" + secondCheckerEmail + '\'' +
                ", capacityCheckStatue=" + capacityCheckStatue +
                ", paiQiResult='" + paiQiResult + '\'' +
                ", thirdCheckerUserName='" + thirdCheckerUserName + '\'' +
                ", thirdCheckerEmail='" + thirdCheckerEmail + '\'' +
                ", paiQiQueRenStatue=" + paiQiQueRenStatue +
                ", qunFaFangAnQueRenState=" + qunFaFangAnQueRenState +
                ", thirdCheckBeiZhu='" + thirdCheckBeiZhu + '\'' +
                ", shuJuUserName='" + shuJuUserName + '\'' +
                ", shuJuEmail='" + shuJuEmail + '\'' +
                ", dataCodes='" + dataCodes + '\'' +
                ", dataUsersDescription='" + dataUsersDescription + '\'' +
                ", actualUserNum=" + actualUserNum +
                ", endBeiZhu='" + endBeiZhu + '\'' +
                ", emailTo='" + emailTo + '\'' +
                ", emailToUserName='" + emailToUserName + '\'' +
                ", currentUserEmail='" + currentUserEmail + '\'' +
                ", currentUserName='" + currentUserName + '\'' +
                '}';
    }
}
