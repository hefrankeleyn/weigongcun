package com.edm.edmfetchdataplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 因为要使用多个数据源，所以将自动配置单数据源的配置注释掉
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@SpringBootApplication
public class EdmFetchDataPlatFormApplication {

    public static void main(String[] args) {
        SpringApplication.run(EdmFetchDataPlatFormApplication.class, args);
    }

}
