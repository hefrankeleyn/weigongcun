package com.edm.edmfetchdataplatform.base;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date 2019-06-29
 * @Author lifei
 */
public abstract class BaseQuery {

    /**
     * 当前页,默认为第1页
     */
    private Integer currentPage=1;

    /**
     * 一页的条数， 默认为10条
     */
    private Integer pageSize = 10;

    /**
     * 查询条件
     */
    private Map<String, Object> keyValues = new HashMap<>();

    /**
     * 创建查询条件
     * @return
     */
    public abstract Map<String, Object> buildWhere();

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, Object> getKeyValues() {
        return keyValues;
    }

    @Override
    public String toString() {
        return "BaseQuery{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", keyValues=" + keyValues +
                '}';
    }
}
