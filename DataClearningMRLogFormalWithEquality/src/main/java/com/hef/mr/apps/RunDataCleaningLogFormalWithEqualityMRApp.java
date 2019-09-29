package com.hef.mr.apps;

/**
 * Hello world!
 *
 */

import com.hef.mr.mapper.DataCleaningLogWithEqualityMapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author lifei
 * @date 2019-09-28
 * @description 清洗日志，其日志格式的是kv对，kv之间用等号连接
 */
public class RunDataCleaningLogFormalWithEqualityMRApp
{
    public static void main( String[] args ) {
        Configuration configuration = new Configuration();
        if (args==null || args.length==0){
            System.err.println("Usage: process <in> <out>");
            System.exit(2);
        }
        try {
            Job job = Job.getInstance(configuration);
            job.setJarByClass(RunDataCleaningLogFormalWithEqualityMRApp.class);
            job.setMapperClass(DataCleaningLogWithEqualityMapper.class);
            // 没有reduce， 将其设置为0
            job.setNumReduceTasks(0);
            // 设置mr输入的类型
            job.setOutputKeyClass(Text.class);
            // 设置mr 输出的类型
            job.setOutputValueClass(NullWritable.class);

            // 指定要处理的数据所在的位置
            FileInputFormat.addInputPath(job, new Path(args[0]));
            FileOutputFormat.setOutputPath(job, new Path(args[1]));

            // 向 yarm 集群提交这个job
            boolean res = job.waitForCompletion(true);
            System.exit(res?0:1);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
