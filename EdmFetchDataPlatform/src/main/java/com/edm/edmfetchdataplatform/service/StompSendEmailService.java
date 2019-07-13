package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.translate.EdmLiuZhuanEmailParameters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用STOMP消息
 */
public interface StompSendEmailService {

    /**
     * 初始化一个发送邮件的线程池
     * 创建一个带缓存的线程吹， 该池在必要的时候创建线程，在线程空闲60秒之后终止线程
     */
    ExecutorService sendEmailPool = Executors.newCachedThreadPool();

    /**
     * 开启线程发送邮件，发送成功后发送STOMP消息
     * @param currentLoginUserEmail
     * @param edmLiuZhuanEmailParameters
     */
    void sendEmailWithRunnerAndSTOMPMessage(String currentLoginUserEmail, EdmLiuZhuanEmailParameters edmLiuZhuanEmailParameters);
}
