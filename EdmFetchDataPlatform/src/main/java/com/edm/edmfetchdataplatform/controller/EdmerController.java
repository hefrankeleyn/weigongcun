package com.edm.edmfetchdataplatform.controller;

import com.edm.edmfetchdataplatform.domain.Edmer;
import com.edm.edmfetchdataplatform.domain.ResponseResult;
import com.edm.edmfetchdataplatform.domain.Role;
import com.edm.edmfetchdataplatform.domain.status.EdmRolesParamter;
import com.edm.edmfetchdataplatform.domain.status.ResultStatus;
import com.edm.edmfetchdataplatform.service.EdmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
     *
     * @return
     */
    @RequestMapping(value = "/registerEdmerView", method = RequestMethod.GET)
    public String registerEdmerView() {
        return "manager/edmerRegister";
    }

    /**
     * 提交注册页面
     *
     * @param edmer
     * @return
     */
    @RequestMapping(value = "/registerFormSubmit", method = RequestMethod.POST)
    public String registerFormSubmit(Edmer edmer) {
        edmerService.saveEdmer(edmer);
        return "redirect:/edmManagerController";
    }

    /**
     * 查看所有用户
     *
     * @return
     */
    @RequestMapping(value = "/showAllEdmers", method = RequestMethod.GET)
    public String showAllEdmers(Model model) {
        List<Edmer> edmers = edmerService.findAllEdmer();
        model.addAttribute("edmers", edmers);
        return "manager/edmerList";
    }

    /**
     * 展示修改权限页面
     *
     * @return
     */
    @RequestMapping(value = "/roleUpdateView/{eid}", method = RequestMethod.GET)
    public String roleUpdateView(@PathVariable Integer eid, Model model) {
        Edmer edmer = edmerService.findEdmerByEid(eid);
        model.addAttribute("edmer", edmer);
        return "manager/edmerRoleSetting";
    }

    /**
     * 修改Edmer 的权限
     *
     * @param edmRolesParamter
     * @return
     */
    @RequestMapping(value = "/updateEdmerRoles", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult updateEdmerRoles(@RequestBody EdmRolesParamter edmRolesParamter) {
        try {
            List<Role> roles = edmerService.updateEdmerRoles(edmRolesParamter.getEid(), edmRolesParamter.getRids());
            return new ResponseResult(ResultStatus.SUCCESS, roles);
        } catch (RuntimeException e) {
            return new ResponseResult(ResultStatus.FAIL, null);
        }
    }

    /**
     * 根据权限名称，响应的用户
     *
     * @param roleName
     * @return
     */
    @RequestMapping(value = "/findEdmerListByRole", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult findEdmerListByRole(String roleName) {
        try {
            List<Edmer> edmerList = edmerService.findEdmerListByRole(roleName);
            if (edmerList == null || edmerList.isEmpty()) {
                return new ResponseResult(ResultStatus.FAIL, null);
            } else {

                return new ResponseResult(ResultStatus.SUCCESS, edmerList);
            }
        } catch (RuntimeException e) {
            return new ResponseResult(ResultStatus.FAIL, null);
        }
    }
}
