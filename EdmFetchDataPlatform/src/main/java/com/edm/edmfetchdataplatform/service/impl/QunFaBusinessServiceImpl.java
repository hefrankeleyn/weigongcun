package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.domain.QunFaBusiness;
import com.edm.edmfetchdataplatform.mapper.QunFaBusinessMapper;
import com.edm.edmfetchdataplatform.service.QunFaBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 获取群发业务类型的service
 * @Date 2019-07-14
 * @Author lifei
 */
@Service
public class QunFaBusinessServiceImpl implements QunFaBusinessService {


    @Autowired(required = false)
    private QunFaBusinessMapper qunFaBusinessMapper;

    /**
     * 查询所有可用的 QunFaBusiness
     * @return
     */
    @Override
    public List<QunFaBusiness> findAllEnableQunFaBusiness() {
        return qunFaBusinessMapper.findAllEnableQunFaBusiness();
    }

    /**
     * 根据 businessType 查询 QunFaBusiness
     * @param businessType
     * @return
     */
    @Override
    public QunFaBusiness findQunFaBusinessByBusinessType(Integer businessType) {
        if (businessType!=null){
            return qunFaBusinessMapper.findEnalbeQunFaBusinessByBusType(businessType);
        }
        return null;
    }
}
