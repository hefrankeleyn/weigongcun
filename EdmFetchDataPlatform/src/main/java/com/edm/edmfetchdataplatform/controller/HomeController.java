package com.edm.edmfetchdataplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Date 2019-05-16
 * @Author lifei
 */
@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {

    /**
     * 首页
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String home(){
        return "index";
    }

    /**
     * 登录页
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }
}
