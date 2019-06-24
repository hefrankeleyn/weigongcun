package com.edm.edmfetchdataplatform.mapper;

import com.edm.edmfetchdataplatform.domain.EdmApplyFile;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    /**
     * 根据 oid 查询 EdmApplyFile
     * @param oid
     * @return
     */
    @Select("SELECT `fid`,`filename`,`filepath`,`originalfilename`,`oid` FROM `edm_apply_files` " +
            "where oid='#{oid}'")
    @Results(value = {@Result(column = "fid", property = "fid"),
            @Result(column = "filename", property = "filename"),
            @Result(column = "filepath", property = "filepath"),
            @Result(column = "originalfilename", property = "originalfilename"),
            @Result(column = "oid", property = "oid"),
    })
    List<EdmApplyFile> findEdmOrderFilesByOid(String oid);


}
