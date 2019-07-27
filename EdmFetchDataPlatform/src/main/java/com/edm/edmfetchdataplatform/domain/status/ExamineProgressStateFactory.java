package com.edm.edmfetchdataplatform.domain.status;

/**
 * @Date 2019-07-01
 * @Author lifei
 */
public class ExamineProgressStateFactory {

    /**
     * 获取审核状态
     * @param state
     * @return
     */
    public static ExamineProgressState fetchExaminProgressStateByState(Integer state){
        if (ExamineProgressState.READY_EXAMINE.getStatus() == state){
            return ExamineProgressState.READY_EXAMINE;
        }else if (ExamineProgressState.APPLY_GROUP_EXAMINE_FAIL.getStatus() == state){
            return ExamineProgressState.APPLY_GROUP_EXAMINE_FAIL;
        }else if (ExamineProgressState.APPLY_GROUP_EXAMINE_SUCCESS.getStatus() == state){
            return ExamineProgressState.APPLY_GROUP_EXAMINE_SUCCESS;
        }else if (ExamineProgressState.POWER_GROUP_EXAMINE_FAIL.getStatus() == state){
            return ExamineProgressState.POWER_GROUP_EXAMINE_FAIL;
        }else if (ExamineProgressState.POWER_GROUP_EXAMINE_SUCCESS.getStatus() == state){
            return ExamineProgressState.POWER_GROUP_EXAMINE_SUCCESS;
        }else if (ExamineProgressState.SERVICES_GROUP_EXAMINE_FAIL.getStatus() == state){
            return ExamineProgressState.SERVICES_GROUP_EXAMINE_FAIL;
        }else if (ExamineProgressState.SERVICES_GROUP_EXAMINE_SUCCESS.getStatus() == state){
            return ExamineProgressState.SERVICES_GROUP_EXAMINE_SUCCESS;
        }else if (ExamineProgressState.DATA_GROUP_EXAMINE_SUCCESS.getStatus() == state){
            return ExamineProgressState.DATA_GROUP_EXAMINE_SUCCESS;
        }else if (ExamineProgressState.DATA_GROUP_EXAMINE_FAIL.getStatus() == state){
            return ExamineProgressState.DATA_GROUP_EXAMINE_FAIL;
        }else if (ExamineProgressState.ORDER_SUCCESS.getStatus() == state){
            return ExamineProgressState.ORDER_SUCCESS;
        }else if (ExamineProgressState.EXECUTE_DURING.getStatus() == state){
            return ExamineProgressState.EXECUTE_DURING;
        }else {
            return ExamineProgressState.READY_EXAMINE;
        }
    }
}
