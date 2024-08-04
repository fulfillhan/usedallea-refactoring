package com.application.usedallea.product.dao;

import com.application.usedallea.product.dto.ProductDTO;
import com.application.usedallea.product.service.ProductStatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductDAO {

	void createProduct(ProductDTO productDTO);

	List<ProductDTO> getAllProductList();

	long getProductId(long imgId);

	ProductDTO 	getProductDetail(long productId);

	void updateReadCnt(long productId);

	List<String> getImgUUIDList(long productId);

	void updateProduct(ProductDTO productDTO);

	void updateValidateProduct(long productId);

    int getAllProductCnt(Map<String, String> searchCntMap);

	List<ProductDTO> getProductList(Map<String, Object> searchMap);

	int getAllProductCntBySeller(Map<String, String> searchCntMap);

	List<ProductDTO> getProductListBySeller(Map<String, Object> searchMap);

	void updateProductStatus(ProductDTO productId);

	List<ProductDTO> getProductIdBySeller(String sellerId);

	ProductStatus getProductStatus(long productId);

    int getProductCntByUser(String sellerId);

	int getAllProductCntByAdmin(Map<String, String> searchCntMap);


	List<ProductDTO> getProductListByAdmin(Map<String, Object> searchMap);

	//void removeProduct(long productId);
}
