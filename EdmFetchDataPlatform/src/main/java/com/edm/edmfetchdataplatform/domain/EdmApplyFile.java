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
     * 流转单的id
     */
    private String oid;

    public EdmApplyFile() {
    }

    public EdmApplyFile(String filename, String filepath, String originalfilename, String oid) {
        this.filename = filename;
        this.filepath = filepath;
        this.originalfilename = originalfilename;
        this.oid = oid;
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

    @Override
    public String toString() {
        return "EdmApplyFile{" +
                "fid=" + fid +
                ", filename='" + filename + '\'' +
                ", filepath='" + filepath + '\'' +
                ", originalfilename='" + originalfilename + '\'' +
                ", oid='" + oid + '\'' +
                '}';
    }
}
