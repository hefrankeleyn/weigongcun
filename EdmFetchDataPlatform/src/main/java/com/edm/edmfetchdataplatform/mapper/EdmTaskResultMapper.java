package com.edm.edmfetchdataplatform.mapper;

import com.edm.edmfetchdataplatform.domain.EdmTaskResult;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 操作保存结果
 * @Date 2019-07-18
 * @Author lifei
 */
@Mapper
public interface EdmTaskResultMapper {


    /**
     * 保存EdmTaskResult
     * @param edmTaskResult
     */
    @Insert("INSERT INTO `edm_task_result` (`conid`,`ocid`,`user_name`,`status`,`submit_time`,`finish_time`," +
            "`file_path`,`data_code`,`filelinenum`,`topic`,`business_type`,`provincenumsinfo`) " +
            "VALUES (#{conId},#{ocId},#{userName},#{status},#{submitTime},#{finishTime}," +
            "#{filePath},#{dataCode},#{fileLineNum},#{topic},#{businessType},#{provinceNumsInfo})")
    void saveEdmTaskResult(EdmTaskResult edmTaskResult);

    /**
     * 更新EdmTaskResult
     * @param edmTaskResult
     */
    @Update("UPDATE `edm_task_result` SET `conid` = #{conId},`ocid` = #{ocId},`user_name` = #{userName}," +
            "`status` = #{status},`submit_time` = #{submitTime},`finish_time` = #{finishTime}," +
            "`file_path` = #{filePath},`data_code` = #{dataCode},`filelinenum` = #{fileLineNum}," +
            "`topic` = #{topic},`business_type` = #{businessType},`provincenumsinfo` = #{provinceNumsInfo} " +
            "WHERE `task_id` = #{taskId}")
    void updateEdmTaskResultByTaskId(EdmTaskResult edmTaskResult);

    /**
     * 查询所有可用的数据编码
     * @return
     */
    @Select("select `task_id`,`conid`,`ocid`,`user_name`,`status`,`submit_time`,`finish_time`,`file_path`,`data_code`," +
            "`filelinenum`,`topic`,`business_type`,`provincenumsinfo` " +
            "from `edm_task_result` where 1=1 and `status`=2 order by `finish_time`")
    @Results(value = {@Result(column = "task_id", property = "taskId"),
            @Result(column = "conid", property = "conId"),
            @Result(column = "ocid", property = "ocId"),
            @Result(column = "user_name", property = "userName"),
            @Result(column = "status", property = "status"),
            @Result(column = "submit_time", property = "submitTime"),
            @Result(column = "finish_time", property = "finishTime"),
            @Result(column = "file_path", property = "filePath"),
            @Result(column = "data_code", property = "dataCode"),
            @Result(column = "filelinenum", property = "fileLineNum"),
            @Result(column = "topic", property = "topic"),
            @Result(column = "business_type", property = "businessType"),
            @Result(column = "provincenumsinfo", property = "provinceNumsInfo")
    })
    List<EdmTaskResult> findAllEnableEdmTaskResults();


    /**
     * 根据taskid 查询 EdmTaskResult
     * @param taskId
     * @return
     */
    @Select("select `task_id`,`conid`,`ocid`,`user_name`,`status`,`submit_time`,`finish_time`,`file_path`,`data_code`," +
            "`filelinenum`,`topic`,`business_type`,`provincenumsinfo` " +
            "from `edm_task_result` where 1=1 and `task_id`=#{taskId}")
    @Results(value = {@Result(column = "task_id", property = "taskId"),
            @Result(column = "conid", property = "conId"),
            @Result(column = "ocid", property = "ocId"),
            @Result(column = "user_name", property = "userName"),
            @Result(column = "status", property = "status"),
            @Result(column = "submit_time", property = "submitTime"),
            @Result(column = "finish_time", property = "finishTime"),
            @Result(column = "file_path", property = "filePath"),
            @Result(column = "data_code", property = "dataCode"),
            @Result(column = "filelinenum", property = "fileLineNum"),
            @Result(column = "topic", property = "topic"),
            @Result(column = "business_type", property = "businessType"),
            @Result(column = "provincenumsinfo", property = "provinceNumsInfo")
    })
    EdmTaskResult findEdmTaskResultByTaskId(Integer taskId);

    /**
     * 根据ocid查询所有的EdmTaskResult 查询 EdmTaskResult
     * @param ocId
     * @return
     */
    @Select("select `task_id`,`conid`,`ocid`,`user_name`,`status`,`submit_time`,`finish_time`,`file_path`,`data_code`," +
            "`filelinenum`,`topic`,`business_type`,`provincenumsinfo` " +
            "from `edm_task_result` where 1=1 and ocid=#{ocId}")
    @Results(value = {@Result(column = "task_id", property = "taskId"),
            @Result(column = "conid", property = "conId"),
            @Result(column = "ocid", property = "ocId"),
            @Result(column = "user_name", property = "userName"),
            @Result(column = "status", property = "status"),
            @Result(column = "submit_time", property = "submitTime"),
            @Result(column = "finish_time", property = "finishTime"),
            @Result(column = "file_path", property = "filePath"),
            @Result(column = "data_code", property = "dataCode"),
            @Result(column = "filelinenum", property = "fileLineNum"),
            @Result(column = "topic", property = "topic"),
            @Result(column = "business_type", property = "businessType"),
            @Result(column = "provincenumsinfo", property = "provinceNumsInfo")
    })
    List<EdmTaskResult> findEdmTaskResultByOcId(String ocId);

    /**
     * 根据 conId 查询 EdmTaskResult
     * @param conId
     * @return
     */
    @Select("select `task_id`,`conid`,`ocid`,`user_name`,`status`,`submit_time`,`finish_time`,`file_path`,`data_code`," +
            "`filelinenum`,`topic`,`business_type`,`provincenumsinfo` " +
            "from `edm_task_result` where 1=1 and `conid`=#{conId}")
    @Results(value = {@Result(column = "task_id", property = "taskId"),
            @Result(column = "conid", property = "conId"),
            @Result(column = "ocid", property = "ocId"),
            @Result(column = "user_name", property = "userName"),
            @Result(column = "status", property = "status"),
            @Result(column = "submit_time", property = "submitTime"),
            @Result(column = "finish_time", property = "finishTime"),
            @Result(column = "file_path", property = "filePath"),
            @Result(column = "data_code", property = "dataCode"),
            @Result(column = "filelinenum", property = "fileLineNum"),
            @Result(column = "topic", property = "topic"),
            @Result(column = "business_type", property = "businessType"),
            @Result(column = "provincenumsinfo", property = "provinceNumsInfo")
    })
    EdmTaskResult findEdmTaskResultByConId(Integer conId);


    /**
     * 根据conIds 查询所有的 EdmTaskResult
     * @param conIds
     * @return
     */
    @Select({"<script>",
            "select `task_id`,`conid`,`ocid`,`user_name`,`status`,`submit_time`,`finish_time`,`file_path`,`data_code`," +
            "`filelinenum`,`topic`,`business_type`,`provincenumsinfo` " +
            "from `edm_task_result` where 1=1 and `conid` in ",
            "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            " order by `finish_time` ",
            "</script>"
    })
    @Results(value = {@Result(column = "task_id", property = "taskId"),
            @Result(column = "conid", property = "conId"),
            @Result(column = "ocid", property = "ocId"),
            @Result(column = "user_name", property = "userName"),
            @Result(column = "status", property = "status"),
            @Result(column = "submit_time", property = "submitTime"),
            @Result(column = "finish_time", property = "finishTime"),
            @Result(column = "file_path", property = "filePath"),
            @Result(column = "data_code", property = "dataCode"),
            @Result(column = "filelinenum", property = "fileLineNum"),
            @Result(column = "topic", property = "topic"),
            @Result(column = "business_type", property = "businessType"),
            @Result(column = "provincenumsinfo", property = "provinceNumsInfo")
    })
    List<EdmTaskResult> findEdmTaskResultsByConIds(@Param("list") Integer[] conIds);

    /**
     * 根据conIds 查询所有可用的 EdmTaskResult
     * @param conIds
     * @return
     */
    @Select({"<script>",
            "select `task_id`,`conid`,`ocid`,`user_name`,`status`,`submit_time`,`finish_time`,`file_path`,`data_code`," +
            "`filelinenum`,`topic`,`business_type`,`provincenumsinfo` " +
            "from `edm_task_result` where 1=1 and `status`=2 and `conid` in ",
            "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            " order by `finish_time` ",
            "</script>"
    })
    @Results(value = {@Result(column = "task_id", property = "taskId"),
            @Result(column = "conid", property = "conId"),
            @Result(column = "ocid", property = "ocId"),
            @Result(column = "user_name", property = "userName"),
            @Result(column = "status", property = "status"),
            @Result(column = "submit_time", property = "submitTime"),
            @Result(column = "finish_time", property = "finishTime"),
            @Result(column = "file_path", property = "filePath"),
            @Result(column = "data_code", property = "dataCode"),
            @Result(column = "filelinenum", property = "fileLineNum"),
            @Result(column = "topic", property = "topic"),
            @Result(column = "business_type", property = "businessType"),
            @Result(column = "provincenumsinfo", property = "provinceNumsInfo")
    })
    List<EdmTaskResult> findEnableEdmTaskResultsByConIds(@Param("list") Integer[] conIds);


    /**
     * 根据数据编码查询EdmTaskResult
     * @param dataCode
     * @return
     */
    @Select("select `task_id`,`conid`,`ocid`,`user_name`,`status`,`submit_time`,`finish_time`,`file_path`,`data_code`," +
            "`filelinenum`,`topic`,`business_type`,`provincenumsinfo` " +
            "from `edm_task_result` where 1=1 and `data_code`='${dataCode}'")
    @Results(value = {@Result(column = "task_id", property = "taskId"),
            @Result(column = "conid", property = "conId"),
            @Result(column = "ocid", property = "ocId"),
            @Result(column = "user_name", property = "userName"),
            @Result(column = "status", property = "status"),
            @Result(column = "submit_time", property = "submitTime"),
            @Result(column = "finish_time", property = "finishTime"),
            @Result(column = "file_path", property = "filePath"),
            @Result(column = "data_code", property = "dataCode"),
            @Result(column = "filelinenum", property = "fileLineNum"),
            @Result(column = "topic", property = "topic"),
            @Result(column = "business_type", property = "businessType"),
            @Result(column = "provincenumsinfo", property = "provinceNumsInfo")
    })
    EdmTaskResult findEdmTaskResultByDataCode(@Param("dataCode") String dataCode);


}
