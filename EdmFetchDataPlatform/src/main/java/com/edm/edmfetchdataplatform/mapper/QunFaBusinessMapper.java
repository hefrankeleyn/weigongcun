package com.edm.edmfetchdataplatform.mapper;

import com.edm.edmfetchdataplatform.domain.QunFaBusiness;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 群发类型
 */
@Mapper
public interface QunFaBusinessMapper {

    /**
     * 查询所有可用的群发类型
     * @return
     */
    @Select("SELECT `bid`,`bus_type`,`bus_name`,`status`,`hive_table` FROM `qunfa_business` where status=0")
    @Results(value = {@Result(column = "bid", property = "bId"),
                      @Result(column = "bus_type", property = "businessType"),
                      @Result(column = "bus_name", property = "businessName"),
                      @Result(column = "status", property = "status"),
                      @Result(column = "hive_table", property = "hiveTable")
    })
    List<QunFaBusiness> findAllEnableQunFaBusiness();

    /**
     * 根据bus_type 查询 QunFaBusiness
     * @param businessType
     * @return
     */
    @Select("SELECT `bid`,`bus_type`,`bus_name`,`status`,`hive_table` FROM `qunfa_business` where bus_type=#{businessType}")
    @Results(value = {@Result(column = "bid", property = "bId"),
            @Result(column = "bus_type", property = "businessType"),
            @Result(column = "bus_name", property = "businessName"),
            @Result(column = "status", property = "status"),
            @Result(column = "hive_table", property = "hiveTable")
    })
    QunFaBusiness findEnalbeQunFaBusinessByBusType(Integer businessType);


}
