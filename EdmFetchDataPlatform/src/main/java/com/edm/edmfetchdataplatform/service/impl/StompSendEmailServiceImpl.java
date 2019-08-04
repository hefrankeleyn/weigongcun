package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.domain.ResponseResult;
import com.edm.edmfetchdataplatform.domain.status.ResultStatus;
import com.edm.edmfetchdataplatform.domain.translate.EdmLiuZhuanEmailParameters;
import com.edm.edmfetchdataplatform.service.EdmSendEmailService;
import com.edm.edmfetchdataplatform.service.StompSendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * @Date 2019-07-13
 * @Author lifei
 */
@Service
public class StompSendEmailServiceImpl implements StompSendEmailService {


    private static Logger logger = Logger.getLogger("com.edm.edmfetchdataplatform.service.impl.StompSendEmailServiceImpl");

    @Autowired
    private EdmSendEmailService edmSendEmailService;
    // 用户发布消息，客户端通过订阅接收消息
    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;

    /**
     * 开启线程发送邮件，发送成功后发送STOMP消息
     *
     * @param edmLiuZhuanEmailParameters
     */
    @Override
    public void sendEmailWithRunnerAndSTOMPMessage(String currentLoginUserEmail, EdmLiuZhuanEmailParameters edmLiuZhuanEmailParameters) {
        // 启动一个线程发邮件
        // 使用lambda 表示时创建一个实例
        Runnable sendEmailRun = () -> {
            logger.info(edmLiuZhuanEmailParameters.toString());
            try {
                edmSendEmailService.sendThymeleafEmail(edmLiuZhuanEmailParameters);
                // 发布主题消息
                simpMessageSendingOperations.convertAndSendToUser(currentLoginUserEmail,
                        "/topic/sendemailfeed",
                        new ResponseResult(ResultStatus.SUCCESS, "邮件发送成功"));
            } catch (RuntimeException e) {
                simpMessageSendingOperations.convertAndSendToUser(currentLoginUserEmail,
                        "/topic/sendemailfeed",
                        new ResponseResult(ResultStatus.FAIL, "邮件发送失败"));
            }

        };
        // 启用线程池的一个线程发送邮件
        StompSendEmailService.sendEmailPool.submit(sendEmailRun);
    }
}
