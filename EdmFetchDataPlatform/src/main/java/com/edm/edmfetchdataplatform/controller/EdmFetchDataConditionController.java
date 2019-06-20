package com.edm.edmfetchdataplatform.controller;

import com.edm.edmfetchdataplatform.domain.*;
import com.edm.edmfetchdataplatform.domain.status.ResultStatus;
import com.edm.edmfetchdataplatform.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.logging.Logger;

/**
 * @Date 2019-06-11
 * @Author lifei
 */
@Controller
@RequestMapping("/edmFetchDataConditionController")
public class EdmFetchDataConditionController {

    private static Logger logger = Logger.getLogger("com.edm.edmfetchdataplatform.controller.EdmFetchDataConditionController");

    @Autowired
    private EdmTargetDescriptionService edmTargetDescriptionService;

    @Autowired
    private EdmZoneService edmZoneService;

    @Autowired
    private EdmerService edmerService;

    @Autowired
    private EdmConditionService edmConditionService;

    @Autowired
    private EdmApplyOrderService edmApplyOrderService;

    /**
     * 展示提数条件页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/showConditionView", method = RequestMethod.GET)
    public String showConditionView(Model model){
        // 获取所有可提取的目标数据
        List<EdmTargetDescription> edmTargetDescriptions = edmTargetDescriptionService.findAllEdmTargetDescription();
        // 获取所有的省份数据
        List<EdmZone> provinces = edmZoneService.findAllProvinces();
        //将数据放到模型中
        model.addAttribute("edmTargetDescriptions", edmTargetDescriptions);
        model.addAttribute("provinces",provinces);
        return "conditionForm";
    }

    /**
     * 根据省份代码获取城市数据
     * @param provincecode
     * @return
     */
    @RequestMapping(value = "/findCitiesByProvinceCode", method = RequestMethod.POST)
    public @ResponseBody ResponseResult findCitiesByProvinceCode(String provincecode){
        List<EdmZone> cities = edmZoneService.findCitiesByProvinceCode(provincecode);
        if(cities!=null && !cities.isEmpty()){
            return new ResponseResult(ResultStatus.SUCCESS, cities);
        }
        return new ResponseResult(ResultStatus.FAIL, cities);
    }

    /**
     * 申请数据提取
     * @param edmFetchDataCondition
     * @return
     */
    @RequestMapping(value = "/applyFetchData", method = RequestMethod.POST)
    public String applyFetchData(Authentication authentication, EdmFetchDataCondition edmFetchDataCondition){
        logger.info(edmFetchDataCondition.toString());
        // 获取用户的邮箱
        String userEmail = authentication.getName();
        Edmer edmer = edmerService.findEdmerByEmail(userEmail);
        // 保存请求
        edmConditionService.saveEdmCondition(edmFetchDataCondition, edmer);
        return "redirect:/edmFetchDataConditionController/conditionSubmitSuccess";
    }

    /**
     * 成功添加申请项展示页面
     * @return
     */
    @RequestMapping(value = "/conditionSubmitSuccess", method = RequestMethod.GET)
    public String conditionSubmitSuccess(){
        return "conditionSubmitHint";
    }


    /**
     * 查询当前登陆用户的待提交项
     * @return
     */
    @RequestMapping(value = "/findUserEdmPrepareList", method = RequestMethod.GET)
    public String findUserEdmPrepareList(Authentication authentication,Model model){
        // 获取用户名的邮箱
        String userEmail = authentication.getName();
        List<EdmCondition> edmConditions = edmConditionService.findEdmFetchDataConditionsByUserEmail(userEmail);
        model.addAttribute("edmConditions", edmConditions);
        return "edmPrepareList";
    }

    /**
     * 展示申请页面
     * @return
     */
    @RequestMapping(value = "/showApplyView", method = RequestMethod.POST)
    public String showApplyView(Authentication authentication,Integer[] conId, Model model){
        // 获取用户名的邮箱
        String userEmail = authentication.getName();

        EdmApplyOrder edmApplyOrder = edmApplyOrderService.orderInit(conId, userEmail);
        // 将初始化订单数据放到模型中
        model.addAttribute("edmApplyOrder", edmApplyOrder);
        return "applyEmdForm";
    }

    /**
     * 提交申请页面
     * @param edmApplyOrder
     * @return
     */
    @RequestMapping(value = "/edmApplySubmit", method = RequestMethod.POST)
    public String edmApplySubmit(@RequestPart("edmFiles") MultipartFile[] edmFiles, EdmApplyOrder edmApplyOrder){
        if (edmFiles == null || edmFiles.length == 0){
            System.out.println("file is empty");
        }else {
            for (MultipartFile file :
                    edmFiles) {
                String originalFilename = file.getOriginalFilename();
                System.out.println(originalFilename);
            }
        }
        logger.info(edmApplyOrder.toString());
        return "redirect:/home";
    }

}
