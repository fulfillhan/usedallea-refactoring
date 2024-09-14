package com.application.usedallea.product.service;

import com.application.usedallea.img.Service.ImgService;
import com.application.usedallea.img.domain.entity.Img;
import com.application.usedallea.img.domain.repository.ImgRepository;
import com.application.usedallea.img.dto.ImgRegisterDto;
import com.application.usedallea.product.domain.entity.Product;
import com.application.usedallea.product.domain.repository.ProductRepository;
import com.application.usedallea.product.dto.HomePageProductDTO;
import com.application.usedallea.product.dto.ProductDetailDTO;
import com.application.usedallea.product.dto.ProductRegisterDto;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ImgService productImgService;
    private final ProductRepository productRepository;
    private final ImgRepository imgRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public long saveProduct(List<MultipartFile> uploadImg,
                            ProductRegisterDto productDto,
                            ImgRegisterDto productImgDto) throws IOException {
        long imgId = productImgService.saveImg(uploadImg, productImgDto);
        Product newProduct = Product.from(imgId, productDto);
        productRepository.save(newProduct);
        return newProduct.getProductId();
    }

    @Override
    public ProductDetailDTO findByProductId(long productId, boolean isCheckReadCnt) {
        Product product = productRepository.findByProductId(productId);
        if (isCheckReadCnt) {
            product.increaseReadCount();
        }
        return ProductDetailDTO.from(product);
    }

    @Override
    public int getTotalProductCount(Map<String, String> searchCountMap) {
        return productRepository.getTotalProductCount(searchCountMap);
    }

    @Override
    public List<HomePageProductDTO> getProductList(Map<String, Object> searchInfoMap) {

        List<Product> productListBySearchInfo = productRepository.findProductsBySearchInfo(searchInfoMap);
        List<HomePageProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productListBySearchInfo) {
            HomePageProductDTO productDTO = HomePageProductDTO.from(product);
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }

    @Override
    public List<String> getImgUUIDList(long productId) {
        Product product = productRepository.findByProductId(productId);
        long imgId = product.getImgId();
        return productImgService.findImgByUUID(imgId);
    }

    @Override
    public List<String> findImgListById(long imgId) {
        List<String> imgUUIDList = new ArrayList<>();
        Img img = imgRepository.findById(imgId);
        imgUUIDList.add(img.getImgUUID());
        return imgUUIDList;
    }
}
