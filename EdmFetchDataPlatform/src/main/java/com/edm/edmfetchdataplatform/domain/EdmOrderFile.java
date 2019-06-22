package com.edm.edmfetchdataplatform.domain;

/**
 * @Date 2019-06-18
 * @Author lifei
 */
public class EdmOrderFile {

    // 主键
    private Long fid;

    // 附件的名称
    private String edmFileName;

    // 附件的存储路径
    private String edmFilePath;

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getEdmFileName() {
        return edmFileName;
    }

    public void setEdmFileName(String edmFileName) {
        this.edmFileName = edmFileName;
    }

    public String getEdmFilePath() {
        return edmFilePath;
    }

    public void setEdmFilePath(String edmFilePath) {
        this.edmFilePath = edmFilePath;
    }
}
