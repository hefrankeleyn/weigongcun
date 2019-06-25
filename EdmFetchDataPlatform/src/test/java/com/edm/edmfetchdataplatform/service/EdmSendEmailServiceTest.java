package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmApplyFile;
import com.edm.edmfetchdataplatform.domain.translate.EdmLiuZhuanEmailParameters;
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

        edmSendEmailService.sendThymeleafEmail(edmLiuZhuanEmailParameters);
    }
}
