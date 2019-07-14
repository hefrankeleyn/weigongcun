package com.edm.edmfetchdataplatform.domain;

import com.edm.edmfetchdataplatform.tools.MyArrayUtil;

import java.io.Serializable;

/**
 * 提数条件
 * @Date 2019-06-15
 * @Author lifei
 */
public class EdmCondition implements Serializable {

    private static final Long serialVersionUID = -2362L;
    // 主键, 自增
    private Integer conId;

    /**
     * 提数的业务类型
     */
    private QunFaBusiness qunFaBusiness;

    // 所要提取的用户维度
    private String dimensions;

    // 用户维度描述
    private String descriptions;

    /**
     * 是否拼接省份条件
     * 1 : 拼接省份条件
     * 0 : 不拼接省份条件
     */
    private Integer provinceIf;

    /**
     * 省份代码,多个省份代码用逗号隔开
     */
    private String provinceCodes;

    /**
     * 省份名称
     */
    private String provinceNames;

    /**
     * 对省份条件的操作
     * 1 : 包含所选省份  in ('')
     * 0 : 排除所选省份  not in ('')
     * null ： 不需要拼接此条件
     */
    private Integer provinceOpt;

    /**
     * 是否拼接城市条件
     * 1 : 拼接城市条件
     * 0 : 不拼接城市条件
     */
    private Integer cityIf;

    /**
     * 城市代码, 多个省份代码之间用逗号隔开
     */
    private String cityCodes;

    /**
     * 城市名称
     */
    private String cityNames;
    /**
     * 对城市条件的操作
     * 0： 包含所选城市
     * 1： 排除所选城市
     *  null 不需要拼接此条件
     */
    private Integer cityOpt;


    /**
     * 所要提取的数据量
     * 不能为空
     */
    private Integer limitNum;

    /**
     * 申请流转单的 id
     */
    private String oid;


    /**
     * 每一个提数提条件对应一个用户
     */
    private Edmer edmer;


    public EdmCondition() {
        this.provinceIf = 0;
        this.cityIf = 0;
    }

    public EdmCondition(EdmFetchDataCondition condition, Edmer edmer){
        this.dimensions = MyArrayUtil.arrayToStr(condition.getDimensions());
        this.provinceIf = condition.getProvinceIf()==null? 0: condition.getProvinceIf();
        this.provinceCodes = MyArrayUtil.arrayToStr(condition.getProvinceCodes());
        this.provinceOpt = condition.getProvinceOpt();
        this.cityIf = condition.getCityIf()==null? 0: condition.getCityIf();
        this.cityCodes = MyArrayUtil.arrayToStr(condition.getCityCodes());
        this.cityOpt = condition.getCityOpt();
        this.limitNum = condition.getLimitNum()==null || condition.getLimitNum() <0 ? 0: condition.getLimitNum();

        this.edmer = edmer;
        this.qunFaBusiness = condition.getQunFaBusiness();
    }

    public EdmCondition(EdmFetchDataCondition condition, Edmer edmer, QunFaBusiness qunFaBusiness){
        this.dimensions = MyArrayUtil.arrayToStr(condition.getDimensions());
        this.provinceIf = condition.getProvinceIf()==null? 0: condition.getProvinceIf();
        this.provinceCodes = MyArrayUtil.arrayToStr(condition.getProvinceCodes());
        this.provinceOpt = condition.getProvinceOpt();
        this.cityIf = condition.getCityIf()==null? 0: condition.getCityIf();
        this.cityCodes = MyArrayUtil.arrayToStr(condition.getCityCodes());
        this.cityOpt = condition.getCityOpt();
        this.limitNum = condition.getLimitNum()==null || condition.getLimitNum() <0 ? 0: condition.getLimitNum();

        this.edmer = edmer;

        this.qunFaBusiness = qunFaBusiness;
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

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public Integer getProvinceIf() {
        return provinceIf;
    }

    public void setProvinceIf(Integer provinceIf) {
        this.provinceIf = provinceIf;
    }

    public String getProvinceCodes() {
        return provinceCodes;
    }

    public void setProvinceCodes(String provinceCodes) {
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

    public String getCityCodes() {
        return cityCodes;
    }

    public void setCityCodes(String cityCodes) {
        this.cityCodes = cityCodes;
    }

    public Integer getCityOpt() {
        return cityOpt;
    }

    public void setCityOpt(Integer cityOpt) {
        this.cityOpt = cityOpt;
    }

    public Integer getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
    }

    public Edmer getEdmer() {
        return edmer;
    }

    public void setEdmer(Edmer edmer) {
        this.edmer = edmer;
    }

    public String getProvinceNames() {
        return provinceNames;
    }

    public void setProvinceNames(String provinceNames) {
        this.provinceNames = provinceNames;
    }

    public String getCityNames() {
        return cityNames;
    }

    public void setCityNames(String cityNames) {
        this.cityNames = cityNames;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }


    @Override
    public String toString() {
        return "EdmCondition{" +
                "conId=" + conId +
                ", qunFaBusiness=" + qunFaBusiness +
                ", dimensions='" + dimensions + '\'' +
                ", descriptions='" + descriptions + '\'' +
                ", provinceIf=" + provinceIf +
                ", provinceCodes='" + provinceCodes + '\'' +
                ", provinceNames='" + provinceNames + '\'' +
                ", provinceOpt=" + provinceOpt +
                ", cityIf=" + cityIf +
                ", cityCodes='" + cityCodes + '\'' +
                ", cityNames='" + cityNames + '\'' +
                ", cityOpt=" + cityOpt +
                ", limitNum=" + limitNum +
                ", oid='" + oid + '\'' +
                ", edmer=" + edmer +
                '}';
    }
}
