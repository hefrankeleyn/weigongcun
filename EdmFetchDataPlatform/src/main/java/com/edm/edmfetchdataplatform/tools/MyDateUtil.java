package com.edm.edmfetchdataplatform.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Date 2019-05-16
 * @Author lifei
 */
public class MyDateUtil {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    private static SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyyMMddHHmmss");
    private static SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy");
    private static SimpleDateFormat simpleDateFormat4 = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 将时间类型转换成字符串
     * @param date
     * @return
     */
    public static String toDateStr(Date date){
        return simpleDateFormat.format(date);
    }

    /**
     * 当前的时间
     * @return
     */
    public static String currentDatetimeStr(){
        String datetime = simpleDateFormat2.format(new Date());
        return datetime;
    }

    /**
     * 获取当前年份
     * @return
     */
    public static String currentYearStr(){
        return simpleDateFormat3.format(new Date());
    }

    /**
     * 创建时间格式为： yyyy-MM-dd
     * @param applyDate
     * @return
     */
    public static String excelDate(Date applyDate){
        return simpleDateFormat4.format(applyDate);
    }


}
