package com.hef.hiveoptdemo.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.logging.Logger;

/**
 * @Date 2019-08-04
 * @Author lifei
 */
@Configuration
public class HiveJdbcConfig {

    private static final Logger logger = Logger.getLogger("com.hef.hiveoptdemo.config.HiveJdbcConfig");

    @Value("${hive.url}")
    private String hiveUrl;

    @Value("${hive.driver-class-name}")
    private String hiveDriveClassName;

    @Value("${hive.username}")
    private String hiveUserName;

    @Value("${hive.password}")
    private String hivePassWorld;


    @Bean(name = "hiveJdbcDataSource")
    @Qualifier("hiveJdbcDataSource")
    public DataSource dataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setUrl(hiveUrl);
        dataSource.setDriverClassName(hiveDriveClassName);
        dataSource.setUsername(hiveUserName);
        dataSource.setPassword(hivePassWorld);
        return dataSource;
    }

    @Bean(name = "hiveJdbcTemplate")
    public JdbcTemplate hiveJdbcTemplate(@Qualifier("hiveJdbcDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
