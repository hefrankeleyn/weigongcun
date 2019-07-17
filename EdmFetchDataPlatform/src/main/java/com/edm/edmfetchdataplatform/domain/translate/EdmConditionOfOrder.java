package com.edm.edmfetchdataplatform.domain.translate;

import com.edm.edmfetchdataplatform.domain.EdmApplyOrder;
import com.edm.edmfetchdataplatform.domain.EdmCondition;
import com.edm.edmfetchdataplatform.domain.Edmer;
import com.edm.edmfetchdataplatform.domain.QunFaBusiness;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 将EdmCondition 和 Order 整合成一个实体类
 * @Date 2019-07-15
 * @Author lifei
 */
public class EdmConditionOfOrder implements Serializable {

    private static final Long serialVersionUID = -21116515L;

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


    /**
     * 流转单的名称
     */
    private String orderName;

    /**
     * 申请时间
     */
    private Date applyDate;

    public EdmConditionOfOrder() {
    }

    public EdmConditionOfOrder(EdmCondition edmCondition, EdmApplyOrder edmApplyOrder) {
        this.conId = edmCondition.getConId();
        this.qunFaBusiness = edmCondition.getQunFaBusiness();
        this.dimensions = edmCondition.getDimensions();
        this.descriptions = edmCondition.getDescriptions();
        this.provinceIf = edmCondition.getProvinceIf();
        this.provinceCodes = edmCondition.getProvinceCodes();
        this.provinceNames = edmCondition.getProvinceNames();
        this.provinceOpt = edmCondition.getProvinceOpt();
        this.cityIf = edmCondition.getCityIf();
        this.cityCodes = edmCondition.getCityCodes();
        this.cityNames = edmCondition.getCityNames();
        this.cityOpt = edmCondition.getCityOpt();
        this.limitNum = edmCondition.getLimitNum();
        this.oid = edmCondition.getOid();
        this.edmer = edmCondition.getEdmer();
        this.orderName = edmApplyOrder.getOrderName();
        this.applyDate = edmApplyOrder.getApplyDate();
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

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
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

    public String getProvinceNames() {
        return provinceNames;
    }

    public void setProvinceNames(String provinceNames) {
        this.provinceNames = provinceNames;
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

    public String getCityNames() {
        return cityNames;
    }

    public void setCityNames(String cityNames) {
        this.cityNames = cityNames;
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

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Edmer getEdmer() {
        return edmer;
    }

    public void setEdmer(Edmer edmer) {
        this.edmer = edmer;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    @Override
    public String toString() {
        return "EdmConditionOfOrder{" +
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
                ", orderName='" + orderName + '\'' +
                ", applyDate=" + applyDate +
                '}';
    }
}
