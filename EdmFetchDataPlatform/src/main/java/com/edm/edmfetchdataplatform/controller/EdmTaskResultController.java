package com.edm.edmfetchdataplatform.controller;

import com.edm.edmfetchdataplatform.base.EdmPage;
import com.edm.edmfetchdataplatform.base.query.DataCodeOfEdmOrderQuery;
import com.edm.edmfetchdataplatform.domain.EdmTaskResult;
import com.edm.edmfetchdataplatform.domain.Edmer;
import com.edm.edmfetchdataplatform.domain.ResponseResult;
import com.edm.edmfetchdataplatform.domain.status.ResultStatus;
import com.edm.edmfetchdataplatform.domain.translate.DataCodeOfEdmApplyOrder;
import com.edm.edmfetchdataplatform.service.EdmTaskResultService;
import com.edm.edmfetchdataplatform.service.EdmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Date 2019-07-18
 * @Author lifei
 */
@Controller
@RequestMapping(value = "/edmTaskResultController")
public class EdmTaskResultController {

    @Autowired
    private EdmTaskResultService edmTaskResultService;

    @Autowired
    private EdmerService edmerService;

    /**
     * 判断数据编码是否存在
     * @param dataCode
     * @return
     */
    @RequestMapping(value = "/judgeDataCodeIfExists", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult judgeDataCodeIfExists(String dataCode){
        EdmTaskResult edmTaskResult = edmTaskResultService.findEdmTaskResultByDataCode(dataCode);
        if (edmTaskResult==null){
            return new ResponseResult(ResultStatus.FAIL, "数据编码不存在");
        }else {
            return new ResponseResult(ResultStatus.SUCCESS, "数据编码存在");
        }
    }

    /**
     * 根据数据编码和oid查询对应的分省数据信息
     * @param oid
     * @param dataCode
     * @return
     */
    @RequestMapping(value = "/findDataCodeOfEdmApplyOrderByDataCode/{oid}", method = RequestMethod.GET)
    public String findDataCodeOfEdmApplyOrderByDataCode(@PathVariable("oid") String oid,
                                                        String dataCode, Model model){
        DataCodeOfEdmApplyOrder dataCodeOfEdmApplyOrder =
                edmTaskResultService.findDataCodeOfEdmApplyOrderByOidAndDataCode(oid, dataCode);
        model.addAttribute("dataCodeOfEdmApplyOrder", dataCodeOfEdmApplyOrder);
        return "dataCodeProvinceNumDesc";
    }

    /**
     * 查询一页当前用户的数据编码
     * @return
     */
    @RequestMapping(value = "/findPageCurrentUserDataCode", method = RequestMethod.GET)
    public String findPageCurrentUserDataCode(Authentication authentication, Model model){
        // 获取用户名的邮箱
        String userEmail = authentication.getName();
        Edmer edmer = edmerService.findEdmerByEmail(userEmail);
        DataCodeOfEdmOrderQuery dataCodeOfEdmOrderQuery = new DataCodeOfEdmOrderQuery();
        dataCodeOfEdmOrderQuery.setEid(edmer.getEid());
        EdmPage<DataCodeOfEdmApplyOrder> dataCodeOfEdmApplyOrderPage = edmTaskResultService.findPageDataCodeOfEdmApplyOrderByDataCodeQuery(dataCodeOfEdmOrderQuery);
        model.addAttribute("edmer", edmer);
        model.addAttribute("dataCodeOfEdmApplyOrderPage", dataCodeOfEdmApplyOrderPage);
        return "dataCodeList";
    }

    /**
     * 分页查询，查询一页数据编码
     * 历史的数据编码用于拼接查询条件的，添加的状态一定将其改为1
     * @param dataCodeOfEdmOrderQuery
     * @return
     */
    @RequestMapping(value = "/findPageCurrentUserDataCodeByQuery", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult findPageCurrentUserDataCodeByQuery(@RequestBody DataCodeOfEdmOrderQuery dataCodeOfEdmOrderQuery){
        EdmPage<DataCodeOfEdmApplyOrder> dataCodeOfEdmApplyOrderPage = edmTaskResultService.findPageDataCodeOfEdmApplyOrderByDataCodeQuery(dataCodeOfEdmOrderQuery);
        return new ResponseResult(ResultStatus.SUCCESS, dataCodeOfEdmApplyOrderPage);
    }


}
