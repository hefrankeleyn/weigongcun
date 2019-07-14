package com.edm.edmfetchdataplatform.domain;

import com.edm.edmfetchdataplatform.domain.status.ResultStatus;

import java.io.Serializable;

/**
 * 用于封装Controller的查询结果， 用于RESTful
 * @Date 2019-06-11
 * @Author lifei
 */
public class ResponseResult implements Serializable {

    private static final Long serialVersionUID = -27774125L;


    private Integer status;
    private Object result;
    private String info;

    public ResponseResult(ResultStatus resultStatus, Object result) {
        this.result = result;
        this.status = resultStatus.getStatus();
        this.info = resultStatus.getInfo();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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
                "status=" + status +
                ", result=" + result +
                ", info='" + info + '\'' +
                '}';
    }
}
