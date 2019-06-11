package com.edm.edmfetchdataplatform.controller;

import com.edm.edmfetchdataplatform.domain.EdmTargetDescription;
import com.edm.edmfetchdataplatform.domain.EdmZone;
import com.edm.edmfetchdataplatform.domain.ResponseResult;
import com.edm.edmfetchdataplatform.domain.status.ResultStatus;
import com.edm.edmfetchdataplatform.service.EdmTargetDescriptionService;
import com.edm.edmfetchdataplatform.service.EdmZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Date 2019-06-11
 * @Author lifei
 */
@Controller
@RequestMapping("/edmFetchDataConditionController")
public class EdmFetchDataConditionController {

    @Autowired
    private EdmTargetDescriptionService edmTargetDescriptionService;

    @Autowired
    private EdmZoneService edmZoneService;

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


}
