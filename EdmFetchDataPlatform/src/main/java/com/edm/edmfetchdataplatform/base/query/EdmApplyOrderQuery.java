package com.edm.edmfetchdataplatform.base.query;

import com.edm.edmfetchdataplatform.base.BaseQuery;
import com.edm.edmfetchdataplatform.domain.Role;
import com.edm.edmfetchdataplatform.domain.status.ExamineProgressState;
import com.edm.edmfetchdataplatform.domain.status.GroupRole;

import java.util.ArrayList;
import java.util.List;
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


    /**
     * 流转单的状态
     */
    private List<Integer> orderStates;

    @Override
    public Map<String, Object> buildWhere() {
        if (this.eid!=null){
            getKeyValues().put("eid", this.eid);
        }
        if (this.orderStates!=null && !this.orderStates.isEmpty()){
            getKeyValues().put("orderStates", orderStates);
        }
        return getKeyValues();
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public List<Integer> getOrderStates() {
        return orderStates;
    }

    public void setOrderStates(List<Integer> orderStates) {
        this.orderStates = orderStates;
    }

    @Override
    public String toString() {
        return "EdmApplyOrderQuery{" +
                "eid=" + eid +
                ", orderStates=" + orderStates +
                '}';
    }
}
