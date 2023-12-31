package com.chenivy66.springbootmall.dao.Impl;

import com.chenivy66.springbootmall.dao.ProductDao;
import com.chenivy66.springbootmall.dto.ProductQueryPamas;
import com.chenivy66.springbootmall.dto.ProductRequest;
import com.chenivy66.springbootmall.model.Product;
import com.chenivy66.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer countProducts(ProductQueryPamas productQueryPamas) {
        String sql="SELECT count(*)"
                + " from product WHERE 1=1";

        Map<String,Object> map= new HashMap<>();
        addFilterSql(sql,map,productQueryPamas);

        Integer total = namedParameterJdbcTemplate.queryForObject(
            sql, 
            map, 
            Integer.class
            );

        return  total;
    }

    @Override
    public List<Product> getProducts(ProductQueryPamas productQueryPamas) {
        String sql="SELECT product_Id, product_name, category, image_url,"
                + " price, stock, description, created_date, last_modified_date"
                + " from product WHERE 1=1";

        //類別
        Map<String,Object> map= new HashMap<>();
        addFilterSql(sql,map,productQueryPamas);

        //排序
        if(productQueryPamas.getOrderBy()!=null)
        {
            sql=sql + " ORDER BY " + productQueryPamas.getOrderBy();

            if(productQueryPamas.getSort()!=null)
            {
                sql=sql + " " +productQueryPamas.getSort();
            }
        }

        //分頁
        sql=sql + "  LIMIT :limit OFFSET :offset";
        map.put("limit",productQueryPamas.getLimit());
        map.put("offset",productQueryPamas.getOffset());


        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        if(productList.size()>0)
        {
            return productList;
        }
        else {
            return null;
        }
    }

    @Override
    public Product getProductById(Integer productId) {
        String sql="SELECT product_Id, product_name, category, image_url,"
        + " price, stock, description, created_date, last_modified_date"
        + " from product "
        + " WHERE product_Id=:productId";

        Map<String,Object> map= new HashMap<>();
        map.put("productId",productId);

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        if(productList.size()>0)
        {
            return productList.get(0);
        }
        else {
            return null;
        }
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        String sql="INSERT INTO product (product_name, category, image_url,price, stock,"
                + "  description, created_date, last_modified_date)"
                + " VALUES "
                + " (" +
                ":productName," +
                ":category," +
                ":imageUrl," +
                ":price," +
                ":stock," +
                ":description," +
                ":createdDate," +
                ":lastModifiedDate" +
                ")";

        Map<String,Object> map= new HashMap<>();
        map.put("productName",productRequest.getProductName());
        map.put("category",productRequest.getCategory().toString());
        map.put("imageUrl",productRequest.getImageUrl());
        map.put("price",productRequest.getPrice());
        map.put("stock",productRequest.getStock());
        map.put("description",productRequest.getDescription());

        Date now=new Date();
        map.put("createdDate",now);
        map.put("lastModifiedDate",now);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);

        Integer productId = keyHolder.getKey().intValue();

        return productId;
    }

    @Override
    public void updateProduct(Integer productId,ProductRequest productRequest) {
        String sql="UPDATE  product Set " +
                " product_name = :productName," +
                " category = :category," +
                " image_url = :imageUrl,"+
                " price = :price, "+
                " stock = :stock,"+
                " description = :description,"+
                " last_modified_date = :lastModifiedDate" +
                " WHERE product_Id=:productId";

        Map<String,Object> map= new HashMap<>();
        map.put("productId",productId);
        map.put("productName",productRequest.getProductName());
        map.put("category",productRequest.getCategory().toString());
        map.put("imageUrl",productRequest.getImageUrl());
        map.put("price",productRequest.getPrice());
        map.put("stock",productRequest.getStock());
        map.put("description",productRequest.getDescription());

        Date now=new Date();
        map.put("lastModifiedDate",now);

        namedParameterJdbcTemplate.update(sql,map);

    }

    @Override
    public void deleteProduct(Integer productId) {
        String sql="DELETE FROM product "+
                " WHERE product_Id=:productId";

        Map<String,Object> map= new HashMap<>();
        map.put("productId",productId);

        namedParameterJdbcTemplate.update(sql,map);
    }

    private void addFilterSql(String sql,Map<String,Object> map,ProductQueryPamas productQueryPamas){
        //類別

        if(productQueryPamas.getCategory()!=null)
        {
            map.put("category",productQueryPamas.getCategory().name());
            sql=sql + " AND category=:category";
        }

        //搜尋
        if(productQueryPamas.getSearch()!=null)
        {
            map.put("search","%"+productQueryPamas.getSearch()+"%");
            sql=sql + " AND product_name like :search";
        }
    }
}

