package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmApplyFile;
import com.edm.edmfetchdataplatform.domain.EdmApplyOrder;
import com.edm.edmfetchdataplatform.domain.EdmApplyOrderCheckResult;

/**
 * edm 群发流转单 excel service
 */
public interface EdmExcelService {


    /**
     * 创建EDM 申请流转单的excel, 将excel放到特定的目录下
     * @param edmApplyOrder  流转单申请的部分
     * @param edmApplyOrderCheckResult  流转单的结果
     * @return EdmApplyFile excel的元数据
     */
    EdmApplyFile createEdmApplyExcelOrder(EdmApplyOrder edmApplyOrder,
                                          EdmApplyOrderCheckResult edmApplyOrderCheckResult,
                                          String filePath,
                                          String fileName);


}
