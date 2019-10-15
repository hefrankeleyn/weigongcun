package com.hef.mr.utils;

import com.hef.mr.beans.BusinessNames;

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
     * 根据日志的业务类型解析日志
     *
     * @param line
     * @param businessName
     * @param keyNames
     * @param regularExpression
     * @param keyValueSplitRegularExpression
     * @return
     */
    public static Map<String, String> reHandleLogWithEquality(String line, String businessName, String keyNames,
                                                              String regularExpression,
                                                              String keyValueSplitRegularExpression) {
        if (businessName != null) {
            try{
                // 解析feedback的日志
                if (businessName.toUpperCase().trim().equals(BusinessNames.FEEDBACK.toString())) {
                    return reHandleFeedBackLog(line, keyNames, regularExpression, keyValueSplitRegularExpression);
                } else {
                    return reHandleLogWithEquality(line, keyNames, regularExpression, keyValueSplitRegularExpression);
                }
            }catch (Exception e){
                logger.warning(line);
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }
    }


    /**
     * 解析feedback的日志
     *
     * @param line
     * @param keyNames
     * @param regularExpression
     * @param keyValueSplitRegularExpression
     * @return
     */
    private static Map<String, String> reHandleFeedBackLog(String line, String keyNames,
                                                           String regularExpression,
                                                           String keyValueSplitRegularExpression) {
        Map<String, String> reKeyValues = null;
        Pattern compile = Pattern.compile(regularExpression);
        Matcher matcher = compile.matcher(line);
        // 查询是否匹配上
        if (matcher.matches()) {
            reKeyValues = new HashMap<>();
            // 获取所有的key
            String[] keys = keyNames.split(",");
            // 将前几个固定的值放到map中
            int matchCount = matcher.groupCount();
            for (int i = 1; i < matchCount; i++) {
                reKeyValues.put(keys[i - 1], matcher.group(i));
            }
            // 获取最后一位的value
            String spValues = matcher.group(matchCount);
            // 根据“|” 切分字符串
            if (!spValues.trim().equals("")) {
                String[] spParameters = spValues.split(keyValueSplitRegularExpression);
                for (String parameter : spParameters) {
                    if (parameter.contains("\"dataString\"")) {
                        handleDataString(parameter, reKeyValues);
                    } else if (parameter.contains("\"emailRn\"")) {
                        handleEmailRn(parameter, reKeyValues);
                    } else {
                        splitToKeyItemAndValueItemAddToMap(parameter, "=", "\"",reKeyValues);
                    }
                }
            }
        }
        return reKeyValues;
    }

    /**
     * 处理 k=v 样式的日志
     * 解析不是feedback的日志
     *
     * @param line
     * @return
     */
    private static Map<String, String> reHandleLogWithEquality(String line, String keyNames,
                                                               String regularExpression,
                                                               String keyValueSplitRegularExpression) {
//        System.out.println(line);
        Map<String, String> reKeyValues = null;
        Pattern compile = Pattern.compile(regularExpression);
        Matcher matcher = compile.matcher(line);
        // 查询是否匹配上
        if (matcher.matches()) {
            reKeyValues = new HashMap<>();
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
                    spKeys.add(tempKey.substring(0, eqIndex + 1));
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
                    // 去除行首和行尾的",之后再去除空格
                    temp_value = trimStr(temp_value, "\"");
                    reKeyValues.put(temp_key, temp_value);
                }
            }
        }
        return reKeyValues;
    }

    /**
     * 移除行尾和行尾特定的字符，并去除行首和行尾空格
     *
     * @param originStr
     * @param removeStr
     * @return
     */
    public static String trimStr(String originStr, String removeStr) {
        if (originStr.startsWith(removeStr)) {
            originStr = originStr.substring(removeStr.length());
        }
        if (originStr.endsWith(removeStr)) {
            originStr = originStr.substring(0, originStr.length() - removeStr.length());
        }
        return originStr.trim();
    }

    /**
     * 将字符串根据第一个切分符，切分成key和value
     *
     * @param parameter
     * @param splitStr
     * @param keyValue
     */
    private static void splitToKeyItemAndValueItemAddToMap(String parameter, String splitStr, String removeStr, Map<String, String> keyValue) {
        String trimParameter = parameter.trim();
        int eqIndex = trimParameter.indexOf(splitStr);
        // 切分出key和value，并去除开头和结尾的“\"”
//        System.out.println(trimParameter);
        String keyItem = trimStr(trimParameter.substring(0, eqIndex).toLowerCase(), removeStr);
        String valueItem = trimStr(trimParameter.substring(eqIndex + 1, trimParameter.length()), removeStr);
        keyValue.put(keyItem, valueItem);
    }

    /**
     * 将字符串根据第一个切分符，切分成key和value
     * @param parameters
     * @param splitStr
     * @param keyValue
     */
    private static void splitToKeyItemAndValueItemAddToMap(String[] parameters, String splitStr, String removeStr, Map<String, String> keyValue) {
        if (parameters != null && parameters.length > 0) {
            for (String parameter :
                    parameters) {
                splitToKeyItemAndValueItemAddToMap(parameter, splitStr, removeStr, keyValue);

            }
        }
    }

    /**
     * 处理特殊的 dataString
     *
     * @param dataString
     * @param keyValues
     */
    private static void handleDataString(String dataString, Map<String, String> keyValues) {
        if (dataString != null) {
            String subDataString = dataString.substring(dataString.indexOf("{") + 1, dataString.lastIndexOf("}"));
            String[] subDataStringParameters = subDataString.split("\",\"");
            for (String parameter :
                    subDataStringParameters) {
                splitToKeyItemAndValueItemAddToMap(parameter, ":", "\"", keyValues);
            }
        }
    }


    /**
     * 处理特殊的 dataString
     * @param dataString
     * @param keyValues
     */
    private static void handleEmailRn(String dataString, Map<String, String> keyValues) {
        if (dataString != null) {
            String subDataString = dataString.substring(dataString.indexOf("{") + 1, dataString.lastIndexOf("}"));
            if (subDataString.contains("{") && subDataString.contains("}")) {
                String beforeStr = subDataString.substring(0, subDataString.indexOf("{"));
                String middleStr = subDataString.substring(subDataString.indexOf("{") + 1, subDataString.indexOf("}"));
                String afterStr = subDataString.substring(subDataString.indexOf("}"));
                afterStr = afterStr.substring(afterStr.indexOf(",") + 1);
                // 切分成 key 和 value ，并放到keyValue中
                splitToKeyItemAndValueItemAddToMap(beforeStr.substring(0,beforeStr.lastIndexOf(",")).split(","),"=", "'", keyValues);
                splitToKeyItemAndValueItemAddToMap(afterStr.split(","),"=", "'", keyValues);
                keyValues.put(beforeStr.substring(beforeStr.lastIndexOf(",")+1).split("=")[0].trim().toLowerCase(), middleStr.trim());
            }
        }
    }


}
