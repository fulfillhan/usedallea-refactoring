package com.application.usedallea.product.service;


import com.application.usedallea.img.dto.ImgRegisterDto;
import com.application.usedallea.product.dto.ProductRegisterDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    long saveProduct(List<MultipartFile> uploadImg,
                     ProductRegisterDto productDto,
                     ImgRegisterDto productImgDto) throws IOException;


    ProductRegisterDto findByProductId(long productId, boolean isCheckReadCnt);
}
