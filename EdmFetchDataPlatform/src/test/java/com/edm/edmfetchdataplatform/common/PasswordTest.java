package com.edm.edmfetchdataplatform.common;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Date 2019-05-15
 * @Author lifei
 */
public class PasswordTest {

    /**
     * 测试加密算法
     */
    @Test
    public void generatePassword(){
        String ps = "edm@pass";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rePs = passwordEncoder.encode(ps);
        System.out.println(rePs);
    }

    @Test
    public void tokenValiditySecondsTest(){
        // 2 天
        System.out.println(241920/60/60/24);
        System.out.println(7 * 24 * 60 * 60);
    }
}
