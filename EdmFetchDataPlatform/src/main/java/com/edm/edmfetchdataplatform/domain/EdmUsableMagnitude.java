package com.edm.edmfetchdataplatform.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * edm可发量
 * @Date 2019-05-16
 * @Author lifei
 */
public class EdmUsableMagnitude implements Serializable {

    private static final Long serialVersionUID = -200625L;

    // 目标的名称
    private String target;

    // 可发的量级
    private Long magnitude;

    // 日期
    private Date dt;

    // 目标的名称描述
    private String description;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Long getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Long magnitude) {
        this.magnitude = magnitude;
    }

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "EdmUsableMagnitude{" +
                "target='" + target + '\'' +
                ", magnitude=" + magnitude +
                ", dt=" + dt +
                ", description='" + description + '\'' +
                '}';
    }
}
