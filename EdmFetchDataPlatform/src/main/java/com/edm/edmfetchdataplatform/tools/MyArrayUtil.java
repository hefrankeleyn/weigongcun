package com.edm.edmfetchdataplatform.tools;

/**
 * @Date 2019-06-15
 * @Author lifei
 */
public class MyArrayUtil {

    public static String arrayToStr(String[] array){
        if(array == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if(i == array.length-1){
                sb.append(array[i]);
            }else {
                sb.append(array[i]+ ",");
            }
        }
        return sb.toString();
    }

    /**
     * 将字符串变成数组
     * @param str
     * @return
     */
    public static String[] strToArray(String str){
        if(str == null){
            return new String[0];
        }else {
            String[] array = str.split(",");
            return array;
        }
    }
}
