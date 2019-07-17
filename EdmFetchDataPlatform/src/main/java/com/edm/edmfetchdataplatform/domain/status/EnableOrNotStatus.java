package com.edm.edmfetchdataplatform.domain.status;

/**
 * 可用及不可用的状态
 * @Date 2019-07-15
 * @Author lifei
 */
public enum  EnableOrNotStatus {

    enable_status(2, "可用"), disable_status(1, "不可用");

    private Integer status;
    private String desc;

    EnableOrNotStatus(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }


    public String getDesc() {
        return desc;
    }
}
