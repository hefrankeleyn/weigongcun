package com.hef.mr.mapper;

import com.hef.mr.utils.DataCleaningPropertiesUtil;
import com.hef.mr.utils.KeyCompareValueToLineUtil;
import com.hef.mr.utils.RegularHandlerUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Map;

/**
 * @Date 2019-09-28
 * @Author lifei
 * @description 清洗kv用等号连接的日志
 */
public class DataCleaningLogWithEqualityMapper extends Mapper<LongWritable, Text, Text, NullWritable> {


    /**
     * 清洗日志
     *
     * @param key     偏移量
     * @param value   一行内容
     * @param context 经过逻辑处理之后的输出
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context) throws IOException, InterruptedException {
        Configuration configuration = context.getConfiguration();
        // 拿到一行数据，将其转换为String
        String line = value.toString();
        String logRegularExpression = configuration.get("logRegularExpression");
        String splitRegularExpression = configuration.get("splitRegularExpression");
        String logKeyNames = configuration.get("logKeyNames");
        String businessName = configuration.get("businessName");
        Map<String, String> reMapResult = RegularHandlerUtil.reHandleLogWithEquality(
                line,
                businessName,
                logKeyNames,
                logRegularExpression,
                splitRegularExpression);
        String new_line = KeyCompareValueToLineUtil.keysCompareValuesToStr(logKeyNames.split(","), reMapResult);
        if (new_line != null && !new_line.trim().equals("")) {
            context.write(new Text(new_line), NullWritable.get());
        }
    }
}
