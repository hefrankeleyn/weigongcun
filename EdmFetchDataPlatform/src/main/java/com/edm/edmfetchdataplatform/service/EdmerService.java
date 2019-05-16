package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.Edmer;

/**
 * edmer 的service
 */
public interface EdmerService {

    Edmer findEdmerByEid(Long eid);
}
