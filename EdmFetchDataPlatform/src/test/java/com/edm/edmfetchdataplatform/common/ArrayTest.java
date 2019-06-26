package com.edm.edmfetchdataplatform.common;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date 2019-06-26
 * @Author lifei
 */
public class ArrayTest {

    @Test
    public void listToArrayTest(){
        List<String> nameList = new ArrayList<>();
        nameList.add("aaaa");
        nameList.add("bbbb");
        nameList.add("cccc");

        String[] names = new String[nameList.size()];

        System.out.println(Arrays.toString(nameList.toArray(names)));
    }

    @Test
    public void listAddTest(){
        List<String> names = new ArrayList<>();
        names.add(0, "aaa");
        System.out.println(names.toString());
    }


}
