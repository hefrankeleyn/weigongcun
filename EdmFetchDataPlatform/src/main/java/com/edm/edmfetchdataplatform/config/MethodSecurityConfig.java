package com.edm.edmfetchdataplatform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * 启动方法级别的权限控制
 * 使用 @Secured 注解限制方法调用
 *
 * 同时开启 @Secured注解 和 JSR-250 的 @RolesAllowed注解
 * @Date 2019-07-23
 * @Author lifei
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
}
