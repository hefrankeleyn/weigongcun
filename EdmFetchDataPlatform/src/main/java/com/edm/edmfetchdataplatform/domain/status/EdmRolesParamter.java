package com.edm.edmfetchdataplatform.domain.status;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 用户接受Controller 的参数
 * @Date 2019-06-23
 * @Author lifei
 */
public class EdmRolesParamter implements Serializable {

    private Integer eid;
    private Integer[] rids;

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer[] getRids() {
        return rids;
    }

    public void setRids(Integer[] rids) {
        this.rids = rids;
    }


    @Override
    public String toString() {
        return "EdmRolesParamter{" +
                "eid=" + eid +
                ", rids=" + Arrays.toString(rids) +
                '}';
    }
}
