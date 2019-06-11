package com.edm.edmfetchdataplatform.domain.status;

/**
 * @Date 2019-06-11
 * @Author lifei
 */
public enum  ResultStatus {

    SUCCESS(0),FAIL(1);


    private int status;

    ResultStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
