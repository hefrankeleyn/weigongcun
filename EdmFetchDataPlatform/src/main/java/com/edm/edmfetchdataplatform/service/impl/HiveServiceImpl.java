package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.domain.EdmCondition;
import com.edm.edmfetchdataplatform.service.HiveService;

/**
 * 操作hive， 生成数据编码
 * @Date 2019-07-14
 * @Author lifei
 */
public class HiveServiceImpl implements HiveService {

    /**
     * 根据 EdmCondition 中的条件，提取数据，并将数据生成数据编码
     * 步骤：
     *     1. 将EdmCondition 拼接成sql语句；
     *     2. 连接hive执行查询操作，并将查到的数据保存到一张临时表中；
     *     3. 将临时表数据写入到txt文件中；
     *     4. 将txt文件发送到一个目录 （该目录是精准投送平台能够读取的）；
     *     5. 查询临时表：获取各省份及数据量情况；
     *     6. 根据当前时间创建一个数据编码；
     *     7. 将数据编码及临时表的数据写入 提取历史表中；
     *     8. 将“申请人姓名、申请时间、提取结束时间、文件名称（路径是固定的）、数据编码、
     *           当前数据编码的状态（1 为不可用， 2 为可用），提取的业务类型（EDM，账单）、
     *           各省份对应数据量、文件名称、当前活动主题名称、数据名称”属性封装到一个Bean中返回
     * @param edmCondition
     * @return
     */
    @Override
    public String createDataCodeByEdmCondition(EdmCondition edmCondition) {
        return null;
    }
}
