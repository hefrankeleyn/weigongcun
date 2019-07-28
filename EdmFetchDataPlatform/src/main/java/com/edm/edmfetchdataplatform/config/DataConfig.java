package com.edm.edmfetchdataplatform.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Date 2019-06-20
 * @Author lifei
 */
@Configuration
public class DataConfig {

    @Value("${hefself.data.edm-upload-filepath}")
    private String upLoadPath;

    @Value("${hefself.data.mailserver-username}")
    private String emailFrom;

    public String getUpLoadPath() {
        return upLoadPath;
    }

    public String getEmailFrom() {
        return emailFrom;
    }
}
