package com.application.usedallea.product.domain.repository;

import com.application.usedallea.product.domain.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductRepository {
    void save(Product product);

    Product findById(long productId);

    int findTotalProductsCount(Map<String, String> searchCountMap);

    List<Product> findProductsList(Map<String, Object> searchInfoMap);

    List<Product> findSellerProductsList(Map<String, Object> searchInfoMap);

    int findTotalProductCountBySeller(Map<String, String> searchCntMap);

    void updateReadCount(Product product);

    void update(Product product);
}
