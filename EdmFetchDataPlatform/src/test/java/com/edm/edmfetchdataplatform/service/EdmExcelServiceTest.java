package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.config.DataConfig;
import com.edm.edmfetchdataplatform.domain.*;
import com.edm.edmfetchdataplatform.tools.MyFileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 测试创建excel
 * @Date 2019-06-25
 * @Author lifei
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EdmExcelServiceTest {


    @Autowired
    private EdmExcelService edmExcelService;

    @Autowired
    private DataConfig dataConfig;

    @Test
    public void createExcelTest(){
        EdmApplyOrder edmApplyOrder = new EdmApplyOrder();

        EdmApplyOrderCheckResult edmApplyOrderCheckResult = new EdmApplyOrderCheckResult();




        edmApplyOrder.setOrderName("沃油料13期");

        Edmer edmer = new Edmer();
        edmer.setUsername("小谢");
        edmer.setDepartment("运营组");

        // 申请人及组别
        edmApplyOrder.setEdmer(edmer);
        edmApplyOrder.setQunFaTypeDescription("收入");
        edmApplyOrder.setQunFaSubjectAndContext("节日了，有活动");
        edmApplyOrder.setPaiQiYiXiang("2019年11月1日");
        edmApplyOrder.setTargetSendProvince("北京,天津");
        edmApplyOrder.setUserConditions("高级活跃用户100w");
        edmApplyOrder.setSendNum(1000);
        edmApplyOrder.setChannelSends("516");
        edmApplyOrder.setHowSupplement("不补充");
        edmApplyOrder.setMessageContext("今天心情真好，点这里~");
        edmApplyOrder.setApplyDate(new Date());

        List<EdmTaskResult> edmTaskResults = new ArrayList<>();
        EdmTaskResult edmTaskResult = new EdmTaskResult();
        edmTaskResult.setProvinceNumsInfo("北京:234、天津:223、广东:556");
        edmTaskResult.setDataCode("1:201907081345");
        edmTaskResult.setSubmitTime(new Date());
        edmTaskResult.setFileLineNum(1000);
        EdmTaskResult edmTaskResult2 = new EdmTaskResult();
        edmTaskResult2.setProvinceNumsInfo("北京:234、天津:223、广东:556");
        edmTaskResult2.setDataCode("1:201907081346");
        edmTaskResult2.setSubmitTime(new Date());
        edmTaskResult2.setFileLineNum(1000);


        edmTaskResults.add(edmTaskResult);
        edmTaskResults.add(edmTaskResult2);

        edmApplyOrderCheckResult.setDataCodes("1:201907081345、1:201907081346");
        edmApplyOrderCheckResult.setEdmTaskResults(edmTaskResults);

        String rootPath = dataConfig.getUpLoadPath();
        // 根据根目录创建一个唯一的目录
        String uniqueFilePath = MyFileUtil.createUniqueFilePath(rootPath);
        System.out.println("uniqueFilePath: " + uniqueFilePath);
        String excelFileName = "测试.xlsx";
        EdmApplyFile edmApplyFile = edmExcelService.createEdmApplyExcelOrder(edmApplyOrder, edmApplyOrderCheckResult,
                uniqueFilePath, excelFileName);
        System.out.println(edmApplyFile);

    }
}
