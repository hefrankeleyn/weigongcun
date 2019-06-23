package com.edm.edmfetchdataplatform.domain.status;

/**
 * @Date 2019-06-23
 * @Author lifei
 */
public class LevelStateFactory {


    /**
     * 创建LevelState
     * @param level
     * @return
     */
    public static LevelState fetchLevelStateByLeve(Integer level){
        if (LevelState.EXECUTE.getLevel() == level){
            return LevelState.EXECUTE;
        }else {
            return LevelState.SUPERVISION;
        }
    }
}
