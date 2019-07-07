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

    // 能力组审核的状态
    private String capacityCheckStatue;

    // 排期结果
    private String paiQiResult;

    // 排期确认的结果
    private Integer paiQiQueRenStatue;

    // 群发方案确认
    private Integer qunFaFangAnQueRenState;

    /**
     * 第三位核查者，对排期的备注
     */
    private String thirdCheckBeiZhu;

    /**
     * 数据编码
     */
    private String dataCode;

    /**
     * 用户属性说明
     */
    private String dataUsersDescription;

    /**
     * 实际的用户量
     */
    private String actualUserNum;

    /**
     * 最后的备注
     */
    private String endBeiZhu;

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

    public String getCapacityCheckStatue() {
        return capacityCheckStatue;
    }

    public void setCapacityCheckStatue(String capacityCheckStatue) {
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

    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }

    public String getDataUsersDescription() {
        return dataUsersDescription;
    }

    public void setDataUsersDescription(String dataUsersDescription) {
        this.dataUsersDescription = dataUsersDescription;
    }

    public String getActualUserNum() {
        return actualUserNum;
    }

    public void setActualUserNum(String actualUserNum) {
        this.actualUserNum = actualUserNum;
    }

    public String getEndBeiZhu() {
        return endBeiZhu;
    }

    public void setEndBeiZhu(String endBeiZhu) {
        this.endBeiZhu = endBeiZhu;
    }

    @Override
    public String toString() {
        return "EdmApplyOrderResultQuery{" +
                "oid='" + oid + '\'' +
                ", orderState=" + orderState +
                ", firstCheckerUserName='" + firstCheckerUserName + '\'' +
                ", capacityCheckStatue='" + capacityCheckStatue + '\'' +
                ", paiQiResult='" + paiQiResult + '\'' +
                ", paiQiQueRenStatue=" + paiQiQueRenStatue +
                ", qunFaFangAnQueRenState=" + qunFaFangAnQueRenState +
                ", thirdCheckBeiZhu='" + thirdCheckBeiZhu + '\'' +
                ", dataCode='" + dataCode + '\'' +
                ", dataUsersDescription='" + dataUsersDescription + '\'' +
                ", actualUserNum='" + actualUserNum + '\'' +
                ", endBeiZhu='" + endBeiZhu + '\'' +
                '}';
    }
}
