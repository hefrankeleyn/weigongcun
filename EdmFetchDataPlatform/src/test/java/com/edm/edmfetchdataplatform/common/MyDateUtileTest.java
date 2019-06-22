package com.edm.edmfetchdataplatform.common;

import com.edm.edmfetchdataplatform.tools.MyDateUtil;
import org.junit.Test;


/**
 * @Date 2019-06-20
 * @Author lifei
 */
public class MyDateUtileTest {

    @Test
    public void datetimeTest(){
        String dateTime = MyDateUtil.currentDatetimeStr();
        System.out.println(dateTime);
    }

    @Test
    public void randomDate(){
        double random = Math.random();
        int r = (int) (random * 1000);
        System.out.println(r);
    }

    @Test
    public void fileNameProfix(){
        String realFileName = "aa.txt";
        String profixName = realFileName.substring(realFileName.lastIndexOf("."));
        System.out.println(profixName);
    }
}
