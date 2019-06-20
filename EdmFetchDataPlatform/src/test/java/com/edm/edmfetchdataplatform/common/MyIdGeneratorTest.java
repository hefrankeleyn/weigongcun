package com.edm.edmfetchdataplatform.common;

import com.edm.edmfetchdataplatform.tools.MyIdGenerator;
import org.junit.Test;

import java.util.UUID;

/**
 * @Date 2019-06-20
 * @Author lifei
 */
public class MyIdGeneratorTest {


    @Test
    public void generateUUIDTest(){
        String uuid = MyIdGenerator.createUUID();
        System.out.println(uuid);
    }
}
