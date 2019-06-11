package com.edm.edmfetchdataplatform.domain;

/**
 * 用于匹配指标的中文名称
 * @Date 2019-05-31
 * @Author lifei
 */
public enum Dimension {
    HEIGHT_CLASS_ONE("lg01", "中高级活跃用户一（连续六个月有邮短阅读（点击URL）行为用户）"),
    HEIGHT_CLASS_TWO("lg02", "中高级活跃用户二（连续三个月有邮短阅读（点击URL）行为用户）"),
    FIDELITY_USERS("zc", "忠诚用户（近三个月有1次及以上邮箱登录行为的用户）"),
    COMMON_ACTIVE_ONE("df01", "一般活跃用户一（近三个月活跃用户（涵盖各个用户层)）"),
    COMMON_ACTIVE_TWO("df02", "一般活跃用户二（近六个月活跃用户（涵盖各个用户层)）"),
    COMMON_ACTIVE_THREE("df03", "一般活跃用户三（近三个月有1-3次邮短阅读（点击URL)（涵盖各个用户层)）"),
    NEW_USERS("new","新用户：近三个月注册邮箱账号的用户"),
    SILENCE_USERS_ONE("sl01","沉默用户三（九个月以上没有活跃行为的用户（涵盖各用户层））"),
    SILENCE_USERS_TWO("sl02","沉默用户二（近九个月没有活跃行为的用户（涵盖各用户层））"),
    SILENCE_USERS_THREE("sl03","沉默用户一（近六个月没有活跃行为的用户（涵盖各用户层））");

    private String target;
    private String description;

    Dimension(String target, String description){
        this.target = target;
        this.description = description;
    }

    public String getTarget() {
        return target;
    }

    public String getDescription() {
        return description;
    }
}
