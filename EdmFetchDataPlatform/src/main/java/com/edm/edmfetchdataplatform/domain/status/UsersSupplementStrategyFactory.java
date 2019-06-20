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
        if (strategyState == UsersSupplementStrategy.OTHER.getStrategyState()){
            return UsersSupplementStrategy.OTHER.getStrategyDescription();
        } else if (strategyState == UsersSupplementStrategy.NO_SUPPLEMENT.getStrategyState()){
            return UsersSupplementStrategy.NO_SUPPLEMENT.getStrategyDescription();
        } else if (strategyState == UsersSupplementStrategy.SUPPLEMENT_SILENCE.getStrategyState()){
            return UsersSupplementStrategy.SUPPLEMENT_SILENCE.getStrategyDescription();
        } else {
            return UsersSupplementStrategy.SUPPLEMENT_OTHER_PROVINCE.getStrategyDescription();
        }
    }
}
