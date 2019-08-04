package com.hef.hiveoptdemo.dao;

import com.hef.hiveoptdemo.utils.MyDateUtil;
import com.hef.hiveoptdemo.utils.MyFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @Date 2019-08-04
 * @Author lifei
 */
@Service
public class HiveDaoImpl implements HiveDao{


    @Autowired
    @Qualifier("hiveJdbcTemplate")
    private JdbcTemplate hiveJdbcTemplate;


    private static final Logger logger = Logger.getLogger("com.hef.hiveoptdemo.dao.HiveDaoImpl");

    @Value("${hefself.data.hive-input-datapath}")
    private String hiveOutputPath;

    @Override
    public void finddimensionNums() {
        String sql = "select dimension,count(*) from dm.dm_kf_usable_users_dimension_current_month group by dimension";
        logger.info(sql);
        List<Map<String, Object>> results = hiveJdbcTemplate.queryForList(sql);

        logger.info(results.toString());

        // 判断目录是否存在，如果不存在，创建目录
        MyFileUtil.createPathIfNotExists(hiveOutputPath);
        String fileName = MyDateUtil.currentDatetimeStr() + ".txt";
        File outputFile = new File(hiveOutputPath + File.separator + fileName);
        try(FileOutputStream fileOutputStream = new FileOutputStream(outputFile)){
            if (results!=null && !results.isEmpty()){
                for (Map<String, Object> list :
                        results) {
                    String dimension = (String)list.get("key");
                    Integer num = (Integer)list.get("value");
                    String nums = dimension + ": " + num;
                    fileOutputStream.write(nums.getBytes());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
