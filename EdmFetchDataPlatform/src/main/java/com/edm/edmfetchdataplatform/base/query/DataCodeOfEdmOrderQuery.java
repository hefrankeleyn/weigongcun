package com.edm.edmfetchdataplatform.base.query;

import com.edm.edmfetchdataplatform.base.BaseQuery;

import java.util.List;
import java.util.Map;

/**
 * @Date 2019-07-21
 * @Author lifei
 */
public class DataCodeOfEdmOrderQuery extends BaseQuery {

    /**
     * 申请者的id
     */
    private Integer eid;

    /**
     * 流转单审核结果类的 id
     * 应该是流转成功的订单id
     */
    private List<String> ocIds;


    @Override
    public Map<String, Object> buildWhere() {
        if (this.eid!=null){
            getKeyValues().put("eid", this.eid);
        }
        if (this.ocIds!=null){
            getKeyValues().put("ocIds", this.ocIds);
        }

        return getKeyValues();
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public List<String> getOcIds() {
        return ocIds;
    }

    public void setOcIds(List<String> ocIds) {
        this.ocIds = ocIds;
    }

    @Override
    public String toString() {
        return "DataCodeOfEdmOrderQuery{" +
                "eid=" + eid +
                ", ocIds=" + ocIds +
                '}';
    }
}
