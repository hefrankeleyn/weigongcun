package com.edm.edmfetchdataplatform.config;

import com.edm.edmfetchdataplatform.domain.UserDetailsLogin;
import com.edm.edmfetchdataplatform.service.EdmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Date 2019-05-16
 * @Author lifei
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private EdmerService edmerService;

    // remember me 的时效
    private static final int TOKEN_VALIDITY_SECONDS = 7 * 24 * 60 * 60;

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                UserDetailsLogin userDetail = edmerService.findUserDetailsByEmail(username);
                return userDetail;
            }
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/home").authenticated()
                .antMatchers("/edmUsableMagnitudeController/showCurrentDayEdmUsableMagnitudes").hasRole("EDM")    // hasRole("ROLE_EDM")将会报异常
                // 必须登录才能访问的请求： 流转单列表、对edmApplyFile的请求、edm订单项,查看可发数据量后台管理,角色管理,审核过程
                .antMatchers("/edmApplyOrderController/**",
                        "/edmApplyFileController/**",
                        "/edmerController/**",
                        "/edmFetchDataConditionController/**",
                        "/edmUsableMagnitudeController/**",
                        "/edmManagerController",
                        "/roleController/**",
                        "/edmApplyOrderCheckResultController/**").authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
             .and()    // 启用 rememberMe 功能
                .rememberMe()
                .tokenValiditySeconds(TOKEN_VALIDITY_SECONDS)
                .key("edmerKey")
             .and()   // 启用 退出登录
                .logout()
                .logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }
}
