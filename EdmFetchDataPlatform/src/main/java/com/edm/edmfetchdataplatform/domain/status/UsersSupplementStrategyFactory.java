package com.edm.edmfetchdataplatform.domain.status;

/**
 * @Date 2019-06-18
 * @Author lifei
 */
public class UsersSupplementStrategyFactory {


    /**
     * 获取状态
     * @param strategyState
     * @return
     */
    public static String generateStrategyDescriptionByState(Integer strategyState){
        if (strategyState == 0){
            return UsersSupplementStrategy.OTHER.getStrategyDescription();
        } else if (strategyState == 1){
            return UsersSupplementStrategy.NO_SUPPLEMENT.getStrategyDescription();
        } else if (strategyState == 2){
            return UsersSupplementStrategy.SUPPLEMENT_SILENCE.getStrategyDescription();
        } else {
            return UsersSupplementStrategy.SUPPLEMENT_OTHER_PROVINCE.getStrategyDescription();
        }
    }
}
