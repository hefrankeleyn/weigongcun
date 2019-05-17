package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.Edmer;
import com.edm.edmfetchdataplatform.domain.UserDetailsLogin;

/**
 * edmer 的service
 */
public interface EdmerService {

    Edmer findEdmerByEid(Long eid);

    UserDetailsLogin findUserDetailsByUserName(String username);

    UserDetailsLogin findUserDetailsByEmail(String email);
}
