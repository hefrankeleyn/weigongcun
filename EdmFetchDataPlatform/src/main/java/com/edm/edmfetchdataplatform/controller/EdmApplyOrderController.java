package com.edm.edmfetchdataplatform.controller;

import com.edm.edmfetchdataplatform.base.EdmPage;
import com.edm.edmfetchdataplatform.base.query.EdmApplyOrderQuery;
import com.edm.edmfetchdataplatform.domain.EdmApplyOrder;
import com.edm.edmfetchdataplatform.domain.EdmApplyOrderCheckResult;
import com.edm.edmfetchdataplatform.domain.Edmer;
import com.edm.edmfetchdataplatform.domain.ResponseResult;
import com.edm.edmfetchdataplatform.domain.status.ExamineProgressStateFactory;
import com.edm.edmfetchdataplatform.domain.status.ResultStatus;
import com.edm.edmfetchdataplatform.service.DownLoadFileService;
import com.edm.edmfetchdataplatform.service.EdmApplyOrderService;
import com.edm.edmfetchdataplatform.service.EdmerService;
import com.edm.edmfetchdataplatform.tools.MyStrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Logger;

/**
 * @Date 2019-06-27
 * @Author lifei
 */
@Controller
@RequestMapping(value = "/edmApplyOrderController")
public class EdmApplyOrderController {

    private static final Logger logger = Logger.getLogger("com.edm.edmfetchdataplatform.controller.EdmApplyOrderController");

    @Autowired
    private EdmApplyOrderService edmApplyOrderService;

    @Autowired
    private EdmerService edmerService;


    @Autowired
    private DownLoadFileService downLoadFileService;


    /**
     * 查询当前用户申请的流转单
     *
     * @param authentication
     * @return
     */
    @RequestMapping(value = "/findCurrentUserEdmApplyOrders", method = RequestMethod.GET)
    public String findCurrentUserEdmApplyOrders(Authentication authentication, Model model) {
        // 获取用户名的邮箱
        String userEmail = authentication.getName();
        List<EdmApplyOrder> edmApplyOrders = edmApplyOrderService.findEdmApplyOrdersByEmail(userEmail);
        model.addAttribute("edmApplyOrders", edmApplyOrders);
        return "edmApplyOrderList";
    }


    /**
     * 查询一页 edmApplyOrdersPage
     *
     * @param authentication
     * @param model
     * @return
     */
    @RequestMapping(value = "/findPageCurrentUserEdmApplyOrders", method = RequestMethod.GET)
    public String findPageCurrentUserEdmApplyOrders(Authentication authentication, Model model) {
        // 获取用户名的邮箱
        String userEmail = authentication.getName();
        Edmer edmer = edmerService.findEdmerByEmail(userEmail);
        EdmPage<EdmApplyOrder> pageEdmApplyOrders = edmApplyOrderService.findPageEdmApplyOrdersByEmail(userEmail);
        model.addAttribute("pageEdmApplyOrders", pageEdmApplyOrders);
        model.addAttribute("edmer", edmer);
        return "edmApplyOrdersPageList";
    }

    /**
     * 根据用户的权限查询 待审核的edmApplyOrder 列表
     * @param authentication
     * @param model
     * @return
     */
    @RequestMapping(value = "/findPageEdmApplyOrdersByUserRole", method = RequestMethod.GET)
    public String findPageEdmApplyOrdersByUserRole(Authentication authentication, Model model) {
        // 获取用户名的邮箱
        String userEmail = authentication.getName();
        Edmer edmer = edmerService.findEdmerByEmail(userEmail);
        // 根据用户角色获取订单的状态
        List<Integer> orderStatus = edmApplyOrderService.findOptOrderStatusByRoles(edmer.getRoles());

        // 初始化查询条件
        EdmApplyOrderQuery edmApplyOrderQuery = new EdmApplyOrderQuery();
        edmApplyOrderQuery.setOrderStates(orderStatus);
        // 查询一页数据
        EdmPage<EdmApplyOrder> pageEdmApplyOrders = edmApplyOrderService.findPageEdmApplyOrdersByBEdmApplyOrderQuery(edmApplyOrderQuery);
        model.addAttribute("pageEdmApplyOrders", pageEdmApplyOrders);
        model.addAttribute("edmer", edmer);
        return "edmApplyOrderCheckPageList";
    }
    /**
     * 根据用户的权限查询 待审核的edmApplyOrder 列表
     * @param authentication
     * @param edmApplyOrderQuery  条件
     * @return
     */
    @RequestMapping(value = "/findPageEdmApplyOrdersByUserRoleAndQuery", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult findPageEdmApplyOrdersByUserRoleAndQuery(Authentication authentication,@RequestBody EdmApplyOrderQuery edmApplyOrderQuery) {
        // 获取用户名的邮箱
        String userEmail = authentication.getName();
        Edmer edmer = edmerService.findEdmerByEmail(userEmail);
        // 根据用户角色获取订单的状态
        List<Integer> orderStatus = edmApplyOrderService.findOptOrderStatusByRoles(edmer.getRoles());

        // 初始化查询条件
        edmApplyOrderQuery.setOrderStates(orderStatus);
        // 查询一页数据
        EdmPage<EdmApplyOrder> pageEdmApplyOrders = edmApplyOrderService.findPageEdmApplyOrdersByBEdmApplyOrderQuery(edmApplyOrderQuery);

        return new ResponseResult(ResultStatus.SUCCESS, pageEdmApplyOrders);
    }



    /**
     * 根据 EdmApplyOrderQuery 查询一页 EdmApplyOrder
     *
     * @param edmApplyOrderQuery
     * @return
     */
    @RequestMapping(value = "/findPageCurrentUserEdmApplyOrdersByQuery", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult findPageCurrentUserEdmApplyOrdersByQuery(@RequestBody EdmApplyOrderQuery edmApplyOrderQuery) {
        EdmPage<EdmApplyOrder> pageEdmApplyOrders = edmApplyOrderService.findPageEdmApplyOrdersByQuery(edmApplyOrderQuery);
        return new ResponseResult(ResultStatus.SUCCESS, pageEdmApplyOrders);
    }


    /**
     * 根据oid 查询群发流转单
     *
     * @param oid
     * @param model
     * @return
     */
    @RequestMapping(value = "/findEdmApplyOrderByOid/{oid}", method = RequestMethod.GET)
    public String findEdmApplyOrderByOid(@PathVariable("oid") String oid, Model model) {
        EdmApplyOrder edmApplyOrder = edmApplyOrderService.findEdmApplyOrderByOid(oid);
        // 将\r\n 换成 <br>
        if (edmApplyOrder.getTargetSendProvince() != null) {
            edmApplyOrder.setTargetSendProvince(edmApplyOrder.getTargetSendProvince().replaceAll("\r\n", "<br>"));
        }
        // 将\r\n 换成 <br>
        if (edmApplyOrder.getUserConditions() != null) {
            edmApplyOrder.setUserConditions(edmApplyOrder.getUserConditions().replaceAll("\r\n", "<br>"));
        }
        // 将\r\n 换成 <br>
        if (edmApplyOrder.getUserConditions() != null) {
            EdmApplyOrderCheckResult edmApplyOrderCheckResult = edmApplyOrder.getEdmApplyOrderCheckResult();
            if (!MyStrUtil.isEmptyOrNull(edmApplyOrderCheckResult.getDataUsersDescription())){
                edmApplyOrderCheckResult.setDataUsersDescription(edmApplyOrderCheckResult.getDataUsersDescription().replaceAll("\r\n", "<br>"));
            }
        }

        // 将数据放到模型中
        model.addAttribute("edmApplyOrder", edmApplyOrder);
        logger.info(edmApplyOrder.toString());
        return "edmApplyOrderDesc";
    }


    /**
     * 展示审批流转单
     * @param oid
     * @param model
     * @return
     */
    @RequestMapping(value = "/findCheckEdmApplyOrderByOid/{oid}", method = RequestMethod.GET)
    public String findCheckEdmApplyOrderByOid(Authentication authentication,
                                              @PathVariable("oid") String oid, Model model) {
        // 获取用户名的邮箱
        String userEmail = authentication.getName();
        Edmer edmer = edmerService.findEdmerByEmail(userEmail);
        // 展示审批单
        findEdmApplyOrderByOid(oid, model);
        // 将当前用户添加到模型中
        model.addAttribute("currentEdmer", edmer);
        logger.info(edmer.toString());
        return "edmApplyOrderCheck";
    }

    /**
     * 根据流转单的oid下载流转单的Excel
     *
     * @param oid
     * @return
     */
    @RequestMapping(value = "/downLoadEdmApplyOrderExcel/{oid}", method = RequestMethod.GET)
    public void downLoadEdmApplyOrderExcel(HttpServletResponse response,
                                           @PathVariable String oid) throws FileNotFoundException {

        File excelFile = edmApplyOrderService.getEdmApplyOrderExcelByOid(oid);

        logger.info("The length of " + excelFile.getName() + " is " + excelFile.length());
        downLoadFileService.downLoadFile(excelFile, response);
    }

    /**
     * 查询当前订单的进度条
     * @param oid
     */
    @RequestMapping(value = "/findEdmApplyOrderProgress/{oid}", method = RequestMethod.GET)
    public String findEdmApplyOrderProgress(@PathVariable String oid, Model model){
        EdmApplyOrder edmApplyOrder = edmApplyOrderService.findEdmApplyOrderByOid(oid);
        edmApplyOrder.setOrderStateDescription(ExamineProgressStateFactory.fetchExaminProgressStateByState(edmApplyOrder.getOrderState()).getDescription());

        model.addAttribute("edmApplyOrder", edmApplyOrder);
        return "edmProgressBar";
    }


}
