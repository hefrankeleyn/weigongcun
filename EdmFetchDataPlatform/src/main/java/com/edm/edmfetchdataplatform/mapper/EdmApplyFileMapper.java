package com.edm.edmfetchdataplatform.mapper;

import com.edm.edmfetchdataplatform.domain.EdmApplyFile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * edmApplyfile Mapper
 * @Date 2019-06-20
 * @Author lifei
 */
@Mapper
public interface EdmApplyFileMapper {


    /**
     * 保存EdmApplyFile
     * @param edmApplyFile
     */
    @Insert("INSERT INTO edm_apply_files (`filename`,`filepath`,`originalfilename`,`oid`) " +
            "VALUES(#{filename},#{filepath},#{originalfilename},#{oid});")
    void saveEdmApplyFile(EdmApplyFile edmApplyFile);


}
