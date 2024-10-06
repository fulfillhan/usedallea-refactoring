package com.application.usedallea.product.service;


import com.application.usedallea.img.dto.ImgRegisterDto;
import com.application.usedallea.home.dto.HomePageProductDTO;
import com.application.usedallea.product.dto.ProductDetailDTO;
import com.application.usedallea.product.dto.ProductRegisterDto;
import com.application.usedallea.product.dto.ProductUpdateDto;
import com.application.usedallea.utils.dto.PaginationDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ProductService {

    long saveProduct(List<MultipartFile> uploadImg,
                     ProductRegisterDto productDto,
                     ImgRegisterDto productImgDto) throws IOException;


    ProductDetailDTO findProductDetailWithViewCount(long productId, String userId,boolean isCheckReadCount);

    List<HomePageProductDTO> getProductList(Map<String, Object> searchInfoMap);

    List<String> getImgUUIDList(long productId);

    List<String> findImgListById(long imgID);

    PaginationDTO getHomePageAllProducts(String searchWord, int currentPageNumber, int onePageProductCount);

    PaginationDTO getProductsBySeller(String sellerId, String searchWord, int onePageProductCount, int currentPageNumber);

    void updateProuduct(ProductUpdateDto productUpdateDto);
}
