package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.domain.EdmApplyFile;
import com.edm.edmfetchdataplatform.mapper.EdmApplyFileMapper;
import com.edm.edmfetchdataplatform.service.EdmApplyFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 申请流转单的附件 service
 *
 * @Date 2019-06-20
 * @Author lifei
 */
@Service
public class EdmApplyFileServiceImpl implements EdmApplyFileService {


    @Autowired(required = false)
    private EdmApplyFileMapper edmApplyFileMapper;

    /**
     * 保存一个
     *
     * @param edmApplyFile
     */
    @Override
    public void saveEdmApplyFile(EdmApplyFile edmApplyFile) {
        if (edmApplyFile != null) {
            edmApplyFileMapper.saveEdmApplyFile(edmApplyFile);
        }
    }

    /**
     * 保存多个 edmApplyFile
     *
     * @param edmApplyFiles
     */
    @Override
    public void saveEdmApplyFiles(EdmApplyFile[] edmApplyFiles) {
        if (edmApplyFiles != null && edmApplyFiles.length > 0) {
            for (EdmApplyFile edmApplyFile :
                    edmApplyFiles) {
                saveEdmApplyFile(edmApplyFile);
            }
        }

    }

    @Override
    public List<EdmApplyFile> findEdmApplyFilesByOid(String oid) {
        List<EdmApplyFile> edmApplyFiles = edmApplyFileMapper.findEdmOrderFilesByOid(oid);
        return edmApplyFiles;
    }
}
