package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmApplyOrder;
import com.edm.edmfetchdataplatform.domain.EdmOrderCheckers;

/**
 * edm 群发流转单 excel service
 */
public interface EdmExcelService {


    /**
     * 创建EDM 申请流转单的excel
     * @param edmApplyOrder
     */
    void createEdmApplyExcelOrder(EdmApplyOrder edmApplyOrder, EdmOrderCheckers edmOrderCheckers);
}
