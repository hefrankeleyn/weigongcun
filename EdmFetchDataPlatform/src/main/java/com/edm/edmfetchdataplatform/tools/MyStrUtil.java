package com.edm.edmfetchdataplatform.tools;

/**
 * 工具类，对字符串的操作
 * @Date 2019-07-19
 * @Author lifei
 */
public class MyStrUtil {

    /**
     * 判断是否为空字符串
     * @param str
     * @return
     */
    public static boolean isEmptyOrNull(String str){
        if (str!=null && !str.trim().equals("")){
            return false;
        }else {
            return true;
        }
    }
}
