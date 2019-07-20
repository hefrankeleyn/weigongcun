package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmApplyFile;
import com.edm.edmfetchdataplatform.domain.translate.EdmLiuZhuanEmailParameters;
import com.edm.edmfetchdataplatform.mapper.EdmApplyFileMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2019-06-24
 * @Author lifei
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EdmSendEmailServiceTest {

    @Autowired
    private EdmSendEmailService edmSendEmailService;

    @Autowired
    private EdmApplyFileService edmApplyFileService;

    @Autowired(required = false)
    private EdmApplyFileMapper edmApplyFileMapper;


    @Test
    public void sendEmailTest(){
        EdmLiuZhuanEmailParameters edmLiuZhuanEmailParameters = new EdmLiuZhuanEmailParameters();
        edmLiuZhuanEmailParameters.setOrderName("沃油料13期");
        edmLiuZhuanEmailParameters.setPaiQiYiXiang("2019年1月");
        edmLiuZhuanEmailParameters.setEmailTo("shuju@wo.cn");
        String[] emailCcs = new String[]{"lifei6@asiainfo.com", "shuju@wo.cn"};
        edmLiuZhuanEmailParameters.setEmailCc(emailCcs);
        edmLiuZhuanEmailParameters.setEmailToUserName("丽丽");
        List<EdmApplyFile> edmApplyFiles = new ArrayList<>();
        edmApplyFiles.add(new EdmApplyFile("/Users/lifei/Documents/工作/asiainfo/projects/01-Test", "测试发邮件01.md"));
        edmApplyFiles.add(new EdmApplyFile("/Users/lifei/Documents/工作/asiainfo/projects/01-Test", "测试发邮件02.md"));
        edmApplyFiles.add(new EdmApplyFile("/Users/lifei/Documents/工作/asiainfo/projects/01-Test", "测试发邮件01.md"));
        edmLiuZhuanEmailParameters.setEdmApplyFiles(edmApplyFiles);

//        edmSendEmailService.sendThymeleafEmail(edmLiuZhuanEmailParameters);
    }

    @Test
    public void sendExcelTest(){
        List<EdmApplyFile> edmApplyFiles = edmApplyFileService.findEdmApplyFilesByOid("611f9a409d7d4f57a27c1882e2f28f58");
        EdmLiuZhuanEmailParameters edmLiuZhuanEmailParameters = new EdmLiuZhuanEmailParameters();
        edmLiuZhuanEmailParameters.setOrderName("沃油料13期");
        edmLiuZhuanEmailParameters.setPaiQiYiXiang("2019年1月");
        edmLiuZhuanEmailParameters.setEmailTo("shuju@wo.cn");
        String[] emailCcs = new String[]{"lifei6@asiainfo.com", "shuju@wo.cn"};
        edmLiuZhuanEmailParameters.setEmailCc(emailCcs);
        edmLiuZhuanEmailParameters.setEmailToUserName("丽丽");
        edmLiuZhuanEmailParameters.setEdmApplyFiles(edmApplyFiles);
//        edmSendEmailService.sendThymeleafEmail(edmLiuZhuanEmailParameters);
    }

    @Test
    public void resultSendEmail(){
//        EdmLiuZhuanEmailParameters{, emailTo='shuju@wo.cn',
//        emailCc=[hefrankeleyn@163.com,
//        1246874542@qq.com,
//        frankeleyn@163.com],
//        emailToUserName='edm',
//        orderName='沃油料01期',
//        paiQiYiXiang='2019年11月13日',
//        edmApplyFiles=[EdmApplyFile{fid=null,
//        filename='《沃油料01期》群发流转单-20190720.xlsx',
//        filepath='/Users/lifei/Documents/servers/edm_upload_files/2019/0720182710701',
//        originalfilename='《沃油料01期》群发流转单-20190720.xlsx', flag=1, oid='3dcb924f0417449ebc29be60ca04e4b0'}],
//        orderStatus=7, groupName=数据组}
        EdmLiuZhuanEmailParameters edmLiuZhuanEmailParameters = new EdmLiuZhuanEmailParameters(7);
        edmLiuZhuanEmailParameters.setOrderName("沃油料01期");
        edmLiuZhuanEmailParameters.setPaiQiYiXiang("2019年1月");
        edmLiuZhuanEmailParameters.setEmailTo("shuju@wo.cn");
        edmLiuZhuanEmailParameters.setEmailToUserName("edm");
        edmLiuZhuanEmailParameters.setPaiQiYiXiang("2019年11月13日");
        String[] emailCcs = new String[]{"1246874542@qq.com", "frankeleyn@163.com","hefrankeleyn@163.com"};
        edmLiuZhuanEmailParameters.setEmailCc(emailCcs);
        List<EdmApplyFile> edmApplyFiles = new ArrayList<>();
        edmApplyFiles.add(new EdmApplyFile("/Users/lifei/Documents/servers/edm_upload_files/2019/0720182710701",
                "《沃油料01期》群发流转单-20190720.xlsx"));
        edmLiuZhuanEmailParameters.setEdmApplyFiles(edmApplyFiles);
//        edmSendEmailService.sendThymeleafEmail(edmLiuZhuanEmailParameters);

    }



}
