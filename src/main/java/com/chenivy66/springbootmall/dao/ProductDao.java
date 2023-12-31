package com.chenivy66.springbootmall.dao;


import com.chenivy66.springbootmall.dto.ProductQueryPamas;
import com.chenivy66.springbootmall.dto.ProductRequest;
import com.chenivy66.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {
    Integer countProducts(ProductQueryPamas productQueryPamas);
    List<Product> getProducts(ProductQueryPamas productQueryPamas);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);

    void deleteProduct(Integer productId);
}
