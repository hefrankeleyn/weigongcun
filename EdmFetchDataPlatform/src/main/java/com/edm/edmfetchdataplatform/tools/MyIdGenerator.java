package com.edm.edmfetchdataplatform.tools;

import java.util.UUID;

/**
 * @Date 2019-06-15
 * @Author lifei
 */
public class MyIdGenerator {

    /**
     * 产生UUID
     * @return
     */
    public static String createUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
