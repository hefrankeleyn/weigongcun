package com.edm.edmfetchdataplatform.domain.status;

/**
 * @Date 2019-06-18
 * @Author lifei
 */
public enum  IncludeState {

    /**
     * 1 : 拼接条件
     * 0 : 不拼接条件
     */
    INCLUDE(1, "包含"), EXCLUDE(0, "排除");

    private Integer state;
    private String des;

    IncludeState(Integer state, String des) {
        this.state = state;
        this.des = des;
    }

    public Integer getState() {
        return state;
    }


    public String getDes() {
        return des;
    }
}
