package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.QunFaBusiness;

import java.util.List;

/**
 * 操作群发业务类型的service
 * @Date 2019-07-14
 * @Author lifei
 */
public interface QunFaBusinessService {

    /**
     * 查询所有可用的群发业务类型
     * @return
     */
    List<QunFaBusiness> findAllEnableQunFaBusiness();

    /**
     * 根据群发业务类型的标识，查询群发业务
     * @param businessType
     * @return
     */
    QunFaBusiness findQunFaBusinessByBusinessType(Integer businessType);
}
