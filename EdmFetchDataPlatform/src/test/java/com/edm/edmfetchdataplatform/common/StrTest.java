package com.edm.edmfetchdataplatform.common;

import org.junit.Test;

/**
 * @Date 2019-07-20
 * @Author lifei
 */
public class StrTest {

    @Test
    public void sbTest(){
        StringBuilder sb = new StringBuilder();
        String str = null;
        System.out.println(sb.toString());
        System.out.println(str);

    }

    @Test
    public void actualNumTest(){
        Integer actualNum = null;
        System.out.println(actualNum);
        System.out.println(actualNum + "");
    }


    @Test
    public void dataCodeTest(){
        String dataCode = "1:20190717100737";
        String month = dataCode.substring(2, 8);
        String day = dataCode.substring(2, 10);
        System.out.println(month);
        System.out.println(day);
    }
}
