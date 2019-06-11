package com.edm.edmfetchdataplatform.common;

import com.edm.edmfetchdataplatform.domain.Dimension;
import org.junit.Test;

/**
 * @Date 2019-06-03
 * @Author lifei
 */
public class EnumTest {

    @Test
    public void testDimension(){
        String description = Dimension.COMMON_ACTIVE_ONE.getDescription();
        System.out.println(description);
    }
}
