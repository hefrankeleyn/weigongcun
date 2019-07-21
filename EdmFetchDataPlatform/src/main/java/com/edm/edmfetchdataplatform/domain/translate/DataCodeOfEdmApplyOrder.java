package com.edm.edmfetchdataplatform.domain.translate;

import com.edm.edmfetchdataplatform.domain.EdmApplyOrder;
import com.edm.edmfetchdataplatform.domain.EdmTaskResult;
import com.edm.edmfetchdataplatform.tools.MyArrayUtil;
import com.edm.edmfetchdataplatform.tools.MyDateUtil;
import com.edm.edmfetchdataplatform.tools.MyStrUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于封装数据编码分省数据的信息
 * @Date 2019-07-21
 * @Author lifei
 */
public class DataCodeOfEdmApplyOrder implements Serializable {

    private static final Long serialVersionUID = -2110715415L;


    private String oid;
    /**
     * 流转单的名称
     */
    private String orderName;
    /**
     * 申请人的姓名
     */
    private String userName;


    /**
     * 数据编码
     */
    private String dataCode;


    /**
     * 数据量
     */
    private Integer fileLineNum;

    /**
     * 数据编码创建的时间
     */
    private String dataCodeCreateDate;


    private Map<String, Integer> provinceNums;

    public DataCodeOfEdmApplyOrder() {
    }

    public DataCodeOfEdmApplyOrder(EdmApplyOrder edmApplyOrder, EdmTaskResult edmTaskResult) {
        this.oid = edmApplyOrder.getOid();
        this.orderName = edmApplyOrder.getOrderName();
        this.userName = edmApplyOrder.getEdmer().getUsername();
        this.dataCode = edmTaskResult.getDataCode();
        this.fileLineNum = edmTaskResult.getFileLineNum();
        this.dataCodeCreateDate = MyDateUtil.excelDate(edmTaskResult.getFinishTime());
        if (!MyStrUtil.isEmptyOrNull(edmTaskResult.getProvinceNumsInfo())){
            String[] provinceNameAndNumArray = MyArrayUtil.strToArray(edmTaskResult.getProvinceNumsInfo());
            provinceNums = new HashMap<>();
            for (String provinceNameAndNum :
                    provinceNameAndNumArray) {
                String[] proNum = provinceNameAndNum.split(":");
                provinceNums.put(proNum[0], Integer.parseInt(proNum[1]));
            }
        }
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getDataCodeCreateDate() {
        return dataCodeCreateDate;
    }

    public void setDataCodeCreateDate(String dataCodeCreateDate) {
        this.dataCodeCreateDate = dataCodeCreateDate;
    }

    public Map<String, Integer> getProvinceNums() {
        return provinceNums;
    }

    public void setProvinceNums(Map<String, Integer> provinceNums) {
        this.provinceNums = provinceNums;
    }

    @Override
    public String toString() {
        return "DataCodeOfEdmApplyOrder{" +
                "orderName='" + orderName + '\'' +
                ", oid='" + oid + '\'' +
                ", userName='" + userName + '\'' +
                ", dataCode='" + dataCode + '\'' +
                ", fileLineNum='" + fileLineNum + '\'' +
                ", dataCodeCreateDate='" + dataCodeCreateDate + '\'' +
                ", provinceNums=" + provinceNums +
                '}';
    }
}
