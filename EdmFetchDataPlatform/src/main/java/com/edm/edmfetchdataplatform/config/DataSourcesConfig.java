package com.edm.edmfetchdataplatform.config;

import liquibase.integration.spring.SpringLiquibase;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 数据源的配置
 * @Date 2019-08-04
 * @Author lifei
 */
@Configuration
public class DataSourcesConfig {

    /**
     * mysql数据源
     * @return
     */
    /*@Bean(name = "mySqlDataSource")
    @Qualifier("mySqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource mySqlDataSource(){
        return DataSourceBuilder.create().build();
    }*/
    @Value("${spring.datasource.driver-class-name}")
    private String driveName;

    @Value("${spring.datasource.jdbc-url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String passWorld;

    @Bean(name = "mySqlDataSource")
    @Qualifier("mySqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public BasicDataSource dataSourcePool(){
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driveName);
        ds.setUrl(dbUrl);
        ds.setUsername(userName);
        ds.setPassword(passWorld);
        ds.setInitialSize(5);
        return ds;
    }

    @Bean(name = "liquibase")
    public SpringLiquibase primaryLiquibase(@Qualifier("mySqlDataSource") DataSource dataSource,
                                            LiquibaseProperties liquibaseProperties){
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(liquibaseProperties.getChangeLog());
        liquibase.setContexts(liquibaseProperties.getContexts());
        liquibase.setDefaultSchema(liquibaseProperties.getDefaultSchema());
        liquibase.setDropFirst(liquibaseProperties.isDropFirst());
        liquibase.setShouldRun(liquibaseProperties.isEnabled());
        liquibase.setLabels(liquibaseProperties.getLabels());
        liquibase.setChangeLogParameters(liquibaseProperties.getParameters());
        liquibase.setRollbackFile(liquibaseProperties.getRollbackFile());
        return liquibase;
    }

    @Bean
    @ConfigurationProperties(prefix = "hefself.data.liquibase")
    public LiquibaseProperties liquibaseProperties(){
        return new LiquibaseProperties();
    }
}
