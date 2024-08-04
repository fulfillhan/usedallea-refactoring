package com.application.usedallea.product.service;

import com.application.usedallea.img.dto.ProductImgDTO;
import com.application.usedallea.img.service.ProductImgService;
import com.application.usedallea.product.dao.ProductDAO;
import com.application.usedallea.product.dto.ProductDTO;
import com.application.usedallea.zzim.dao.ZzimDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {


	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private ZzimDAO zzimDAO;

	@Autowired
	private ProductImgService productImgService;

	private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);


	@Override
	public long createProduct(List<MultipartFile> uploadImg, ProductDTO productDTO, ProductImgDTO productImgDTO) throws Exception, IOException {

		long imgId = productImgService.saveImg(uploadImg, productImgDTO);    // img테이블에 이미지 저장하기 -> 이미지 id 생성
		productDTO.setImgId(imgId);
		//단위 테스트
		//System.out.println(imgId);

		productDTO.setStatus(ProductStatus.판매중.name());                   // 상품의 품질상태 저장하기

		productDAO.createProduct(productDTO);                               //상품 테이블에 이미지 id 저장하여 등록된 상품테이블 모두 저장하기

		return productDAO.getProductId(imgId);

	}

	@Override
	public ProductDTO getProductDetail(long productId, boolean isCheckReadCnt) {

		// 로그인을 하고, 해당 유저가 판매자가 아닌경우에만 조회수 증가
		if (isCheckReadCnt) {
			productDAO.updateReadCnt(productId);                                 // 조회수 증가 readCount
		}

		ProductDTO productDTO = productDAO.getProductDetail(productId);
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime createdAt = productDTO.getCreatedAt();
		long daysAgo = Duration.between(createdAt, now).toDays();
		long hoursAgo = Duration.between(createdAt, now).toHours();
		productDTO.setDaysAgo(daysAgo);
		productDTO.setHoursAgo(hoursAgo);

		return productDTO;
	}

	@Override
	public List<String> getImgUUIDList(long productId) {

		return productDAO.getImgUUIDList(productId);
	}

	@Override
	public void updateProduct(ProductDTO productDTO) {
		productDAO.updateProduct(productDTO);
	}

	@Override
	public void updateValidateProduct(long productId) {
		productDAO.updateValidateProduct(productId);
	}

	@Override
	public int getAllProductCnt(Map<String, String> searchCntMap) {

		return productDAO.getAllProductCnt(searchCntMap);
	}

	@Override
	public List<ProductDTO> getProductList(Map<String, Object> searchMap) {
		return productDAO.getProductList(searchMap);
	}

	@Override
	public int getAllProductCntBySeller(Map<String, String> searchCntMap) {
		return productDAO.getAllProductCntBySeller(searchCntMap);
	}

	@Override
	public List<ProductDTO> getProductListBySeller(Map<String, Object> searchMap) {
		return productDAO.getProductListBySeller(searchMap);
	}

	@Override
	public ProductStatus updateProductStatus(long productId, ProductStatus status) {

		ProductDTO productDTO = new ProductDTO();
		productDTO.setProductId(productId);
		productDTO.setStatus(String.valueOf(status));

		productDAO.updateProductStatus(productDTO);
		return productDAO.getProductStatus(productId);
	}


	@Override
	public int getProducCntByUser(String sellerId) {
		return productDAO.getProductCntByUser(sellerId);
	}

	@Override
	public int getAllProductCntByAdmin(Map<String, String> searchCntMap) {
		return productDAO.getAllProductCntByAdmin(searchCntMap);
	}

	@Override
	public List<ProductDTO> getProductListByAdmin(Map<String, Object> searchMap) {
		return productDAO.getProductListByAdmin(searchMap);
	}

	@Override
	public List<ProductDTO> getProductIdBySeller(String sellerId) {
		return productDAO.getProductIdBySeller(sellerId);
	}




}
