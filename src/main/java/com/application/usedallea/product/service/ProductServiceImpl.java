package com.application.usedallea.product.service;

import com.application.usedallea.img.Service.ImgService;
import com.application.usedallea.img.dto.ImgRegisterDto;
import com.application.usedallea.product.domain.entity.Product;
import com.application.usedallea.product.domain.repository.ProductRepository;
import com.application.usedallea.product.dto.HomePageProductDTO;
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

    @Override
    public int getTotalProductCount(Map<String, String> searchCountMap) {
        return productRepository.getTotalProductCount(searchCountMap);
    }

/*    @Override
    public List<ProductRegisterDto> getProductList(Map<String, Object> searchInfoMap) {
        //searchInfo 검색 조건으로 productList 가져오기
        String searchWord = (String) searchInfoMap.get("searchWord");
        int startProductIdx = (int) searchInfoMap.get("startProductIdx");
        int onePageProductCount = (int) searchInfoMap.get("onePageProductCount");

        List<Product> productListBySearchInfo = productRepository.findProductsBySearchInfo(searchInfoMap);

        List<ProductRegisterDto> productDTOList = new ArrayList<>();
        for (Product product : productListBySearchInfo) {
            ProductRegisterDto productDTO = ProductRegisterDto.setProudctBySearchInfo(product);
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }*/
    @Override
    public List<HomePageProductDTO> getProductList(Map<String, Object> searchInfoMap) {
        //searchInfo 검색 조건으로 productList 가져오기
        /*String searchWord = (String) searchInfoMap.get("searchWord");
        int startProductIdx = (int) searchInfoMap.get("startProductIdx");
        int onePageProductCnt = (int) searchInfoMap.get("onePageProductCnt");*/

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
}
