package com.edm.edmfetchdataplatform.domain;

import com.edm.edmfetchdataplatform.domain.status.CheckResultStatue;

import java.io.Serializable;
import java.util.Date;

/**
 * EDM 流转单 审核结果
 * @Date 2019-06-27
 * @Author lifei
 */
public class EdmApplyOrderCheckResult implements Serializable {

    /**
     * 流转单审核结果的id
     * UUID
     */
    private String ocId;

    /**
     * 群发流转单初审人姓名
     * 申请组初审
     */
    private String firstCheckerUserName;

    /**
     * 初审的状态
     */
    private CheckResultStatue firstCheckerResultStatus;
    /**
     * 初审的结果
     */
    private String firstCheckerResult;

    /**
     *
     * 能力组审核
     */
    private String secondCheckerUserName;

    /**
     * 第二次审核的状态
     */
    private CheckResultStatue secondCheckerResultStatus;
    /**
     * 第二次审核结果
     */
    private String secondCheckerResult;

    // 能力组审核的结果状态
    private Integer capacityCheckStatue;
    /**
     * 排期结果
     */
    private String paiQiResult;


    /**
     * 客服组核查的状态
     */
    private String thirdCheckerUserName;

    /**
     * 第三位核查排期确认结果
     */
    private CheckResultStatue thirdCheckerPaiQiResultStatue;
    private String thirdCheckerPaiQiResult;

    // 排期确认
    private Integer paiQiQueRenStatue;

    /**
     * 第三位核查者，对群发方案确认的结果
     */
    private CheckResultStatue thirdCheckerQunFaFangAnResultStatue;
    private String thirdCheckerQunFaFangAnResult;

    //群发方案确认
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
    private String dataCode;

    /**
     * 数据提取结果 附属sheet
     */
    private String fetchResultSheetName;

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
    private String ednBeiZhu;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 流转单的id
     */
    private String oid;


    /**
     * 对属性进行初始化
     */
    public EdmApplyOrderCheckResult() {
        this.firstCheckerUserName="建伟";
        this.secondCheckerUserName="葛兴羽";
        this.thirdCheckerUserName="梁南";
        this.thirdCheckBeiZhu = "请剔除黑名单用户和省分";
        this.shuJuUserName = "数据";
        this.shuJuEmail = "shuju@wo.cn";
        this.firstCheckerResult = "";
        this.secondCheckerResult = "";
        this.thirdCheckerPaiQiResult = "";
        this.thirdCheckerQunFaFangAnResult = "";
        this.dataCode = "";
        this.fetchResultSheetName = "";
        this.dataUsersDescription = "";
        this.actualUserNum = "";
        this.updateTime = new Date();
        this.ednBeiZhu = "";

    }

    public String getOcId() {
        return ocId;
    }

    public void setOcId(String ocId) {
        this.ocId = ocId;
    }

    public String getFirstCheckerUserName() {
        return firstCheckerUserName;
    }

    public void setFirstCheckerUserName(String firstCheckerUserName) {
        this.firstCheckerUserName = firstCheckerUserName;
    }

    public CheckResultStatue getFirstCheckerResultStatus() {
        return firstCheckerResultStatus;
    }

    public void setFirstCheckerResultStatus(CheckResultStatue firstCheckerResultStatus) {
        this.firstCheckerResultStatus = firstCheckerResultStatus;
        if (firstCheckerResultStatus !=null){
            this.firstCheckerResult = firstCheckerResultStatus.getDesc();
        }
    }

    public String getFirstCheckerResult() {
        return firstCheckerResult;
    }

    public String getSecondCheckerUserName() {
        return secondCheckerUserName;
    }

    public void setSecondCheckerUserName(String secondCheckerUserName) {
        this.secondCheckerUserName = secondCheckerUserName;
    }

    public CheckResultStatue getSecondCheckerResultStatus() {
        return secondCheckerResultStatus;
    }

    public void setSecondCheckerResultStatus(CheckResultStatue secondCheckerResultStatus) {
        this.secondCheckerResultStatus = secondCheckerResultStatus;
        if (secondCheckerResultStatus !=null){
            this.secondCheckerResult = secondCheckerResultStatus.getDesc();
        }
    }

    public String getSecondCheckerResult() {
        return secondCheckerResult;
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

    public void setFirstCheckerResult(String firstCheckerResult) {
        this.firstCheckerResult = firstCheckerResult;
    }

    public void setSecondCheckerResult(String secondCheckerResult) {
        this.secondCheckerResult = secondCheckerResult;
    }

    public String getThirdCheckerUserName() {
        return thirdCheckerUserName;
    }

    public void setThirdCheckerUserName(String thirdCheckerUserName) {
        this.thirdCheckerUserName = thirdCheckerUserName;
    }

    public CheckResultStatue getThirdCheckerPaiQiResultStatue() {
        return thirdCheckerPaiQiResultStatue;
    }

    public void setThirdCheckerPaiQiResultStatue(CheckResultStatue thirdCheckerPaiQiResultStatue) {
        this.thirdCheckerPaiQiResultStatue = thirdCheckerPaiQiResultStatue;
        if (thirdCheckerPaiQiResultStatue != null){
            this.thirdCheckerPaiQiResult = thirdCheckerPaiQiResultStatue.getDesc();
        }
    }

    public String getThirdCheckerPaiQiResult() {
        return thirdCheckerPaiQiResult;
    }

    public CheckResultStatue getThirdCheckerQunFaFangAnResultStatue() {
        return thirdCheckerQunFaFangAnResultStatue;
    }

    public void setThirdCheckerQunFaFangAnResultStatue(CheckResultStatue thirdCheckerQunFaFangAnResultStatue) {
        this.thirdCheckerQunFaFangAnResultStatue = thirdCheckerQunFaFangAnResultStatue;
        if (thirdCheckerQunFaFangAnResultStatue != null){
            this.thirdCheckerQunFaFangAnResult = thirdCheckerQunFaFangAnResultStatue.getDesc();
        }
    }

    public String getThirdCheckerQunFaFangAnResult() {
        return thirdCheckerQunFaFangAnResult;
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

    public String getFetchResultSheetName() {
        return fetchResultSheetName;
    }

    public void setFetchResultSheetName(String fetchResultSheetName) {
        this.fetchResultSheetName = fetchResultSheetName;
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

    public void setThirdCheckerPaiQiResult(String thirdCheckerPaiQiResult) {
        this.thirdCheckerPaiQiResult = thirdCheckerPaiQiResult;
    }

    public void setThirdCheckerQunFaFangAnResult(String thirdCheckerQunFaFangAnResult) {
        this.thirdCheckerQunFaFangAnResult = thirdCheckerQunFaFangAnResult;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
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

    public String getEdnBeiZhu() {
        return ednBeiZhu;
    }

    public void setEdnBeiZhu(String ednBeiZhu) {
        this.ednBeiZhu = ednBeiZhu;
    }

    @Override
    public String toString() {
        return "EdmApplyOrderCheckResult{" +
                "ocId='" + ocId + '\'' +
                ", firstCheckerUserName='" + firstCheckerUserName + '\'' +
                ", firstCheckerResultStatus=" + firstCheckerResultStatus +
                ", firstCheckerResult='" + firstCheckerResult + '\'' +
                ", secondCheckerUserName='" + secondCheckerUserName + '\'' +
                ", secondCheckerResultStatus=" + secondCheckerResultStatus +
                ", secondCheckerResult='" + secondCheckerResult + '\'' +
                ", paiQiResult='" + paiQiResult + '\'' +
                ", thirdCheckerUserName='" + thirdCheckerUserName + '\'' +
                ", thirdCheckerPaiQiResultStatue=" + thirdCheckerPaiQiResultStatue +
                ", thirdCheckerPaiQiResult='" + thirdCheckerPaiQiResult + '\'' +
                ", thirdCheckerQunFaFangAnResultStatue=" + thirdCheckerQunFaFangAnResultStatue +
                ", thirdCheckerQunFaFangAnResult='" + thirdCheckerQunFaFangAnResult + '\'' +
                ", shuJuUserName='" + shuJuUserName + '\'' +
                ", shuJuEmail='" + shuJuEmail + '\'' +
                ", dataCode='" + dataCode + '\'' +
                ", fetchResultSheetName='" + fetchResultSheetName + '\'' +
                ", dataUsersDescription='" + dataUsersDescription + '\'' +
                ", actualUserNum='" + actualUserNum + '\'' +
                ", thirdCheckBeiZhu='" + thirdCheckBeiZhu + '\'' +
                ", updateTime=" + updateTime +
                ", oid='" + oid + '\'' +
                '}';
    }
}
