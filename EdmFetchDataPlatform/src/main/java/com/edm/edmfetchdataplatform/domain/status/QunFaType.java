package com.edm.edmfetchdataplatform.domain.status;

/**
 * 群发类型
 * @author Li Fei
 * @date 2019-06-17
 */
public enum QunFaType {

    SHOU_RU(1, "收入"),
    LA_HUO(2, "拉活"),
    WEI_XI(3, "维系"),
    ZHANGDAN_EMAIL(4, "账单-邮"),
    ZHANGDAN_DUAN(5, "短"),
    EMAIL_AND_DUAN(6, "邮+短"),
    PUSH(7, "PUSH")
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
