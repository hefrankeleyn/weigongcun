package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmApplyFile;

import java.util.List;

/**
 *
 */
public interface EdmApplyFileService {
    /**
     * 保存一个 edmApplyFile
     * @param edmApplyFile
     */
    void saveEdmApplyFile(EdmApplyFile edmApplyFile);

    /**
     * 保存多个 edmApplyFile
     * @param edmApplyFiles
     */
    void saveEdmApplyFiles(EdmApplyFile[] edmApplyFiles);

    /**
     * 根据oid查询附件
     * @param oid
     * @return
     */
    List<EdmApplyFile> findEdmApplyFilesByOid(String oid);
}
