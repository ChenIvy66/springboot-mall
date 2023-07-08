package com.chenivy66.springbootmall.dao;

import com.chenivy66.springbootmall.dto.ProductRequest;
import com.chenivy66.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);
}
