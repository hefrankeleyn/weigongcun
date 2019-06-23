package com.edm.edmfetchdataplatform.controller;

import com.edm.edmfetchdataplatform.domain.ResponseResult;
import com.edm.edmfetchdataplatform.domain.Role;
import com.edm.edmfetchdataplatform.domain.status.ResultStatus;
import com.edm.edmfetchdataplatform.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Date 2019-06-23
 * @Author lifei
 */
@Controller
@RequestMapping(value = "/roleController")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 查询所有的权限
     * @return
     */
    @RequestMapping(value = "/findAllRole", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult findAllRole(){
        List<Role> roles = roleService.findAllRole();
        if (roles!=null && !roles.isEmpty()){
            return new ResponseResult(ResultStatus.SUCCESS, roles);
        }
        return new ResponseResult(ResultStatus.FAIL, roles);
    }
}
