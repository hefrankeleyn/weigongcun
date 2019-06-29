package com.edm.edmfetchdataplatform.base.query;

import com.edm.edmfetchdataplatform.base.BaseQuery;

import java.util.Map;

/**
 * @Date 2019-06-29
 * @Author lifei
 */
public class EdmApplyOrderQuery extends BaseQuery {

    /**
     * 申请者的id
     */
    private Integer eid;

    @Override
    public Map<String, Object> buildWhere() {
        if (this.eid!=null){
            getKeyValues().put("eid", this.eid);
        }
        return getKeyValues();
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }
}
