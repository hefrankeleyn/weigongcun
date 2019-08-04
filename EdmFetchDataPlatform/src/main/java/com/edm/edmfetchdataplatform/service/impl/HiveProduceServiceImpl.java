package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.domain.EdmTaskResult;
import com.edm.edmfetchdataplatform.domain.QunFaBusiness;
import com.edm.edmfetchdataplatform.domain.status.EnableOrNotStatus;
import com.edm.edmfetchdataplatform.domain.status.IncludeState;
import com.edm.edmfetchdataplatform.domain.translate.EdmConditionOfOrder;
import com.edm.edmfetchdataplatform.service.HiveService;
import com.edm.edmfetchdataplatform.tools.MyArrayUtil;
import com.edm.edmfetchdataplatform.tools.MyDateUtil;
import com.edm.edmfetchdataplatform.tools.MyFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

/**
 * 操作hive， 生成数据编码
 *
 * @Date 2019-07-14
 * @Author lifei
 */
@Service
@Profile("production")
public class HiveProduceServiceImpl implements HiveService {

    private static Logger logger = Logger.getLogger("com.edm.edmfetchdataplatform.service.impl.HiveServiceImpl");

    @Autowired
    @Qualifier("hiveJdbcTemplate")
    private JdbcTemplate hiveJdbcTemplate;


    @Value("${hefself.data.hive-input-datapath}")
    private String hiveOutputPath;

    /**
     * 根据 EdmCondition 中的条件，提取数据，并将数据生成数据编码
     * 步骤：
     * 1. 将EdmCondition 解析成sql语句；
     * 2. 连接hive执行查询操作，并将查到的数据保存到一张临时表中；
     * 3. 将临时表数据写入到txt文件中；
     * 4. 将txt文件发送到一个目录 （该目录是精准投送平台能够读取的）；
     * 5. 查询临时表：获取各省份及数据量情况；
     * 6. 根据当前时间创建一个数据编码；
     * 7. 将数据编码及临时表的数据写入 提取历史表中；
     * 8. 将“申请人姓名、申请时间、提取结束时间、文件名称（路径是固定的）、数据编码、
     * 当前数据编码的状态（1 为不可用， 2 为可用），提取的业务类型（EDM，账单）、
     * 各省份对应数据量、文件名称、当前活动主题名称、数据名称”属性封装到一个Bean中返回
     *
     * @param edmConditionOfOrder
     * @return
     */
    @Override
    public EdmTaskResult optHiveFetchEdmTaskResult(EdmConditionOfOrder edmConditionOfOrder) {
        EdmTaskResult edmTaskResult = new EdmTaskResult();
        try {
            // 任务开始的时间
            edmTaskResult.setSubmitTime(new Date());
            // 临时表表名
            String year_month_day = MyDateUtil.toDateStr(new Date());
            Random random = new Random();
            String tempTableName = "tmp.temp_qunfa_tishu_" + year_month_day + "_" + random.nextInt(1000);

            //  如果临时表存在删除临时表
            String deleteTempTableSql = deleteTempTableSql(tempTableName);
            // @TODO 连接hive执行删除操作
            logger.info(deleteTempTableSql);
            hiveJdbcTemplate.execute(deleteTempTableSql);
            // 创建临时表
            // @TODO 连接hive, 执行创建临时表操作
            String createTempTableSql = createTempTableSql(tempTableName);
            logger.info(createTempTableSql);
            hiveJdbcTemplate.execute(createTempTableSql);
            // @TODO 将EdmCondition解析成SQL语句， 连接hive， 执行查询语句，将查出的数据存放到临时表中,排除当前的黑名单用户
            String selectDataSql = createSqlByEdmCondition(edmConditionOfOrder, tempTableName);
            logger.info(selectDataSql);
            hiveJdbcTemplate.execute(selectDataSql);

            // @TODO 执行hive查询， 查询临时表数据，将数据存放到特定目录下的一个文件中
            String selectUserAndMailroot = selectUserDeviceAndMailroot(tempTableName);
            logger.info(selectUserAndMailroot);
            // 将临时表的数据写入到text文件中
            String dataFilePath = tempTableName + "_" + year_month_day + ".txt";
            MyFileUtil.createPathIfNotExists(hiveOutputPath);
            String inputDataToFile = "hive -e '" + selectDataSql + ";'>"+dataFilePath+ File.separator + dataFilePath;
            Runtime.getRuntime().exec(inputDataToFile);

            // @TODO 查询该临时表各省份的数据量
            Map<String, Integer> provinceNums = edmTaskResult.getProvinceNums();
            String selectProNum = selectProvinceNums(tempTableName);
            logger.info(selectProNum);
            List<Map<String, Object>> proNums = hiveJdbcTemplate.queryForList(selectProNum);
            if (proNums!=null){
                Iterator<Map<String, Object>> iterator = proNums.iterator();
                while (iterator.hasNext()){
                    Map<String, Object> next = iterator.next();
                    provinceNums.put((String)next.get("key"), (Integer) next.get("value"));
                }
            }
            // @TODO 执行hive查询， 临时表总的数据量
            String selectTotalNum = selectTotalNum(tempTableName);
            logger.info(selectTotalNum);
            Integer totalNum = hiveJdbcTemplate.queryForObject(selectTotalNum, Integer.class);
            // 查询实际的数据量
            Integer fileLineNum = 0;
            if (totalNum!=null){
                fileLineNum= totalNum;
            }

            // 生成一个数据编码
            // 当前时间，精准到时分秒
            String yearMonthDayHourMinuteSecond = MyDateUtil.currentDatetimeStr();
            String datacode = edmConditionOfOrder.getQunFaBusiness().getBusinessType() + ":" + yearMonthDayHourMinuteSecond + random.nextInt(100) + random.nextInt(100);

            // 任务结束时间
            edmTaskResult.setConId(edmConditionOfOrder.getConId());
            edmTaskResult.setFinishTime(new Date());
            edmTaskResult.setBusinessType(edmConditionOfOrder.getQunFaBusiness().getBusinessName());
            edmTaskResult.setDataCode(datacode);
            edmTaskResult.setFileLineNum(fileLineNum);
            edmTaskResult.setProvinceNums(provinceNums);
            edmTaskResult.setStatus(EnableOrNotStatus.disable_status.getStatus());
            edmTaskResult.setFilePath(dataFilePath);
            edmTaskResult.setTopic(edmConditionOfOrder.getOrderName());
            edmTaskResult.setUserName(edmConditionOfOrder.getEdmer().getUsername());

            // @TODO 将临时表数据添加到提取历史记录表中
            String createHistoryTableSql = createHistoryTableIfNotExists();
            logger.info(createHistoryTableSql);
            hiveJdbcTemplate.execute(createHistoryTableSql);
            String insertToHistoryTable = insertToHistoryTable(tempTableName, year_month_day.substring(0, 6), year_month_day, edmConditionOfOrder.getQunFaBusiness().getBusinessType(), datacode);
            logger.info(insertToHistoryTable);
            hiveJdbcTemplate.execute(insertToHistoryTable);

            // @TODO 更新可发数据库，将临时表中的数据从可发数据库中排除
            String[] dimensions = MyArrayUtil.strToArray(edmConditionOfOrder.getDimensions());
            if (dimensions != null && dimensions.length > 0) {
                for (String dimension : dimensions) {
                    String overwriteHistoryTable = overwriteEnableTableData(tempTableName, dimension);
                    logger.info(overwriteHistoryTable);
                    hiveJdbcTemplate.execute(overwriteHistoryTable);
                }
            }
            return edmTaskResult;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 拼接复写原始表特定分区的数据
     *
     * @param tempTableName
     * @return
     */
    private String overwriteEnableTableData(String tempTableName, String dimension) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert overwrite table dm.dm_kf_usable_users_dimension_current_month partition(dimension='" + dimension + "') " +
                "select " +
                "t1.user_device,t1.mailroot,t1.provincecode,t1.citycode,t1.allfee_label, " +
                "t1.manu_name,t1.phone_price_label,t1.sex,t1.age " +
                "from " +
                "( " +
                "select " +
                "user_device,mailroot,provincecode,citycode,allfee_label, " +
                "manu_name,phone_price_label,sex,age " +
                "from dm.dm_kf_usable_users_dimension_current_month " +
                "where 1=1 " +
                "and dimension='" + dimension + "' " +
                ")t1 " +
                "left outer join " +
                "( " +
                "select user_device from " + tempTableName +
                " group by user_device " +
                ")t2 " +
                "on t1.user_device=t2.user_device " +
                "where t2.user_device is null " +
                "group by t1.user_device,t1.mailroot,t1.provincecode,t1.citycode, " +
                "t1.allfee_label,t1.manu_name,t1.phone_price_label,t1.sex,t1.age");

        return sb.toString();
    }

    /**
     * 拼接 将临时表数据添加到历史记录表中的 sql
     *
     * @return
     */
    private String insertToHistoryTable(String tempTableName, String yearMonth, String yearMonthDay, Integer typeValue, String datacode) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into dw.dw_user_sendrecords_new partition(month='" + yearMonth + "',day='" + yearMonthDay + "') " +
                "select " +
                "a.user_device, " +
                "'" + typeValue + "' as type, " +
                "a.mailroot, " +
                "b.provincecode, " +
                "b.provincename, " +
                "'" + datacode + "' " +
                "from " +
                tempTableName + " a " +
                "inner join " +
                "dim.tbl_phone_zone b " +
                "on substr(a.user_device,1,7)=b.number");
        return sb.toString();
    }

    /**
     * 拼接如果不存在就创建历史表的sql
     *
     * @return
     */
    private String createHistoryTableIfNotExists() {
        StringBuilder sb = new StringBuilder();
        sb.append("create table if not exists dw.dw_user_sendrecords_new " +
                "( " +
                "user_device             string, " +
                "type                    string, " +
                "mailroot                string, " +
                "provincecode            string, " +
                "provincename            string, " +
                "datacode                string " +
                ")partitioned by( " +
                "month     string, " +
                "day       string " +
                ") " +
                "row format delimited fields terminated by '\\t'  " +
                "lines terminated by '\\n' stored as rcfile");

        return sb.toString();
    }


    /**
     * 查询临时表总的数据量
     *
     * @param tempTableName
     * @return
     */
    private String selectTotalNum(String tempTableName) {
        StringBuilder sb = new StringBuilder();
        sb.append("select count(user_device) num from " + tempTableName);
        return sb.toString();
    }

    /**
     * 查询各省份数据量的情况
     *
     * @param tempTableName
     * @return
     */
    private String selectProvinceNums(String tempTableName) {
        StringBuilder sb = new StringBuilder();
        sb.append("select provincecode,count(user_device) num from " + tempTableName + " group by provincecode");
        return sb.toString();
    }


    /**
     * 查临时表，将user_device 和 mailroot 合成一个字段
     *
     * @param tempTableName
     * @return
     */
    private String selectUserDeviceAndMailroot(String tempTableName) {
        StringBuilder sb = new StringBuilder();
        sb.append("select " +
                "case when t1.mailroot ='' then t1.user_device " +
                "else concat_ws(',' , t1.user_device , t1.mailroot) " +
                "end as user_device " +
                "from " +
                "( " +
                "select user_device, mailroot from " + tempTableName +
                " group by user_device, mailroot" +
                " )t1");
        return sb.toString();
    }


    /**
     * 删除临时表
     *
     * @param tempTableName
     * @return
     */
    private String deleteTempTableSql(String tempTableName) {
        StringBuilder sb = new StringBuilder();
        sb.append("drop table if exists " + tempTableName);

        return sb.toString();
    }


    /**
     * 拼接hive中临时表的sql
     *
     * @return
     */
    private String createTempTableSql(String tempTableName) {
        StringBuilder sb = new StringBuilder();
        // 如果临时表存在删除临时表
        // 创建临时表
        sb.append("create table if not exists " + tempTableName +
                " (" +
                "user_device string, " +
                "mailroot string, " +
                "provincecode  string " +
                ") " +
                "row format delimited fields terminated by '\\t' " +
                "lines terminated by '\\n' stored as textfile");
        return sb.toString();
    }

    /**
     * 根据 EdmCondition 创建sql语句
     * 由于当天添加的黑名单用户在可发库中没有被排除，所有在这里手动排除当前月（没有天分区，所有使用月分区）的黑名单用户
     *
     * @param edmConditionOfOrder
     * @return
     */
    private String createSqlByEdmCondition(EdmConditionOfOrder edmConditionOfOrder, String tempTableName) {
        StringBuilder sb = new StringBuilder();
        // 要查询的表
        QunFaBusiness qunFaBusiness = edmConditionOfOrder.getQunFaBusiness();
        // 拼接将数据存到临时表中
        sb.append("insert into " + tempTableName + " ");
        sb.append("select t1.user_device,t1.mailroot,t1.provincecode from (");
        // 查询用户号码、mailroot、对应的省份代码
        sb.append("select user_device, mailroot, provincecode from dm." + qunFaBusiness.getHiveTable());
        sb.append(" where 1=1 ");
        // 拼接用户维度
        String[] dimensions = MyArrayUtil.strToArray(edmConditionOfOrder.getDimensions());
        if (dimensions != null && dimensions.length > 0) {
            sb.append(" and dimension in (" + arrayToWhereInStr(dimensions) + ")");
        }
        // 是否需要排除或者只提取某些省份
        // 为1 的时候拼接省份条件
        if (edmConditionOfOrder.getProvinceIf() == 1) {
            String[] provincecodes = MyArrayUtil.strToArray(edmConditionOfOrder.getProvinceCodes());
            // 判断是排除还是包含
            if (edmConditionOfOrder.getProvinceOpt() == IncludeState.INCLUDE.getState()) {
                // 包含省份的条件
                if (provincecodes != null && provincecodes.length > 0) {
                    sb.append(" and provincecode in (" + arrayToWhereInStr(provincecodes) + ")");
                }
            } else if (edmConditionOfOrder.getProvinceOpt() == IncludeState.EXCLUDE.getState()) {
                // 排除省份的条件
                if (provincecodes != null && provincecodes.length > 0) {
                    sb.append(" and provincecode not in (" + arrayToWhereInStr(provincecodes) + ")");
                }
            }
        }

        // 判断是否需要排除或包含城市
        // 为1 的时候拼接城市条件
        if (edmConditionOfOrder.getCityIf() == 1) {
            String[] citycodes = MyArrayUtil.strToArray(edmConditionOfOrder.getCityCodes());
            // 判断是排除还是包含
            if (edmConditionOfOrder.getCityOpt() == IncludeState.INCLUDE.getState()) {
                // 包含城市条件
                if (citycodes != null && citycodes.length > 0) {
                    sb.append(" and citycode in (" + arrayToWhereInStr(citycodes) + ")");
                }
            } else if (edmConditionOfOrder.getCityOpt() == IncludeState.EXCLUDE.getState()) {
                // 排除省份条件
                if (citycodes != null && citycodes.length > 0) {
                    sb.append(" and citycode not in (" + arrayToWhereInStr(citycodes) + ")");
                }
            }
        }
        // 拼接 group by
        sb.append(" group by user_device, mailroot, provincecode ");
        sb.append(") t1 ");
        sb.append("left outer join ( ");
        // 排除当前月所有的黑名单
        sb.append("select phone_num as user_device from dw.dw_black_shield where month='" + MyDateUtil.currentYearMonthStr() + "' group by phone_num ");
        //判断需要排除数据编码
        String[] dataCodeArray = MyArrayUtil.strToArray(edmConditionOfOrder.getDataCodes());
        if (dataCodeArray != null && dataCodeArray.length > 0) {
            for (int x = 0; x < dataCodeArray.length; x++) {
                // 获取数据编码的month， 获取数据编码的月份，
                sb.append("union ");
                sb.append("select split(user_device,',')[0] user_device from dw.dw_user_sendrecords_new where 1=1 " +
                        "and month='" + dataCodeArray[x].substring(2, 8) + "' " +
                        "and day='" + dataCodeArray[x].substring(2, 10) + "' " +
                        "and datacode in ('" + dataCodeArray[x] + "') ");
            }
        }
        sb.append(")t2 ");
        sb.append("on t1.user_device=t2.user_device where t2.user_device is null ");
        sb.append("group by t1.user_device,t1.mailroot,t1.provincecode ");
        // 拼接要查询的数据量
        if (edmConditionOfOrder.getLimitNum() != null && edmConditionOfOrder.getLimitNum() > 0) {
            sb.append(" limit " + edmConditionOfOrder.getLimitNum());
        }

        return sb.toString();
    }

    /**
     * 将数组编程 where in 条件
     *
     * @param strArray
     * @return
     */
    private String arrayToWhereInStr(String[] strArray) {
        String strResult = "";
        for (int i = 0; i < strArray.length; i++) {
            if (i == strArray.length - 1) {
                strResult += "'" + strArray[i] + "'";
            } else {
                strResult += "'" + strArray[i] + "',";
            }
        }
        return strResult;
    }
}
