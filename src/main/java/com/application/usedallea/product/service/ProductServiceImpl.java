package com.application.usedallea.product.service;

import com.application.usedallea.img.Service.ImgService;
import com.application.usedallea.img.domain.entity.Img;
import com.application.usedallea.img.domain.repository.ImgRepository;
import com.application.usedallea.img.dto.ImgRegisterDto;
import com.application.usedallea.product.domain.entity.Product;
import com.application.usedallea.product.domain.repository.ProductRepository;
import com.application.usedallea.home.dto.HomePageProductDTO;
import com.application.usedallea.product.dto.ProductDetailDTO;
import com.application.usedallea.product.dto.ProductRegisterDto;
import com.application.usedallea.product.dto.ProductUpdateDto;
import com.application.usedallea.utils.Pagination;
import com.application.usedallea.utils.PaginationImpl;
import com.application.usedallea.utils.dto.PaginationDTO;
import com.application.usedallea.zzim.service.ZzimService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ImgService productImgService;
    private final ZzimService zzimService;
    private final ProductRepository productRepository;
    private final ImgRepository imgRepository;

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
    public ProductDetailDTO findProductDetailWithViewCount(long productId, String userId, boolean isCheckReadCount) {
        Product product = productRepository.findById(productId);

        if (!product.getSellerId().equals(userId) && isCheckReadCount) {
            product.increaseReadCount();
            productRepository.updateReadCount(product);
        }

        return ProductDetailDTO.from(product);
    }

    @Override
    public List<String> findImgListById(long imgId) {
        List<String> imgUUIDList = new ArrayList<>();
        Img img = imgRepository.findById(imgId);
        imgUUIDList.add(img.getImgUUID());
        return imgUUIDList;
    }

    @Override
    public PaginationDTO getHomePageAllProducts(String searchWord, int currentPageNumber, int onePageProductCount) {
        Map<String, String> searchCountMap = new HashMap<>();
        searchCountMap.put("searchWord", searchWord);

        int totalProductCount = productRepository.findTotalProductsCount(searchCountMap);

        //페이징
        Pagination pagination = new PaginationImpl(onePageProductCount, currentPageNumber, totalProductCount);

        Map<String, Object> searchInfoMap = new HashMap<>();
        searchInfoMap.put("searchWord", searchWord);
        searchInfoMap.put("startProductIdx", pagination.getStartProductIdx());
        searchInfoMap.put("onePageProductCnt", onePageProductCount);

        // 상품 리스트 조회
        List<HomePageProductDTO> productList = getProductList(searchInfoMap);
        for (HomePageProductDTO productDTO : productList) {
            List<String> imgUUIDList = getImgUUIDList(productDTO.getProductId());
            if (!imgUUIDList.isEmpty()) {
                String firstImgUUID = imgUUIDList.get(0);
                productDTO.setFirstImgUUID(firstImgUUID);
            }
        }

        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setProductList(productList);
        paginationDTO.setTotalProductCount(totalProductCount);
        pagination.setPagination(paginationDTO);

        return paginationDTO;
    }

    @Override
    public PaginationDTO getProductsBySeller(String sellerId, String searchWord,
                                             int onePageProductCount, int currentPageNumber) {
        Map<String, String> searchCntMap = new HashMap<>();
        searchCntMap.put("searchWord", searchWord);
        searchCntMap.put("sellerId", sellerId);

        int totalProductCountBySeller = productRepository.findTotalProductCountBySeller(searchCntMap);  //특정 판매자의 상품 총 갯수

        //페이징
        Pagination pagination = new PaginationImpl(onePageProductCount, currentPageNumber, totalProductCountBySeller);

        Map<String, Object> searchinfoMap = new HashMap<>();
        searchinfoMap.put("searchWord", searchWord);
        searchinfoMap.put("startProductIdx", pagination.getStartProductIdx());
        searchinfoMap.put("onePageProductCnt", onePageProductCount);
        searchinfoMap.put("sellerId", sellerId);

        List<HomePageProductDTO> productList = getProductList(searchinfoMap);
        for (HomePageProductDTO productDTO : productList) {
            List<String> imgUUIDList = getImgUUIDList(productDTO.getProductId());
            if (!imgUUIDList.isEmpty()) {
                String firstImgUUID = imgUUIDList.get(0);
                productDTO.setFirstImgUUID(firstImgUUID);
            }
            int zzimCount = zzimService.findZzimCount(productDTO.getProductId());
            productDTO.setZzimCount(zzimCount);
        }

        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setProductList(productList);
        paginationDTO.setTotalProductCount(totalProductCountBySeller);
        pagination.setPagination(paginationDTO);

        return paginationDTO;
    }

    @Override
    public void updateProuduct(ProductUpdateDto productUpdateDto) {
        Product existedProduct = productRepository.findById(productUpdateDto.getProductId());
        if(existedProduct == null){
            //어떤 로직?
           throw new RuntimeException("상품이 존재하지 않습니다.");
        }

        Product updatedProduct = existedProduct.toBuilder()
                .productId(existedProduct.getProductId())
                .title(existedProduct.getTitle())
                .category(existedProduct.getCategory())
                .qualityCondition(existedProduct.getQualityCondition())
                .description(existedProduct.getDescription())
                .build();

        productRepository.update(updatedProduct);
    }

    public List<HomePageProductDTO> getProductList(Map<String, Object> searchInfoMap) {
        List<HomePageProductDTO> productDTOList = new ArrayList<>();

        if (searchInfoMap.containsKey("sellerId")) {
            List<Product> productListBySearchInfo = productRepository.findSellerProductsList(searchInfoMap);
            for (Product product : productListBySearchInfo) {
                HomePageProductDTO productDTO = HomePageProductDTO.from(product);
                productDTOList.add(productDTO);
            }
        } else {
            List<Product> productListBySearchInfo = productRepository.findProductsList(searchInfoMap);
            for (Product product : productListBySearchInfo) {
                HomePageProductDTO productDTO = HomePageProductDTO.from(product);
                productDTO.setMinutesAgo(product.calculateMinutesFromNow());
                productDTO.setHoursAgo(product.calculateHoursFromNow());
                productDTO.setDaysAgo(product.calculateDaysFromNow());
                productDTO.setWeeksAgo(product.calculateWeeksFromNow());
                productDTOList.add(productDTO);
            }
        }
        return productDTOList;
    }

    public List<String> getImgUUIDList(long productId) {
        Product product = productRepository.findById(productId);
        long imgId = product.getImgId();
        return productImgService.findImgByUUID(imgId);
    }
}
