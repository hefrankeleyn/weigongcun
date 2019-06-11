package com.edm.edmfetchdataplatform.domain;


import java.util.Arrays;

/**
 * EDM 的提数条件
 * @Date 2019-06-05
 * @Author lifei
 */
public class EdmFetchDataCondition {

    /**
     * 用户维度
     *
     */
    private String dimension;
    /**
     * 省份代码
     */
    private String[] provinceCodes;
    /**
     * 对省份的操作
     */
    private int provinceOpt;

    /**
     * 城市代码
     */
    private String[] cityCodes;

    /**
     * 对城市的操作
     */
    private int cityOpt;

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String[] getProvinceCodes() {
        return provinceCodes;
    }

    public void setProvinceCodes(String[] provinceCodes) {
        this.provinceCodes = provinceCodes;
    }

    public int getProvinceOpt() {
        return provinceOpt;
    }

    public void setProvinceOpt(int provinceOpt) {
        this.provinceOpt = provinceOpt;
    }

    public String[] getCityCodes() {
        return cityCodes;
    }

    public void setCityCodes(String[] cityCodes) {
        this.cityCodes = cityCodes;
    }

    public int getCityOpt() {
        return cityOpt;
    }

    public void setCityOpt(int cityOpt) {
        this.cityOpt = cityOpt;
    }

    @Override
    public String toString() {
        return "EdmFetchDataCondition{" +
                "dimension='" + dimension + '\'' +
                ", provinceCodes=" + Arrays.toString(provinceCodes) +
                ", provinceOpt=" + provinceOpt +
                ", cityCodes=" + Arrays.toString(cityCodes) +
                ", cityOpt=" + cityOpt +
                '}';
    }
}
