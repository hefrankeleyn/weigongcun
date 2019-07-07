package com.edm.edmfetchdataplatform.controller;

import com.edm.edmfetchdataplatform.base.query.EdmApplyOrderResultQuery;
import com.edm.edmfetchdataplatform.domain.EdmApplyOrderCheckResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.logging.Logger;

/**
 * @Date 2019-07-03
 * @Author lifei
 */
@Controller
@RequestMapping(value = "/edmApplyOrderCheckResultController")
public class EdmApplyOrderCheckResultController {

    private static Logger logger = Logger.getLogger("com.edm.edmfetchdataplatform.controller.EdmApplyOrderCheckResultController");

    /**
     * 修改 EdmApplyOrderCheckResult
     * @param edmApplyOrderResultQuery
     * @return
     */
    @RequestMapping(value = "/updateEdmApplyOrderCheckResult", method = RequestMethod.POST)
    public String updateEdmApplyOrderCheckResult(EdmApplyOrderResultQuery edmApplyOrderResultQuery){

        logger.info(edmApplyOrderResultQuery.toString());
        return "edmApplyOrderCheckSuccess";
    }
}
