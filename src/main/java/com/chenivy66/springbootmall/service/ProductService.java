package com.chenivy66.springbootmall.service;

import com.chenivy66.springbootmall.dto.ProductRequest;
import com.chenivy66.springbootmall.model.Product;

public interface ProductService {

    Product getByProductId(Integer product_Id);

    Integer createProduct(ProductRequest productRequest);
}
