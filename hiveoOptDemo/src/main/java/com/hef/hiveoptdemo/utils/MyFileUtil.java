package com.hef.hiveoptdemo.utils;

import java.io.File;

/**
 * @Date 2019-08-04
 * @Author lifei
 */
public class MyFileUtil {
    /**
     * 创建路径，如果不存在
     * @param pathStr
     */
    public static void createPathIfNotExists(String pathStr){
        File file = new File(pathStr);
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
        }
    }
}
