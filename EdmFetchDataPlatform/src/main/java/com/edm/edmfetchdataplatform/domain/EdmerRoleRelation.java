package com.edm.edmfetchdataplatform.domain;

/**
 * @Date 2019-06-23
 * @Author lifei
 */
public class EdmerRoleRelation {

    private Integer eid;
    private Integer rid;

    public EdmerRoleRelation() {
    }

    public EdmerRoleRelation(Integer eid, Integer rid) {
        this.eid = eid;
        this.rid = rid;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }
}
