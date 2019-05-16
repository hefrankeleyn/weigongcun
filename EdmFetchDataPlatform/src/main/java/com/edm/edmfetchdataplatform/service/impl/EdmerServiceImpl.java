package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.domain.Edmer;
import com.edm.edmfetchdataplatform.mapper.EdmerMapper;
import com.edm.edmfetchdataplatform.service.EdmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Date 2019-05-16
 * @Author lifei
 */
@Service
public class EdmerServiceImpl implements EdmerService {

    @Autowired
    private EdmerMapper edmerMapper;

    @Override
    public Edmer findEdmerByEid(Long eid) {
        return edmerMapper.findEdmerByEid(eid);
    }
}
