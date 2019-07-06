package com.edm.edmfetchdataplatform.controller;

import com.edm.edmfetchdataplatform.domain.EdmApplyOrderCheckResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Date 2019-07-03
 * @Author lifei
 */
@Controller
@RequestMapping(value = "/edmApplyOrderCheckResultController")
public class EdmApplyOrderCheckResultController {


    /**
     * 申请组组长审核
     * @param edmApplyOrderCheckResult
     * @return
     */
    @RequestMapping(value = "/applyGroupLeaderCheck", method = RequestMethod.POST)
    public String applyGroupLeaderCheck(EdmApplyOrderCheckResult edmApplyOrderCheckResult){

        return "";
    }

    /**
     * 能力组审核
     * @param edmApplyOrderCheckResult
     * @return
     */
    @RequestMapping(value = "/capacityGroupCheck", method = RequestMethod.POST)
    public String capacityGroupCheck(EdmApplyOrderCheckResult edmApplyOrderCheckResult){

        return "";
    }

    /**
     * 客户组审核
     * @param edmApplyOrderCheckResult
     * @return
     */
    @RequestMapping(value = "/customerServerGroupCheck", method = RequestMethod.POST)
    public String customerServerGroupCheck(EdmApplyOrderCheckResult edmApplyOrderCheckResult){

        return "";
    }
}
