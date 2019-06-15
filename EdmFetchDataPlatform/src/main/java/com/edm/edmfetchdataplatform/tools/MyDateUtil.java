package com.edm.edmfetchdataplatform.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Date 2019-05-16
 * @Author lifei
 */
public class MyDateUtil {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    /**
     * 将时间类型转换成字符串
     * @param date
     * @return
     */
    public static String toDateStr(Date date){
        return simpleDateFormat.format(date);
    }
}
