package com.edm.edmfetchdataplatform.domain;

import java.io.Serializable;

/**
 * 群发业务
 * @Date 2019-07-14
 * @Author lifei
 */
public class QunFaBusiness implements Serializable {


    private static final Long serialVersionUID = -2238125L;

    /**
     * 业务类型，id
     */
    private Integer bId; // 主键，自增

    /**
     * 不能为空，唯一
     * 提数的业务类型标识：
     * 1 EDM群发
     * 2 账单
     *
     * 当前可能的业务有这么两种，后期如果有其他业务，可以添加
     */
    private Integer businessType;

    /**
     * 当前业务是否可用
     * 0 可用
     * 1 不可用
     */
    private Integer status;

    /**
     * 业务的名称
     */
    private String businessName;

    /**
     * 使用的可发数据表表名，
     * 必要时需要带上完整的库名
     */
    private String hiveTable;

    public Integer getbId() {
        return bId;
    }

    public void setbId(Integer bId) {
        this.bId = bId;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getHiveTable() {
        return hiveTable;
    }

    public void setHiveTable(String hiveTable) {
        this.hiveTable = hiveTable;
    }

    @Override
    public String toString() {
        return "QunFaBusiness{" +
                "bId=" + bId +
                ", businessType=" + businessType +
                ", status=" + status +
                ", businessName='" + businessName + '\'' +
                ", hiveTable='" + hiveTable + '\'' +
                '}';
    }
}
