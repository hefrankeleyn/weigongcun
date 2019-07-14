package com.edm.edmfetchdataplatform.domain;

import java.io.Serializable;

/**
 * 省份和城市的名称及代码
 * @Date 2019-06-11
 * @Author lifei
 */
public class EdmZone implements Serializable {

    private static final Long serialVersionUID = -888522125L;

    private long zoneid;
    private String provincecode;
    private String provincename;
    private String citycode;
    private String cityname;

    public long getZoneid() {
        return zoneid;
    }

    public void setZoneid(long zoneid) {
        this.zoneid = zoneid;
    }

    public String getProvincecode() {
        return provincecode;
    }

    public void setProvincecode(String provincecode) {
        this.provincecode = provincecode;
    }

    public String getProvincename() {
        return provincename;
    }

    public void setProvincename(String provincename) {
        this.provincename = provincename;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    @Override
    public String toString() {
        return "EdmZone{" +
                "zoneid=" + zoneid +
                ", provincecode='" + provincecode + '\'' +
                ", provincename='" + provincename + '\'' +
                ", citycode='" + citycode + '\'' +
                ", cityname='" + cityname + '\'' +
                '}';
    }
}
