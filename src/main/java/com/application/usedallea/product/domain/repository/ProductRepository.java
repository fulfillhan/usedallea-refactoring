package com.application.usedallea.product.domain.repository;

import com.application.usedallea.product.domain.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductRepository {
    void save(Product product);
    Product findByProductId(long productId);
    int getTotalProductCount(Map<String, String> searchCountMap);

    List<Product> findProductsBySearchInfo(Map<String, Object> searchInfoMap);
}
