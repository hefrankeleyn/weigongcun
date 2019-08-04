package com.hef.hiveoptdemo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Date 2019-08-04
 * @Author lifei
 */
public class MyDateUtil {

    private static SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 当前的时间
     * @return
     */
    public static String currentDatetimeStr(){
        String datetime = simpleDateFormat2.format(new Date());
        return datetime;
    }
}
