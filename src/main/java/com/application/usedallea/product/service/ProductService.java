package com.application.usedallea.product.service;


import com.application.usedallea.img.dto.ImgRegisterDto;
import com.application.usedallea.home.dto.HomePageProductDTO;
import com.application.usedallea.product.dto.ProductDetailDTO;
import com.application.usedallea.product.dto.ProductRegisterDto;
import com.application.usedallea.utils.dto.PaginationDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ProductService {

    long saveProduct(List<MultipartFile> uploadImg,
                     ProductRegisterDto productDto,
                     ImgRegisterDto productImgDto) throws IOException;


    ProductDetailDTO findByProductId(long productId, boolean isCheckReadCnt);

    List<HomePageProductDTO> getProductList(Map<String, Object> searchInfoMap);

    List<String> getImgUUIDList(long productId);

    List<String> findImgListById(long imgID);

    PaginationDTO getHomePageProducts(String searchWord, int currentPageNumber, int onePageProductCount);
}
