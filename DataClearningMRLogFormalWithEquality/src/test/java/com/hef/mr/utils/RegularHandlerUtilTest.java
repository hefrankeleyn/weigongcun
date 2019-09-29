package com.hef.mr.utils;

import org.junit.Test;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Date 2019-09-28
 * @Author lifei
 */
public class RegularHandlerUtilTest {


    @Test
    public void proUtilTest(){
        String lineReString = DataCleaningPropertiesUtil.getValueByName("PMAIL_LOGREGULAREXPRESSION");
        System.out.println(lineReString);
    }

    /**
     * 测试解析一条数据
     */
    @Test
    public void reTestLine(){
        String line = "20190823000032#3855428496 pmail retr 0 10.199.82.120 user=\"18608047501@wo.cn\" levelID=0 ID=417";
        String lineReString = DataCleaningPropertiesUtil.getValueByName("PMAIL_LOGREGULAREXPRESSION");
        String splitReString = DataCleaningPropertiesUtil.getValueByName("PMAIL_SPLITREGULAREXPRESSION");
        String keyNames = DataCleaningPropertiesUtil.getValueByName("PMAIL_LOGKEYNAMES");
        Map<String, String> reMapResult = RegularHandlerUtil.reHandleLogWithEquality(line, keyNames, lineReString ,splitReString);
        for (Map.Entry<String, String> kv: reMapResult.entrySet()) {
            System.out.println(kv.getKey() +" : " + kv.getValue());
            System.out.println(kv.getValue());
        }
        String new_line = KeyCompareValueToLineUtil.keysCompareValuesToStr(keyNames.split(","), reMapResult);
        System.out.println(new_line);
    }

    @Test
    public void matchTest(){
        String line = "20190823000032#3855428496 pmail retr 0 10.199.82.120 user=\"18608047501@wo.cn\" levelID=0 ID=417";
        String[] split = line.split("\\?<=\\s\\S+=");
        Pattern pattern = Pattern.compile("(\\s\\S+=)");
        String[] split1 = pattern.split(line);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()){
            System.out.println(line.substring(matcher.start(),matcher.end()));
        }

        for (String s :
                split1) {
            System.out.println(s);
        }
    }

    @Test
    public void removeSomeStrTest(){
        String st="\"aa\"";
        System.out.println(st);
    }
}
