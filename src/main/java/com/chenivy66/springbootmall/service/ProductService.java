package com.chenivy66.springbootmall.service;

import com.chenivy66.springbootmall.constant.ProductCategory;
import com.chenivy66.springbootmall.dto.ProductQueryPamas;
import com.chenivy66.springbootmall.dto.ProductRequest;
import com.chenivy66.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    Integer countProducts(ProductQueryPamas productQueryPamas);
    List<Product> getProducts(ProductQueryPamas productQueryPamas);

    Product getByProductId(Integer product_Id);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);

    void deleteProduct(Integer productId);
}
