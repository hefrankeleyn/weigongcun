package com.hef.mr.utils;

import java.util.Map;

/**
 * @Date 2019-09-28
 * @Author lifei
 */
public class KeyCompareValueToLineUtil {

    /**
     * 将key 和value 拼成行
     * @param keys
     * @param kv
     * @return
     */
    public static String keysCompareValuesToStr(String[] keys, Map<String, String> kv){
        if (kv!=null && !kv.isEmpty()){
            StringBuffer sb = new StringBuffer();
            for (int i=0; i<keys.length; i++){
                if (kv.containsKey(keys[i].toLowerCase().trim())){
                    sb.append(kv.get(keys[i].toLowerCase().trim()));
                }
                if (i<keys.length-1){
                    sb.append("\t");
                }
            }
            return sb.toString();
        }else {
            return null;
        }
    }
}
