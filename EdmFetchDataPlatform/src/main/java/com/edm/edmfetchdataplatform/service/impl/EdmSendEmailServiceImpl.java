package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.config.DataConfig;
import com.edm.edmfetchdataplatform.domain.EdmApplyFile;
import com.edm.edmfetchdataplatform.domain.status.ExamineProgressState;
import com.edm.edmfetchdataplatform.domain.translate.EdmLiuZhuanEmailParameters;
import com.edm.edmfetchdataplatform.service.EdmSendEmailService;
import com.edm.edmfetchdataplatform.tools.MyStrUtil;
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
        // 解决附件名称过程，发送乱码问题
        System.setProperty("mail.mime.splitlongparameters", "false");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(dataConfig.getEmailFrom());
            helper.setTo(edmLiuZhuanEmailParameters.getEmailTo());
            helper.setCc(edmLiuZhuanEmailParameters.getEmailCc());
            String emailSubject = "【" + edmLiuZhuanEmailParameters.getOrderName() + "】内容推广备报材料";
            helper.setSubject(emailSubject);
            // 发送富文本
            helper.setText(generateThymeleafText(edmLiuZhuanEmailParameters), true);
            List<EdmApplyFile> applyFiles = edmLiuZhuanEmailParameters.getEdmApplyFiles();
            if (applyFiles != null && !applyFiles.isEmpty()) {
                File file = null;
                for (EdmApplyFile edmApplyFile :
                        applyFiles) {
                    file = new File(edmApplyFile.getFilepath() + File.separator + edmApplyFile.getFilename());
                    logger.info(file.toString());
                    if (file.exists()) {
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
     *
     * @param edmLiuZhuanEmailParameters
     * @return
     */
    private String generateThymeleafText(EdmLiuZhuanEmailParameters edmLiuZhuanEmailParameters) {
        Context context = new Context();
        if (!MyStrUtil.isEmptyOrNull(edmLiuZhuanEmailParameters.getEmailToUserName())) {
            context.setVariable("username", edmLiuZhuanEmailParameters.getEmailToUserName());
        }
        if (!MyStrUtil.isEmptyOrNull(edmLiuZhuanEmailParameters.getOrderName())) {
            context.setVariable("orderName", edmLiuZhuanEmailParameters.getOrderName());
        }
        if (!MyStrUtil.isEmptyOrNull(edmLiuZhuanEmailParameters.getPaiQiYiXiang())) {
            context.setVariable("paiQiYiXiang", edmLiuZhuanEmailParameters.getPaiQiYiXiang());
        }
        if (!MyStrUtil.isEmptyOrNull(edmLiuZhuanEmailParameters.getGroupName())) {
            context.setVariable("groupName", edmLiuZhuanEmailParameters.getGroupName());
        }
        // 根据流转单的状态获取邮件模板
        String emailModel = fetchEmailModelByOrderStatus(edmLiuZhuanEmailParameters.getOrderStatus());
        return templateEngine.process(emailModel, context);
    }

    /**
     * 根据流转单的状态选择相应的邮件模板
     *
     * @param orderStatus
     * @return
     */
    private String fetchEmailModelByOrderStatus(Integer orderStatus) {
        // 提交申请，让申请组组长审核
        if (orderStatus == ExamineProgressState.READY_EXAMINE.getStatus()) {
            return "email/emdApplyEmailModel.html";
        }
        // 组长审核不通过，
        else if (orderStatus == ExamineProgressState.APPLY_GROUP_EXAMINE_FAIL.getStatus()) {
            return "email/failEmailModel.html";
        }
        // 组长审核通过，告知能力组复审并安排排期
        else if (orderStatus == ExamineProgressState.APPLY_GROUP_EXAMINE_SUCCESS.getStatus()) {
            return "email/applyGroupCheckEmailModel.html";
        }
        // 能力组审核不通过
        else if (orderStatus == ExamineProgressState.POWER_GROUP_EXAMINE_FAIL.getStatus()) {
            return "email/failEmailModel.html";
        }
        // 能力组审核通过，交给客户组进行备案
        else if (orderStatus == ExamineProgressState.POWER_GROUP_EXAMINE_SUCCESS.getStatus()) {
            return "email/capacityGroupCheckEmailModel.html";
        }
        // 客户组审核不通过，终止排期
        else if (orderStatus == ExamineProgressState.SERVICES_GROUP_EXAMINE_FAIL.getStatus()) {
            return "email/failEmailModel.html";
        }
        // 客服组审核通过，交给交给数据组处理
        else if (orderStatus == ExamineProgressState.SERVICES_GROUP_EXAMINE_SUCCESS.getStatus()) {
            return "email/shujuGroupCheckEmailModel.html";
        }
        // 数组组终止处理
        else if (orderStatus == ExamineProgressState.DATA_GROUP_EXAMINE_FAIL.getStatus()) {
            return "email/failEmailModel.html";
        }
        // 数据组处理完成,发邮件给
        else if (orderStatus == ExamineProgressState.DATA_GROUP_EXAMINE_SUCCESS.getStatus()) {
            return "email/shujuSuccessEmailModel.html";
        } else {
            return "email/emdApplyEmailModel.html";
        }
    }
}
