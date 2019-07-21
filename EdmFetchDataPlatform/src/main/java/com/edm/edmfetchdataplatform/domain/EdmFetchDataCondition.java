package com.edm.edmfetchdataplatform.domain;


import com.edm.edmfetchdataplatform.tools.MyArrayUtil;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * EDM 的提数条件,用于和 页面 表单进行交互
 * @Date 2019-06-05
 * @Author lifei
 */
public class EdmFetchDataCondition implements Serializable {


    private static final Long serialVersionUID = -995125L;

    // 主键, 自增
    private Integer conId;

    /**
     * 提数的业务类型
     */
    private QunFaBusiness qunFaBusiness;

    /**
     * 多个用户维度
     */
    private String[] dimensions;

    /**
     * 是否拼接省份条件
     * 1 : 拼接省份条件
     * 0 : 不拼接省份条件
     */
    private Integer provinceIf;
    /**
     * 省份代码
     */
    private String[] provinceCodes;

    /**
     * 对省份条件的操作
     * 0 : 包含所选省份
     * 1 : 排除所选省份
     */
    private Integer provinceOpt;

    /**
     * 是否拼接城市条件
     * 1 : 拼接城市条件
     * 0 : 不拼接城市条件
     */
    private Integer cityIf;

    /**
     * 城市代码
     */
    private String[] cityCodes;

    /**
     * 对城市条件的操作
     * 0： 包含所选城市
     * 1： 排除所选城市
     */
    private Integer cityOpt;

    /**
     * 数据编码
     */
    private String[] dataCodes;

    /**
     * 所要提取的数据量
     * 不能为空
     */
    private Integer limitNum;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 申请流转单的 id
     */
    private String oid;

    /**
     * 每一个提数提条件对应一个用户
     */
    private Edmer edmer;

    public EdmFetchDataCondition() {
        this.provinceIf = 0;
        this.cityIf = 0;
    }

    public EdmFetchDataCondition(EdmCondition edmCondition){
        this.conId = edmCondition.getConId();
        this.dimensions = MyArrayUtil.strToArray(edmCondition.getDimensions());
        this.provinceIf = edmCondition.getProvinceIf();
        this.provinceCodes = MyArrayUtil.strToArray(edmCondition.getProvinceCodes());
        this.provinceOpt = edmCondition.getProvinceOpt();
        this.cityIf = edmCondition.getCityIf();
        this.cityCodes = MyArrayUtil.strToArray(edmCondition.getCityCodes());
        this.cityOpt = edmCondition.getCityOpt();
        this.dataCodes = MyArrayUtil.strToArray(edmCondition.getDataCodes());
        this.limitNum = edmCondition.getLimitNum();
        this.edmer = edmCondition.getEdmer();
        this.createDate = edmCondition.getCreateDate();
    }

    public Integer getConId() {
        return conId;
    }

    public void setConId(Integer conId) {
        this.conId = conId;
    }

    public QunFaBusiness getQunFaBusiness() {
        return qunFaBusiness;
    }

    public void setQunFaBusiness(QunFaBusiness qunFaBusiness) {
        this.qunFaBusiness = qunFaBusiness;
    }

    public String[] getDimensions() {
        return dimensions;
    }

    public void setDimensions(String[] dimensions) {
        this.dimensions = dimensions;
    }

    public Integer getProvinceIf() {
        return provinceIf;
    }

    public void setProvinceIf(Integer provinceIf) {
        this.provinceIf = provinceIf;
    }

    public String[] getProvinceCodes() {
        return provinceCodes;
    }

    public void setProvinceCodes(String[] provinceCodes) {
        this.provinceCodes = provinceCodes;
    }

    public Integer getProvinceOpt() {
        return provinceOpt;
    }

    public void setProvinceOpt(Integer provinceOpt) {
        this.provinceOpt = provinceOpt;
    }

    public Integer getCityIf() {
        return cityIf;
    }

    public void setCityIf(Integer cityIf) {
        this.cityIf = cityIf;
    }

    public String[] getCityCodes() {
        return cityCodes;
    }

    public void setCityCodes(String[] cityCodes) {
        this.cityCodes = cityCodes;
    }

    public Integer getCityOpt() {
        return cityOpt;
    }

    public void setCityOpt(Integer cityOpt) {
        this.cityOpt = cityOpt;
    }

    public String[] getDataCodes() {
        return dataCodes;
    }

    public void setDataCodes(String[] dataCodes) {
        this.dataCodes = dataCodes;
    }

    public Integer getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Edmer getEdmer() {
        return edmer;
    }

    public void setEdmer(Edmer edmer) {
        this.edmer = edmer;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    @Override
    public String toString() {
        return "EdmFetchDataCondition{" +
                "conId=" + conId +
                ", qunFaBusiness=" + qunFaBusiness +
                ", dimensions=" + Arrays.toString(dimensions) +
                ", provinceIf=" + provinceIf +
                ", provinceCodes=" + Arrays.toString(provinceCodes) +
                ", provinceOpt=" + provinceOpt +
                ", cityIf=" + cityIf +
                ", cityCodes=" + Arrays.toString(cityCodes) +
                ", cityOpt=" + cityOpt +
                ", dataCodes=" + Arrays.toString(dataCodes) +
                ", limitNum=" + limitNum +
                ", createDate=" + createDate +
                ", oid='" + oid + '\'' +
                ", edmer=" + edmer +
                '}';
    }
}
