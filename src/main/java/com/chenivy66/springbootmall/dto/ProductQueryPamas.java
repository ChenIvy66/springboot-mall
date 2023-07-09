package com.chenivy66.springbootmall.dto;

import com.chenivy66.springbootmall.constant.ProductCategory;

public class ProductQueryPamas {
    ProductCategory category;
    String search;

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
