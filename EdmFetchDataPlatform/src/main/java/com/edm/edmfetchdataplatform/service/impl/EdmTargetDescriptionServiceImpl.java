package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.domain.EdmTargetDescription;
import com.edm.edmfetchdataplatform.mapper.EdmTargetDescriptionMapper;
import com.edm.edmfetchdataplatform.service.EdmTargetDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Date 2019-06-05
 * @Author lifei
 */
@Service
public class EdmTargetDescriptionServiceImpl implements EdmTargetDescriptionService {

    @Autowired(required = false)
    private EdmTargetDescriptionMapper edmTargetDescriptionMapper;
    @Override
    public List<EdmTargetDescription> findAllEdmTargetDescription() {
        return edmTargetDescriptionMapper.findAllEdmTargetDescription();
    }

    @Override
    public String findDescriptionByTarget(String target) {
        if(target == null){
            return "";
        }
        return edmTargetDescriptionMapper.findEdmDimensionDescriptionByTarget(target);
    }


    @Override
    public List<EdmTargetDescription> findEdmTargetDescriptionsByTargets(String[] targets) {
        if (targets == null || targets.length == 0){
            return null;
        }
        return edmTargetDescriptionMapper.findEdmTargetDescriptionsByTarget(targets);
    }


}
