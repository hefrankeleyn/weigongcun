package com.edm.edmfetchdataplatform.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 根据每一个EdmCondition 从hive中提取数据之后获取到的结果
 * @Date 2019-07-14
 * @Author lifei
 */
public class EdmTaskResult implements Serializable {

    private static final Long serialVersionUID = -2388825L;

    /**
     * 主键，任务id， 自增
     */
    private Integer taskId;

    /**
     * EdmCondition 的id ，用于关联提取条件和提取结果
     */
    private Integer conId;

    /**
     * 创建EdmCondition 者的姓名
     */
    private String userName;

    /**
     * 数据编码是否可用
     * 1： 为不可用
     * 2： 为可用
     */
    private Integer status;

    /**
     * EdmCondition 创建的时间
     */
    private Date submitTime;

    /**
     * EdmContition 提取完成的时间
     */
    private Date finishTime;

    /**
     * 数据编码对应的数据文件
     * 目前存放的是一个文件文件的文件名
     * 文件的路径是和精准投递平台商定好的，是一个固定的目录
     */
    private String filePath;

    /**
     * 数据编码
     */
    private String dataCode;

    /**
     * 数据文件的行数
     */
    private Integer fileLineNum;

    /**
     * EdmContition对应的活动主题名
     */
    private String topic;

    /**
     * 业务类型（EDM，账单）
     */
    private String businessType;

    /**
     * 各个省份对应提取到的数据量信息
     */
    private String provinceNumsInfo;

    /**
     * 省份对应的数据量信息
     */
    private Map<String, Integer> provinceNums = new HashMap<>();

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getConId() {
        return conId;
    }

    public void setConId(Integer conId) {
        this.conId = conId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }

    public Integer getFileLineNum() {
        return fileLineNum;
    }

    public void setFileLineNum(Integer fileLineNum) {
        this.fileLineNum = fileLineNum;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getProvinceNumsInfo() {
        return provinceNumsInfo;
    }

    public void setProvinceNumsInfo(String provinceNumsInfo) {
        this.provinceNumsInfo = provinceNumsInfo;
    }

    public Map<String, Integer> getProvinceNums() {
        return provinceNums;
    }

    public void setProvinceNums(Map<String, Integer> provinceNums) {
        this.provinceNums = provinceNums;
    }

    @Override
    public String toString() {
        return "EdmTaskResult{" +
                "taskId=" + taskId +
                ", conId=" + conId +
                ", userName='" + userName + '\'' +
                ", status=" + status +
                ", submitTime=" + submitTime +
                ", finishTime=" + finishTime +
                ", filePath='" + filePath + '\'' +
                ", dataCode='" + dataCode + '\'' +
                ", fileLineNum=" + fileLineNum +
                ", topic='" + topic + '\'' +
                ", businessType='" + businessType + '\'' +
                ", provinceNumsInfo='" + provinceNumsInfo + '\'' +
                ", provinceNums=" + provinceNums +
                '}';
    }
}
