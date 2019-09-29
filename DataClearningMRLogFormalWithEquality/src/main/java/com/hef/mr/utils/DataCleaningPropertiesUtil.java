package com.hef.mr.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Date 2019-09-28
 * @Author lifei
 */
public class DataCleaningPropertiesUtil {

    private static Properties properties;

    static {
        try {
            properties = new Properties();
            InputStream inputStream = DataCleaningPropertiesUtil.class.getResourceAsStream("/datacleaning.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取value
     * @return
     */
    public static String getValueByName(String keyName) {
        return  properties.getProperty(keyName);
    }
}
