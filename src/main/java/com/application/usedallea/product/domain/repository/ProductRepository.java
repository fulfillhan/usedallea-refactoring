package com.application.usedallea.product.domain.repository;

import com.application.usedallea.product.domain.entity.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductRepository {


    void save(Product product);

    Product findByProductId(long productId);
}
