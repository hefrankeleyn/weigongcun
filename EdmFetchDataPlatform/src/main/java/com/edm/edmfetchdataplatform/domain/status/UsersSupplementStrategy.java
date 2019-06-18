package com.edm.edmfetchdataplatform.domain.status;

/**
 * 目标不足时的补充策略
 * @Date 2019-06-17
 * @Author lifei
 */
public enum  UsersSupplementStrategy {

    OTHER(0, "其他"),
    NO_SUPPLEMENT(1, "不补充"),
    SUPPLEMENT_SILENCE(2, "以沉默用户补充"),
    SUPPLEMENT_OTHER_PROVINCE(3, "以其他省同属性用户补充"),
    ;

    private Integer strategyState;
    private String strategyDescription;
    UsersSupplementStrategy(Integer strategyState, String strategyDescription) {
        this.strategyState = strategyState;
        this.strategyDescription = strategyDescription;
    }

    public Integer getStrategyState() {
        return strategyState;
    }

    public String getStrategyDescription() {
        return strategyDescription;
    }

}
