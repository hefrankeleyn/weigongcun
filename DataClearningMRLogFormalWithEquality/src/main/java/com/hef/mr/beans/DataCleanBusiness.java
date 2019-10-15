package com.hef.mr.beans;

import java.io.Serializable;

/**
 * @Date 2019-09-29
 * @Author lifei
 */
public class DataCleanBusiness implements Serializable {

    private String businessName;
    private String hdfsInputPath;
    private String hdfsOutPutPath;

    private static final Long serialVersionUID = -21115025L;

    public DataCleanBusiness(String businessName, String hdfsInputPath, String hdfsOutPutPath) {
        this.businessName = businessName;
        this.hdfsInputPath = hdfsInputPath;
        this.hdfsOutPutPath = hdfsOutPutPath;
    }

    public DataCleanBusiness() {
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getHdfsInputPath() {
        return hdfsInputPath;
    }

    public void setHdfsInputPath(String hdfsInputPath) {
        this.hdfsInputPath = hdfsInputPath;
    }

    public String getHdfsOutPutPath() {
        return hdfsOutPutPath;
    }

    public void setHdfsOutPutPath(String hdfsOutPutPath) {
        this.hdfsOutPutPath = hdfsOutPutPath;
    }

    @Override
    public String toString() {
        return "DataCleanBusiness{" +
                "businessName='" + businessName + '\'' +
                ", hdfsInputPath='" + hdfsInputPath + '\'' +
                ", hdfsOutPutPath='" + hdfsOutPutPath + '\'' +
                '}';
    }
}
