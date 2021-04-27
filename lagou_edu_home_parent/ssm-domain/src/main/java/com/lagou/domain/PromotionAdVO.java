package com.lagou.domain;

/**
 * 接收页面传递广告参数
 */
public class PromotionAdVO {
    //当前页
    private Integer currentPage;

    //每页显示条数
    private Integer pageSize;

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
}
