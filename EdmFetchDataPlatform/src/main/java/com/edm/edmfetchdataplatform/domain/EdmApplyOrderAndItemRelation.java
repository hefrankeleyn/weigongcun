package com.edm.edmfetchdataplatform.domain;

/**
 * 流转单和申请项之间的关系
 * @Date 2019-06-20
 * @Author lifei
 */
public class EdmApplyOrderAndItemRelation {

    private String oid;
    private Integer conId;

    public EdmApplyOrderAndItemRelation() {
    }

    public EdmApplyOrderAndItemRelation(String oid, Integer conId) {
        this.oid = oid;
        this.conId = conId;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Integer getConId() {
        return conId;
    }

    public void setConId(Integer conId) {
        this.conId = conId;
    }
}
