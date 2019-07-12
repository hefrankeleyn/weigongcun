package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmCondition;

import java.util.List;

/**
 *
 * 发送消息到消息队列
 * @Date 2019-07-12
 * @Author lifei
 */
public interface EdmAlertService {

    /**
     * 将 EdmCondition 发送到消息中间件
     * @param edmCondition
     */
    void sendEdmCondition(EdmCondition edmCondition);

    /**
     * 将多个EdmCondition发送到消息中间件
     * @param edmConditions
     */
    void sendEdmConditions(List<EdmCondition> edmConditions);
}
