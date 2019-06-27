package com.edm.edmfetchdataplatform.controller;

import com.edm.edmfetchdataplatform.domain.EdmApplyOrder;
import com.edm.edmfetchdataplatform.service.EdmApplyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Date 2019-06-27
 * @Author lifei
 */
@Controller
@RequestMapping(value = "/edmApplyOrderController")
public class EdmApplyOrderController {

    @Autowired
    private EdmApplyOrderService edmApplyOrderService;


    /**
     * 查询当前用户申请的流转单
     * @param authentication
     * @return
     */
    @RequestMapping(value = "/findCurrentUserEdmApplyOrders", method = RequestMethod.GET)
    public String findCurrentUserEdmApplyOrders(Authentication authentication, Model model){
        // 获取用户名的邮箱
        String userEmail = authentication.getName();
        List<EdmApplyOrder> edmApplyOrders = edmApplyOrderService.findEdmApplyOrdersByEmail(userEmail);
        model.addAttribute("edmApplyOrders", edmApplyOrders);
        return "edmApplyOrderList";
    }
}
