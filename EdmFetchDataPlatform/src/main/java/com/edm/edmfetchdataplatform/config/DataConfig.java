package com.edm.edmfetchdataplatform.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @Date 2019-06-20
 * @Author lifei
 */
@Configuration
@PropertySource("classpath:app_data.properties")
public class DataConfig {

    @Autowired
    private Environment environment;

    private String upLoadPath;

    private String emailFrom;

    public String getUpLoadPath() {
        return environment.getProperty("edm.upload.filepath");
    }

    public String getEmailFrom() {
        return environment.getProperty("mailserver.username");
    }
}
