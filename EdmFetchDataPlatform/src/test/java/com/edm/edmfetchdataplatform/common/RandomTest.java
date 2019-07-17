package com.edm.edmfetchdataplatform.common;

import org.junit.Test;

import java.util.Random;

/**
 * @Date 2019-07-15
 * @Author lifei
 */
public class RandomTest {

    @Test
    public void randomTest01(){
        Random random = new Random();
        int i = random.nextInt(1000);
        System.out.println(i);
    }


    @Test
    public void strTest(){
        String yearMonthDay = "20190801";
        System.out.println(yearMonthDay.substring(0,6));
    }
}
