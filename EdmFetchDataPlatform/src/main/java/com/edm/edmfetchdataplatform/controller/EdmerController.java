package com.edm.edmfetchdataplatform.controller;

import com.edm.edmfetchdataplatform.domain.Edmer;
import com.edm.edmfetchdataplatform.domain.ResponseResult;
import com.edm.edmfetchdataplatform.domain.Role;
import com.edm.edmfetchdataplatform.domain.status.EdmRolesParamter;
import com.edm.edmfetchdataplatform.domain.status.ResultStatus;
import com.edm.edmfetchdataplatform.service.EdmerService;
import com.edm.edmfetchdataplatform.tools.MyStrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;

/**
 * @Date 2019-06-23
 * @Author lifei
 */
@Controller
@RequestMapping(value = "/edmerController")
public class EdmerController {


    private static Logger logger = Logger.getLogger("com.edm.edmfetchdataplatform.controller.EdmerController");

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
     * 展示修改权限页面
     *
     * @return
     */
    @RequestMapping(value = "/currentUserDetailView", method = RequestMethod.GET)
    public String currentUserDetailView(Authentication authentication, Model model) {
        String email = authentication.getName();
        Edmer edmer = edmerService.findEdmerByEmail(email);
        model.addAttribute("edmer", edmer);
        return "manager/edmerReSetPassword";
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
            logger.info(edmRolesParamter.toString());
            List<Role> roles = edmerService.updateEdmerRoles(edmRolesParamter.getEid(), edmRolesParamter.getRids());
            return new ResponseResult(ResultStatus.SUCCESS, roles);
        } catch (RuntimeException e) {
            logger.warning(e.toString());
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
            logger.info(e.toString());
            return new ResponseResult(ResultStatus.FAIL, null);
        }
    }

    /**
     * 对比密码，是否与旧密码匹配
     * @param eid
     * @param oldPassword
     * @return
     */
    @RequestMapping(value = "/comparePassword", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult comparePassword(Integer eid, String oldPassword){
        Edmer edmer = edmerService.findEdmerByEid(eid);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        boolean matches = bCryptPasswordEncoder.matches(oldPassword,edmer.getPassword());
        if (matches){
            return new ResponseResult(ResultStatus.SUCCESS, "和原有密码一致");
        }else {
            return new ResponseResult(ResultStatus.FAIL, "和原有密码不一致");
        }
    }

    /**
     * 修改密码
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "/reSetPassword", method = RequestMethod.POST)
    public String reSetPassword(Integer eid, String newPassword, HttpServletRequest request){
        if (!MyStrUtil.isEmptyOrNull(newPassword) && eid!=null){
            Edmer edmer = edmerService.findEdmerByEid(eid);
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            edmer.setPassword(bCryptPasswordEncoder.encode(newPassword));
            edmerService.updateEdmer(edmer);
        }
        request.getSession().invalidate();
        return "redirect:/";
    }
}
