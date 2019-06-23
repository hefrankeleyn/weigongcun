package com.edm.edmfetchdataplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Date 2019-06-23
 * @Author lifei
 */
@Controller
@RequestMapping(value = "/edmManagerController")
public class EdmManagerController {

    /**
     * 后台管理首页
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String  managerIndexView(){
        return "manager/manager";
    }
}
