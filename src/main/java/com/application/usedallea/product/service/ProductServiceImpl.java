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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        long productId = newProduct.getProductId();
        return productId;
    }

    @Override
    public ProductRegisterDto findByProductId(long productId, boolean isCheckReadCnt) {
        Product product = productRepository.findByProductId(productId);

        if (isCheckReadCnt) {
            product.increaseReadCount();
        }
        ProductRegisterDto productDTO = ProductRegisterDto.setProduct(product);
        return productDTO;
    }

    // todo 수정 필요  : BindingException: Invalid bound statement (not found)
    //com.application.usedallea.product.domain.repository.ProductRepository.getTotalProductCount
    @Override
    public int getTotalProductCount(Map<String, String> searchCountMap) {
        int totalProductCount = productRepository.getTotalProductCount(searchCountMap);
        return totalProductCount;
    }

    @Override
    public List<ProductRegisterDto> getProductList(Map<String, Object> searchInfoMap) {
        //searchInfo 검색 조건으로 productList 가져오기
        String searchWord = (String) searchInfoMap.get("searchWord");
        int startProductIdx = (int) searchInfoMap.get("startProductIdx");
        int onePageProductCount = (int) searchInfoMap.get("onePageProductCount");

        List<Product> productListBySearchInfo = productRepository.findProductsBySearchInfo();

        List<ProductRegisterDto> productDTOList = new ArrayList<>();
        for (Product product : productListBySearchInfo) {
            ProductRegisterDto productDTO = ProductRegisterDto.setProudctBySearchInfo(product);
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }

    @Override
    public List<String> getImgUUIDList(long productId) {
        Product product = productRepository.findByProductId(productId);
        long imgId = product.getImgId();
        List<String> imgListByUUID = productImgService.findImgByUUID(imgId);
        return imgListByUUID;
    }
}
