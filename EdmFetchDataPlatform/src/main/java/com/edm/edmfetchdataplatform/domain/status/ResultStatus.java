package com.edm.edmfetchdataplatform.domain.status;

/**
 * @Date 2019-06-11
 * @Author lifei
 */
public enum  ResultStatus {

    SUCCESS(0, "SUCCESS"),FAIL(1, "FAIL");


    private int status;
    private String info;

    ResultStatus(int status, String info) {
        this.status = status;
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }
}
