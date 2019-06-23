package com.edm.edmfetchdataplatform.domain.status;

/**
 * @Date 2019-06-23
 * @Author lifei
 */
public enum LevelState {
    EXECUTE(1, "执行"),
    SUPERVISION(2, "监督"),
    ;

    private Integer level;
    private String levelDesc;

    LevelState(Integer level, String levelDesc) {
        this.level = level;
        this.levelDesc = levelDesc;
    }


    public Integer getLevel() {
        return level;
    }

    public String getLevelDesc() {
        return levelDesc;
    }
}
