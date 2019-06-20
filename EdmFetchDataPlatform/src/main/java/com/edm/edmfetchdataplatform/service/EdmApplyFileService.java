package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmApplyFile;

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
}
