package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmTargetDescription;

import java.util.List;

public interface EdmTargetDescriptionService {

    /**
     * 查询所有的用户维度
     * @return
     */
    List<EdmTargetDescription> findAllEdmTargetDescription();

    /**
     * 根据目标查询所有的描述
     * @param target
     * @return
     */
    String findDescriptionByTarget(String target);

}
