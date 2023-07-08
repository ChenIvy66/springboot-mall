package com.chenivy66.springbootmall.service.Impl;

import com.chenivy66.springbootmall.dao.ProductDao;
import com.chenivy66.springbootmall.dto.ProductRequest;
import com.chenivy66.springbootmall.model.Product;
import com.chenivy66.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getByProductId(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
         productDao.updateProduct(productId,productRequest);
    }
}
