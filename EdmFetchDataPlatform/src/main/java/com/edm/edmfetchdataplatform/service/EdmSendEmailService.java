package com.edm.edmfetchdataplatform.service;


import com.edm.edmfetchdataplatform.domain.translate.EdmLiuZhuanEmailParameters;

/**
 * 发送email的 service
 * @Date 2019-06-24
 * @Author lifei
 */
public interface EdmSendEmailService {


    /**
     * 发送邮件
     * @param edmLiuZhuanEmailParameters 发送邮件所需要的参数
     */
    void sendThymeleafEmail(EdmLiuZhuanEmailParameters edmLiuZhuanEmailParameters);
}
