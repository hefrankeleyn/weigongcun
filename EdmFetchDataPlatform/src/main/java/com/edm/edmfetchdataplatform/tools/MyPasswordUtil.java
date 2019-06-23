package com.edm.edmfetchdataplatform.tools;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Date 2019-06-23
 * @Author lifei
 */
public class MyPasswordUtil {


    /**
     * 将密码加密
     * @param password
     * @return
     */
    public static String passwordEncord(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
