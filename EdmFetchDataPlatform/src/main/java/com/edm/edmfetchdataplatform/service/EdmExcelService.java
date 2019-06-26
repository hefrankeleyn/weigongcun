package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmApplyFile;
import com.edm.edmfetchdataplatform.domain.EdmApplyOrder;
import com.edm.edmfetchdataplatform.domain.EdmOrderCheckers;

/**
 * edm 群发流转单 excel service
 */
public interface EdmExcelService {


    /**
     * 创建EDM 申请流转单的excel, 将excel放到特定的目录下
     * @param edmApplyOrder
     * @return EdmApplyFile excel的元数据
     */
    EdmApplyFile createEdmApplyExcelOrder(EdmApplyOrder edmApplyOrder, EdmOrderCheckers edmOrderChecker,String filePath);


}
