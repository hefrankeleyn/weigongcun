package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.config.DataConfig;
import com.edm.edmfetchdataplatform.domain.EdmApplyFile;
import com.edm.edmfetchdataplatform.domain.translate.EdmLiuZhuanEmailParameters;
import com.edm.edmfetchdataplatform.service.EdmSendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;
import java.util.logging.Logger;

/**
 * @Date 2019-06-24
 * @Author lifei
 */
@Service
public class EdmSendEmailServiceImpl implements EdmSendEmailService {

    private static final Logger logger = Logger.getLogger("com.edm.edmfetchdataplatform.service.impl.EdmSendEmailServiceImpl");


    @Autowired
    private JavaMailSender javaMailSender;


    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private DataConfig dataConfig;



    @Override
    public void sendThymeleafEmail(EdmLiuZhuanEmailParameters edmLiuZhuanEmailParameters) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(dataConfig.getEmailFrom());
            helper.setTo(edmLiuZhuanEmailParameters.getEmailTo());
            helper.setCc(edmLiuZhuanEmailParameters.getEmailCc());
            String emailSubject = "【"+edmLiuZhuanEmailParameters.getOrderName()+"】内容推广备报材料";
            helper.setSubject(emailSubject);
            // 发送富文本
            helper.setText(generateThymeleafText(edmLiuZhuanEmailParameters), true);
            List<EdmApplyFile> applyFiles = edmLiuZhuanEmailParameters.getEdmApplyFiles();
            if (applyFiles!=null && !applyFiles.isEmpty()){
                File file = null;
                for (EdmApplyFile edmApplyFile :
                        applyFiles) {
                    file = new File(edmApplyFile.getFilepath() + File.separator + edmApplyFile.getFilename());
                    logger.info(file.toString());
                    if (file.exists()){
                        helper.addAttachment(edmApplyFile.getFilename(), file);
                    }
                }
            }
            // 发送邮件
            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 拼接 thymeleaf 的 参数
     * @param edmLiuZhuanEmailParameters
     * @return
     */
    private String generateThymeleafText(EdmLiuZhuanEmailParameters edmLiuZhuanEmailParameters){
        Context context = new Context();
        context.setVariable("username", edmLiuZhuanEmailParameters.getEmailToUserName());
        context.setVariable("orderName", edmLiuZhuanEmailParameters.getOrderName());
        context.setVariable("paiQiYiXiang", edmLiuZhuanEmailParameters.getPaiQiYiXiang());
        return templateEngine.process("email/emdApplyEmailModel.html", context);
    }
}
