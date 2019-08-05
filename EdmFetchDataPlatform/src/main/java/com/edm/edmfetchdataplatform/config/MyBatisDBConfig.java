package com.edm.edmfetchdataplatform.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * 配置mybatis
 * @Date 2019-08-04
 * @Author lifei
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.edm.edmfetchdataplatform.mapper", sqlSessionFactoryRef = "mySQlSqlSessionFactory",
        sqlSessionTemplateRef = "mySqlSqlSessionTemplate")
public class MyBatisDBConfig implements TransactionManagementConfigurer {

    @Autowired
    @Qualifier("mySqlDataSource")
    private DataSource mySqlDataSource;


    @Bean(name = "mySQlSqlSessionFactory")
    @Qualifier("mySQlSqlSessionFactory")
    public SqlSessionFactory mySQlSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(mySqlDataSource);
        return factoryBean.getObject();
    }

    @Bean(name = "mySqlSqlSessionTemplate")
    @Qualifier("mySqlSqlSessionTemplate")
    public SqlSessionTemplate mySqlSqlSessionTemplate(@Qualifier("mySQlSqlSessionFactory") SqlSessionFactory sessionFactory){
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sessionFactory);
        return sqlSessionTemplate;
    }


    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(mySqlDataSource);
    }
}
