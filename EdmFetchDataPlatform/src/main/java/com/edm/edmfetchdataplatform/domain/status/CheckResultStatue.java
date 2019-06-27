package com.edm.edmfetchdataplatform.domain.status;

/**
 * 流转单审核结果状态
 * @Date 2019-06-27
 * @Author lifei
 */
public enum  CheckResultStatue {
    // 审核通过
    CHECK_SUCCESS(0, "通过"),
    // 审核不通过
    CHECK_FAIL(1, "不通过"),
    // 审核确认
    CHECK_SURE(2, "确认"),
    // 审核取消
    CHECK_CANCEL(3, "取消");


    private Integer state;
    private String desc;

    CheckResultStatue(Integer state, String desc) {
        this.state = state;
        this.desc = desc;
    }

    public Integer getState() {
        return state;
    }

    public String getDesc() {
        return desc;
    }
}
