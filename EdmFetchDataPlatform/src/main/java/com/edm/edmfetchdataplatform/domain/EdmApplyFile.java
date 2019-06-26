package com.edm.edmfetchdataplatform.domain;

/**
 * edm 流转单的附件
 * @Date 2019-06-20
 * @Author lifei
 */
public class EdmApplyFile {

    /**
     * 主键， 自增
     */
    private Long fid;

    /**
     * 文件名
     */
    private String filename;

    /**
     * 文件路径
     */
    private String filepath;

    /**
     * 原始文件名
     */
    private String originalfilename;

    /**
     * 附件的标识： 默认为 0
     * 0 : 流转单的其他附件
     * 1 : 生成流转单的excel
     */
    private Integer flag = 0;

    /**
     * 流转单的id
     */
    private String oid;

    public EdmApplyFile() {
        this.flag= 0;
    }

    public EdmApplyFile(String filepath, String filename) {
        this.filepath = filepath;
        this.filename = filename;
        this.flag = 0;
    }

    public EdmApplyFile(String filename, String filepath, String originalfilename, Integer flag, String oid) {
        this.fid = fid;
        this.filename = filename;
        this.filepath = filepath;
        this.originalfilename = originalfilename;
        this.flag = flag;
        this.oid = oid;
    }

    public EdmApplyFile(String filename, String filepath, String originalfilename, String oid) {
        this.filename = filename;
        this.filepath = filepath;
        this.originalfilename = originalfilename;
        this.oid = oid;
        this.flag = 0;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getOriginalfilename() {
        return originalfilename;
    }

    public void setOriginalfilename(String originalfilename) {
        this.originalfilename = originalfilename;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "EdmApplyFile{" +
                "fid=" + fid +
                ", filename='" + filename + '\'' +
                ", filepath='" + filepath + '\'' +
                ", originalfilename='" + originalfilename + '\'' +
                ", flag=" + flag +
                ", oid='" + oid + '\'' +
                '}';
    }
}
