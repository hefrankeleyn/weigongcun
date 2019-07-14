package com.edm.edmfetchdataplatform.domain;

import java.io.Serializable;

/**
 * edm 各个维度目标的描述
 * @Date 2019-06-03
 * @Author lifei
 */
public class EdmTargetDescription implements Serializable {

    private static final Long serialVersionUID = -2862125L;

    private String target;
    private String description;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "EdmTargetDescription{" +
                "target='" + target + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
