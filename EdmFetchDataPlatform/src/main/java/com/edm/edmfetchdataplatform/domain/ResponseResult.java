package com.edm.edmfetchdataplatform.domain;

import com.edm.edmfetchdataplatform.domain.status.ResultStatus;

import java.io.Serializable;

/**
 * @Date 2019-06-11
 * @Author lifei
 */
public class ResponseResult implements Serializable {

    public static final int success = 0;
    public static final int fail = 1;
    private ResultStatus status;
    private Object result;
    private String info;

    public ResponseResult(ResultStatus status, Object result) {
        this.status = status;
        this.result = result;

        if(status.getStatus() == 0){
            this.info = "success";
        }else {
            this.info = "fail";
        }

    }

    public ResultStatus getStatus() {
        return status;
    }

    public void setStatus(ResultStatus status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "status='" + status + '\'' +
                ", result=" + result +
                ", info='" + info + '\'' +
                '}';
    }
}
