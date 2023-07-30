package com.chenivy66.springbootmall.dto;

public class UserQueryPamas {
    String search;
    String orderBy;
    
    public String getSearch() {
        return search;
    }
    public String getOrderBy() {
        return orderBy;
    }
    public void setSearch(String search) {
        this.search = search;
    }
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
    
}
