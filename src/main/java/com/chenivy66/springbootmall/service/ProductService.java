package com.chenivy66.springbootmall.service;

import com.chenivy66.springbootmall.dto.ProductRequest;
import com.chenivy66.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();

    Product getByProductId(Integer product_Id);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);

    void deleteProduct(Integer productId);
}
