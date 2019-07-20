package com.edm.edmfetchdataplatform.domain;

import com.edm.edmfetchdataplatform.domain.status.CheckResultStatueFactory;
import com.edm.edmfetchdataplatform.tools.MyArrayUtil;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * EDM 流转单 审核结果
 * @Date 2019-06-27
 * @Author lifei
 */
public class EdmApplyOrderCheckResult implements Serializable {


    private static final Long serialVersionUID = -1206125L;

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
    private String firstCheckerEmail;

    /**
     * 初审的状态
     * 申请组组长审核的状态
     */
    private Integer applyGroupCheckStatus;
    /**
     * 初审的结果
     */
    private String firstCheckerResult;

    /**
     *
     * 能力组审核，审核人的姓名
     */
    private String secondCheckerUserName;

    private String secondCheckerEmail;

    /**
     * 第二次审核的状态
     */
    private Integer capacityCheckStatus;
    /**
     * 第二次审核结果
     */
    private String secondCheckerResult;

    /**
     * 排期结果
     */
    private String paiQiResult;


    /**
     * 客服组核查的状态，客服组的姓名
     */
    private String thirdCheckerUserName;
    private String thirdCheckerEmail;

    /**
     * 第三位核查排期确认结果，排期的确认结果
     */
    private Integer paiQiQueRenStatus;
    /**
     * 排期的确认结果
     */
    private String thirdCheckerPaiQiResult;


    /**
     * 第三位核查者，对群发方案确认的结果
     */
    /**
     * 群发方案确认 状态
     */
    private Integer qunFaFangAnQueRenStatus;
    /**
     * 群发方案的确认结果
     */
    private String thirdCheckerQunFaFangAnResult;



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
    private String[] dataCodeArray;

    /**
     * 数据编码结果
     */
    private List<EdmTaskResult> edmTaskResults;
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
    private Integer actualUserNum;

    /**
     * 最后的备注
     */
    private String endBeiZhu;

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
        this.dataCodes = "";
        this.fetchResultSheetName = "";
        this.dataUsersDescription = "";
//        this.actualUserNum = null;
        this.updateTime = new Date();
        this.endBeiZhu = "";

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

    public Integer getApplyGroupCheckStatus() {
        return applyGroupCheckStatus;
    }

    /**
     * 同时为 firstCheckerResult 赋值
     * @param applyGroupCheckStatus
     */
    public void setApplyGroupCheckStatus(Integer applyGroupCheckStatus) {
        this.applyGroupCheckStatus = applyGroupCheckStatus;
        if (applyGroupCheckStatus !=null){
            this.firstCheckerResult = CheckResultStatueFactory.getCheckResult(this.applyGroupCheckStatus).getDesc();
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

    public Integer getCapacityCheckStatus() {
        return capacityCheckStatus;
    }

    public void setCapacityCheckStatus(Integer capacityCheckStatus) {
        this.capacityCheckStatus = capacityCheckStatus;
        if (capacityCheckStatus !=null){
            this.secondCheckerResult = CheckResultStatueFactory.getCheckResult(this.applyGroupCheckStatus).getDesc();
        }
    }

    public String getSecondCheckerResult() {
        return secondCheckerResult;
    }


    public String getPaiQiResult() {
        return paiQiResult;
    }

    public void setPaiQiResult(String paiQiResult) {
        this.paiQiResult = paiQiResult;
    }

    public String getThirdCheckerUserName() {
        return thirdCheckerUserName;
    }

    public void setThirdCheckerUserName(String thirdCheckerUserName) {
        this.thirdCheckerUserName = thirdCheckerUserName;
    }

    public Integer getPaiQiQueRenStatus() {
        return paiQiQueRenStatus;
    }

    public void setPaiQiQueRenStatus(Integer paiQiQueRenStatus) {
        this.paiQiQueRenStatus = paiQiQueRenStatus;
        if (paiQiQueRenStatus !=null){
            this.thirdCheckerPaiQiResult = CheckResultStatueFactory.getCheckResult(this.paiQiQueRenStatus).getDesc();
        }
    }

    public String getThirdCheckerPaiQiResult() {
        return thirdCheckerPaiQiResult;
    }


    public Integer getQunFaFangAnQueRenStatus() {
        return qunFaFangAnQueRenStatus;
    }

    public void setQunFaFangAnQueRenStatus(Integer qunFaFangAnQueRenStatus) {
        this.qunFaFangAnQueRenStatus = qunFaFangAnQueRenStatus;
        if (qunFaFangAnQueRenStatus !=null){
            this.thirdCheckerQunFaFangAnResult = CheckResultStatueFactory.getCheckResult(this.qunFaFangAnQueRenStatus).getDesc();
        }
    }

    public String getThirdCheckerQunFaFangAnResult() {
        return thirdCheckerQunFaFangAnResult;
    }


    public String getThirdCheckBeiZhu() {
        return thirdCheckBeiZhu;
    }

    public void setThirdCheckBeiZhu(String thirdCheckBeiZhu) {
        this.thirdCheckBeiZhu = thirdCheckBeiZhu;
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

    public String getDataCodes() {
        return dataCodes;
    }

    public void setDataCodes(String dataCodes) {
        this.dataCodes = dataCodes;
        if (dataCodes!=null && !dataCodes.trim().equals("")){
            this.dataCodeArray = MyArrayUtil.strToArray(dataCodes);
        }
    }

    public String[] getDataCodeArray() {
        return dataCodeArray;
    }

    public void setDataCodeArray(String[] dataCodeArray) {
        this.dataCodeArray = dataCodeArray;
        if (dataCodeArray!=null && dataCodeArray.length>0){
            this.dataCodes = MyArrayUtil.arrayToStr(dataCodeArray);
        }
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


    public void setFirstCheckerResult(String firstCheckerResult) {
        this.firstCheckerResult = firstCheckerResult;
    }

    public void setSecondCheckerResult(String secondCheckerResult) {
        this.secondCheckerResult = secondCheckerResult;
    }

    public void setThirdCheckerPaiQiResult(String thirdCheckerPaiQiResult) {
        this.thirdCheckerPaiQiResult = thirdCheckerPaiQiResult;
    }

    public void setThirdCheckerQunFaFangAnResult(String thirdCheckerQunFaFangAnResult) {
        this.thirdCheckerQunFaFangAnResult = thirdCheckerQunFaFangAnResult;
    }

    public String getFirstCheckerEmail() {
        return firstCheckerEmail;
    }

    public void setFirstCheckerEmail(String firstCheckerEmail) {
        this.firstCheckerEmail = firstCheckerEmail;
    }

    public String getSecondCheckerEmail() {
        return secondCheckerEmail;
    }

    public void setSecondCheckerEmail(String secondCheckerEmail) {
        this.secondCheckerEmail = secondCheckerEmail;
    }

    public String getThirdCheckerEmail() {
        return thirdCheckerEmail;
    }

    public void setThirdCheckerEmail(String thirdCheckerEmail) {
        this.thirdCheckerEmail = thirdCheckerEmail;
    }

    public List<EdmTaskResult> getEdmTaskResults() {
        return edmTaskResults;
    }

    public void setEdmTaskResults(List<EdmTaskResult> edmTaskResults) {
        this.edmTaskResults = edmTaskResults;
    }

    @Override
    public String toString() {
        return "EdmApplyOrderCheckResult{" +
                "ocId='" + ocId + '\'' +
                ", firstCheckerUserName='" + firstCheckerUserName + '\'' +
                ", firstCheckerEmail='" + firstCheckerEmail + '\'' +
                ", applyGroupCheckStatus=" + applyGroupCheckStatus +
                ", firstCheckerResult='" + firstCheckerResult + '\'' +
                ", secondCheckerUserName='" + secondCheckerUserName + '\'' +
                ", secondCheckerEmail='" + secondCheckerEmail + '\'' +
                ", capacityCheckStatus=" + capacityCheckStatus +
                ", secondCheckerResult='" + secondCheckerResult + '\'' +
                ", paiQiResult='" + paiQiResult + '\'' +
                ", thirdCheckerUserName='" + thirdCheckerUserName + '\'' +
                ", thirdCheckerEmail='" + thirdCheckerEmail + '\'' +
                ", paiQiQueRenStatus=" + paiQiQueRenStatus +
                ", thirdCheckerPaiQiResult='" + thirdCheckerPaiQiResult + '\'' +
                ", qunFaFangAnQueRenStatus=" + qunFaFangAnQueRenStatus +
                ", thirdCheckerQunFaFangAnResult='" + thirdCheckerQunFaFangAnResult + '\'' +
                ", thirdCheckBeiZhu='" + thirdCheckBeiZhu + '\'' +
                ", shuJuUserName='" + shuJuUserName + '\'' +
                ", shuJuEmail='" + shuJuEmail + '\'' +
                ", dataCodes='" + dataCodes + '\'' +
                ", dataCodeArray=" + Arrays.toString(dataCodeArray) +
                ", edmTaskResults=" + edmTaskResults +
                ", fetchResultSheetName='" + fetchResultSheetName + '\'' +
                ", dataUsersDescription='" + dataUsersDescription + '\'' +
                ", actualUserNum=" + actualUserNum +
                ", endBeiZhu='" + endBeiZhu + '\'' +
                ", updateTime=" + updateTime +
                ", oid='" + oid + '\'' +
                '}';
    }
}
