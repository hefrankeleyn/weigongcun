package com.edm.edmfetchdataplatform.controller;

import com.edm.edmfetchdataplatform.base.query.EdmApplyOrderResultQuery;
import com.edm.edmfetchdataplatform.domain.EdmApplyOrderCheckResult;
import com.edm.edmfetchdataplatform.service.EdmApplyOrderCheckResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.logging.Logger;

/**
 * 流转单审核结果的controller
 * @Date 2019-07-03
 * @Author lifei
 */
@Controller
@RequestMapping(value = "/edmApplyOrderCheckResultController")
public class EdmApplyOrderCheckResultController {

    private static Logger logger = Logger.getLogger("com.edm.edmfetchdataplatform.controller.EdmApplyOrderCheckResultController");

    /**
     * 订单结果的service
     */
    @Autowired
    private EdmApplyOrderCheckResultService edmApplyOrderCheckResultService;

    /**
     * 修改 EdmApplyOrderCheckResult
     * @param edmApplyOrderResultQuery
     * @return
     */
    @RequestMapping(value = "/updateEdmApplyOrderCheckResult", method = RequestMethod.POST)
    public String updateEdmApplyOrderCheckResult(Authentication authentication, EdmApplyOrderResultQuery edmApplyOrderResultQuery){
        // 获取当前用户
        // 获取用户名的邮箱
        String userEmail = authentication.getName();

        logger.info(edmApplyOrderResultQuery.toString());
        edmApplyOrderCheckResultService.updateEdmApplyOrderCheckResult(edmApplyOrderResultQuery, userEmail);
        return "redirect:/edmApplyOrderCheckResultController/redirectUpdateEdmOrderCheckSuccessView";
    }

    /**
     * 展示更新成功的页面
     * @return
     */
    @RequestMapping(value = "/redirectUpdateEdmOrderCheckSuccessView")
    public String redirectUpdateEdmOrderCheckSuccessView(){
        return "edmApplyOrderCheckSuccess";
    }
}
