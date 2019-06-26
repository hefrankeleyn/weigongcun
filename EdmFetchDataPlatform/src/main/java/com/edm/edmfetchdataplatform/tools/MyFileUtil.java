package com.edm.edmfetchdataplatform.tools;

import java.io.File;

/**
 * @Date 2019-06-25
 * @Author lifei
 */
public class MyFileUtil {

    /**
     * 根据真实的文件名创建 唯一的文件名
     *
     * @param realFileName
     * @return
     */
    public static String createUpLoadFileName(String realFileName) {
        String datetimeStr = MyDateUtil.currentDatetimeStr();
        double random = Math.random();
        int r = (int) (random * 1000);
        String fileName = uniqueStr() + realFileName.substring(realFileName.lastIndexOf("."));
        return fileName;
    }


    /**
     * 唯一的字符串
     * @return
     */
    private static String uniqueStr(){
        String datetimeStr = MyDateUtil.currentDatetimeStr();
        double random = Math.random();
        int r = (int) (random * 1000);
        return datetimeStr + r;
    }

    /**
     * 根据根目录创建 完整的上传目录
     *
     * @param rootPath
     * @return
     */
    public static String createUpLoadFilePath(String rootPath) {
        String yearStr = MyDateUtil.currentYearStr();
        String filePath = rootPath + File.separator + yearStr;
        File file = new File(rootPath + File.separator + yearStr);
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
        }
        return filePath;
    }


    /**
     * 根据rootpath创建一个唯一的子目录并和根目录拼接到一块
     * @return
     */
    public static String createUniqueFilePath(String rootPath){

        String upLoadFilePath = createUpLoadFilePath(rootPath);
        String filePath = upLoadFilePath + File.separator + uniqueStr().substring(4);
        File file = new File(filePath);
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
        }
        return filePath;
    }
}
