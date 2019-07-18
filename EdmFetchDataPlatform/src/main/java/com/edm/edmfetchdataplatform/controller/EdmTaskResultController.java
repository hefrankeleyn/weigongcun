package com.edm.edmfetchdataplatform.controller;

import com.edm.edmfetchdataplatform.domain.EdmTaskResult;
import com.edm.edmfetchdataplatform.domain.ResponseResult;
import com.edm.edmfetchdataplatform.domain.status.ResultStatus;
import com.edm.edmfetchdataplatform.service.EdmTaskResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Date 2019-07-18
 * @Author lifei
 */
@Controller
@RequestMapping(value = "/edmTaskResultController")
public class EdmTaskResultController {

    @Autowired
    private EdmTaskResultService edmTaskResultService;

    /**
     * 查询所有可用的EdmTaskResult
     * @return
     */
    @RequestMapping(value = "/findAllEnableTaskResult", method = RequestMethod.GET)
    public String findAllEnableTaskResult(Model model) {
        List<EdmTaskResult> edmTaskResults = edmTaskResultService.findAllEnableEdmTaskResults();
        model.addAttribute("edmTaskResults", edmTaskResults);
        return "edmTaskResultList";
    }

    /**
     * 查询当前用户的EdmTaskResult
     * @param authentication
     * @param model
     * @return
     */
    @RequestMapping(value = "/findCurrentUserEnableTask", method = RequestMethod.GET)
    public String findCurrentUserEnableTask(Authentication authentication, Model model){
        // 获取用户名的邮箱
        String userEmail = authentication.getName();
        List<EdmTaskResult> edmTaskResultList = edmTaskResultService.findCurrentUserEnableEdmTaskResults(userEmail);
        model.addAttribute("edmTaskResultList", edmTaskResultList);
        return "edmTaskResultList";
    }


    /**
     * 判断数据编码是否存在
     * @param dataCode
     * @return
     */
    @RequestMapping(value = "/judgeDataCodeIfExists", method = RequestMethod.POST)
    public ResponseResult judgeDataCodeIfExists(String dataCode){
        EdmTaskResult edmTaskResult = edmTaskResultService.findEdmTaskResultByDataCode(dataCode);
        if (edmTaskResult==null){
            return new ResponseResult(ResultStatus.FAIL, "数据编码不存在");
        }else {
            return new ResponseResult(ResultStatus.SUCCESS, "数据编码存在");
        }
    }


}
