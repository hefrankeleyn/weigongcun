package com.edm.edmfetchdataplatform.controller;

import com.edm.edmfetchdataplatform.domain.Edmer;
import com.edm.edmfetchdataplatform.service.EdmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Date 2019-06-23
 * @Author lifei
 */
@Controller
@RequestMapping(value = "/edmerController")
public class EdmerController {

    @Autowired
    private EdmerService edmerService;

    /**
     * 跳转到注册页面
     * @return
     */
    @RequestMapping(value = "/registerEdmerView", method = RequestMethod.GET)
    public String registerEdmerView(){
        return "manager/edmerRegister";
    }

    /**
     * 提交注册页面
     * @param edmer
     * @return
     */
    @RequestMapping(value = "/registerFormSubmit", method = RequestMethod.POST)
    public String registerFormSubmit(Edmer edmer){
        edmerService.saveEdmer(edmer);
        return "redirect:/edmManagerController";
    }

    /**
     * 查看所有用户
     * @return
     */
    @RequestMapping(value = "/showAllEdmers", method = RequestMethod.GET)
    public String showAllEdmers(Model model){
        List<Edmer> edmers = edmerService.findAllEdmer();
        model.addAttribute("edmers", edmers);
        return "manager/edmerList";
    }

    /**
     * 展示修改权限页面
     * @return
     */
    @RequestMapping(value = "/roleUpdateView/{eid}", method = RequestMethod.GET)
    public String roleUpdateView(@PathVariable Integer eid, Model model){
        Edmer edmer = edmerService.findEdmerByEid(eid);
        model.addAttribute("edmer", edmer);
        return "manager/edmerRoleSetting";
    }
}
