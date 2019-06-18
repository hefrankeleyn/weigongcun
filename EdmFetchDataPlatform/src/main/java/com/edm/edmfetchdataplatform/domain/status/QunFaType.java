package com.edm.edmfetchdataplatform.domain.status;

/**
 * 群发类型
 * @author Li Fei
 * @date 2019-06-17
 */
public enum QunFaType {

    SHOU_RU(0, "收入"),
    LA_HUO(1, "拉活"),
    WEI_XI(2, "维系"),
    ZHANGDAN_EMAIL(3, "账单-邮"),
    ZHANGDAN_DUAN(4, "短"),
    EMAIL_AND_DUAN(5, "邮+短"),
    PUSH(6, "PUSH")
    ;


    private Integer typeState;
    private String typeDescription;

    QunFaType(Integer typeState, String typeDescription) {
        this.typeState = typeState;
        this.typeDescription = typeDescription;
    }

    public Integer getTypState() {
        return typeState;
    }

    public String getTypeDescription() {
        return typeDescription;
    }
}
