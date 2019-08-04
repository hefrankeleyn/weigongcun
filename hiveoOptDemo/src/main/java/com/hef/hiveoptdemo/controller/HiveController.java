package com.hef.hiveoptdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * @Date 2019-08-04
 * @Author lifei
 */

@RestController
@RequestMapping(value = "/hiveController")
public class HiveController {

    private static final Logger logger = Logger.getLogger("com.hef.hiveoptdemo.controller.HiveController");

    @Autowired
    @Qualifier("hiveJdbcTemplate")
    private JdbcTemplate hiveJdbcTemplate;
    @RequestMapping("/table/create")
    public String createTable() {
        StringBuffer sql = new StringBuffer("CREATE TABLE IF NOT EXISTS ");
        sql.append("tmp.user_sample");
        sql.append("(user_num BIGINT, user_name STRING, user_gender STRING, user_age INT)");
        sql.append("ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' "); // 定义分隔符
        sql.append("STORED AS TEXTFILE"); // 作为文本存储

        logger.info("Running: " + sql);
        String result = "Create table successfully...";
        try {
            // hiveJdbcTemplate.execute(sql.toString());
            hiveJdbcTemplate.execute(sql.toString());
        } catch (DataAccessException dae) {
            result = "Create table encounter an error: " + dae.getMessage();
            logger.info(result);
        }
        return result;

    }
}
