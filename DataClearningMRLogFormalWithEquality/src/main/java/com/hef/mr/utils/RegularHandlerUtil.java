package com.hef.mr.utils;

import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Date 2019-09-28
 * @Author lifei
 * @Description 正则处理的工具类
 */
public class RegularHandlerUtil {

    private static Logger logger = Logger.getLogger("com.hef.mr.utils.RegularHandlerUtils");

    /**
     * 处理 k=v 样式的日志
     *
     * @param line
     * @return
     */
    public static Map<String, String> reHandleLogWithEquality(String line, String keyNames,
                                                              String regularExpression,
                                                              String keyValueSplitRegularExpression) {
//        System.out.println(line);
        Map<String, String> reKeyValues = new HashMap<>();
        Pattern compile = Pattern.compile(regularExpression);
        Matcher matcher = compile.matcher(line);
        // 查询是否匹配上
        if (matcher.matches()) {
            String[] keys = keyNames.split(",");
            int matchCount = matcher.groupCount();
            for (int i = 1; i < matchCount; i++) {
                reKeyValues.put(keys[i - 1], matcher.group(i));
            }
            // 获取最后一位的value
            String spValues = matcher.group(matchCount);
            // 根据“key=”切分字符串
            if (!spValues.trim().equals("")) {
                Pattern kvSpPattern = Pattern.compile(keyValueSplitRegularExpression);
                Matcher kvSpMatch = kvSpPattern.matcher(spValues);
                List<String> spKeys = new ArrayList<>();
                while (kvSpMatch.find()) {
                    // 获取使用的截取字符串
                    String tempKey = spValues.substring(kvSpMatch.start(), kvSpMatch.end());
                    // 去掉左右空格, 获取第一个 “=” 出现的位置
                    int eqIndex = tempKey.trim().indexOf("=");
                    spKeys.add(tempKey.substring(0, eqIndex+1));
                }
                // 获取key 和value
                for (int sp_i = 0; sp_i < spKeys.size(); sp_i++) {
                    // vlaue 值的开始位置
                    String begin_item = spKeys.get(sp_i) + "=";
                    int begin_index = spValues.indexOf(begin_item) + begin_item.length();
                    // value 值的结束位置
                    int end_index = spValues.length();
                    if (sp_i < spKeys.size() - 1) {
                        String end_item = spKeys.get(sp_i + 1) + "=";
                        end_index = spValues.indexOf(end_item);
                    }
                    // 将key值去除空格，并变成小写
                    String temp_key = spKeys.get(sp_i).trim().toLowerCase();
                    String temp_value = spValues.substring(begin_index, end_index);
                    reKeyValues.put(temp_key, temp_value);
                }
            }
        } else {
            logger.warning("Don't match: " + line);

        }
        return reKeyValues;
    }
}
