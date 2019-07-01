package com.edm.edmfetchdataplatform.domain.status;

/**
 * 提数申请审核过程
 * @Date 2019-06-20
 * @Author lifei
 */
public enum  ExamineProgressState {

    READY_EXAMINE(0, "待申请组组长审核"),
    APPLY_GROUP_EXAMINE_FAIL(1, "申请组组长审核不通过"),
    APPLY_GROUP_EXAMINE_SUCCESS(2, "申请组组长审核通过，等待能力组审核"),
    POWER_GROUP_EXAMINE_FAIL(3, "能力组待审核不通过"),
    POWER_GROUP_EXAMINE_SUCCESS(4, "能力组审核通过，等待客服组审核"),
    SERVICES_GROUP_EXAMINE_FAIL(5, "客服组审核不通过"),
    SERVICES_GROUP_EXAMINE_SUCCESS(6, "客服组审核通过，等待数据组处理"),
    DATA_GROUP_EXAMINE_SUCCESS(7, "数据组处理完成")
    ;


    /**
     * 状态
     */
    private Integer status;

    /**
     * 审核状态描述
     */
    private String description;

    ExamineProgressState(Integer status, String description) {
        this.status = status;
        this.description = description;
    }


    public String getDescription() {
        return description;
    }

    public Integer getStatus() {
        return status;
    }
}
