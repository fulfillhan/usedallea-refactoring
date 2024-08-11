package com.application.usedallea.product.service;

import com.application.usedallea.img.Service.ImgService;
import com.application.usedallea.img.dto.ImgRegisterDto;
import com.application.usedallea.product.domain.entity.Product;
import com.application.usedallea.product.domain.repository.ProductRepository;
import com.application.usedallea.product.dto.ProductRegisterDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ImgService productImgService;
    private final ProductRepository productRepository;

    @Override
    public long saveProduct(List<MultipartFile> uploadImg,
                            ProductRegisterDto productDto,
                            ImgRegisterDto productImgDto) throws IOException {
        long imgId = productImgService.saveImg(uploadImg, productImgDto);
        Product newProduct = Product.createProduct(imgId, productDto);
        productRepository.save(newProduct);
        return newProduct.getProductId();
    }

    @Override
    public ProductRegisterDto findByProductId(long productId, boolean isCheckReadCnt) {
        Product product = productRepository.findByProductId(productId);

        if (isCheckReadCnt) {
            product.increaseReadCount();
        }
        return ProductRegisterDto.setProduct(product);
    }
}
