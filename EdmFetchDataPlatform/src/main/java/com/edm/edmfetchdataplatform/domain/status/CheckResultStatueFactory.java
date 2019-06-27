package com.edm.edmfetchdataplatform.domain.status;

/**
 * 工厂类， 创建 审核状态的类
 * @Date 2019-06-27
 * @Author lifei
 */
public class CheckResultStatueFactory {

    /**
     * 获取 CheckResultStatue
     * @param state
     * @return
     */
    public static CheckResultStatue getCheckResult(Integer state){
        if (CheckResultStatue.CHECK_SUCCESS.getState() == state){
            return CheckResultStatue.CHECK_SUCCESS;
        }else if (CheckResultStatue.CHECK_FAIL.getState() == state){
            return CheckResultStatue.CHECK_FAIL;
        }else if (CheckResultStatue.CHECK_SURE.getState() == state){
            return CheckResultStatue.CHECK_SURE;
        }else if (CheckResultStatue.CHECK_CANCEL.getState() == state){
            return CheckResultStatue.CHECK_CANCEL;
        }else {
            return null;
        }

    }
}
