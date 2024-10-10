package com.application.usedallea.product.service;

import com.application.usedallea.img.Service.ImgService;
import com.application.usedallea.img.domain.entity.Img;
import com.application.usedallea.img.domain.repository.ImgRepository;
import com.application.usedallea.img.dto.ImgRegisterDTO;
import com.application.usedallea.product.domain.entity.Product;
import com.application.usedallea.product.domain.repository.ProductRepository;
import com.application.usedallea.home.dto.HomePageProductDTO;
import com.application.usedallea.product.dto.ProductDetailDTO;
import com.application.usedallea.product.dto.ProductRegisterDTO;
import com.application.usedallea.product.dto.ProductStatusDTO;
import com.application.usedallea.product.dto.ProductUpdateDTO;
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

import static com.application.usedallea.product.service.ProductStatus.*;
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
                            ProductRegisterDTO productDTO,
                            ImgRegisterDTO productImgDTO) throws IOException {
        long imgId = productImgService.saveImg(uploadImg, productImgDTO);
        Product newProduct = Product.from(imgId, productDTO);
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
    public void updateProuduct(ProductUpdateDTO productUpdateDTO) {
        Product existedProduct = productRepository.findById(productUpdateDTO.getProductId());
        if (existedProduct == null) {
            throw new RuntimeException("상품이 존재하지 않습니다.");
        }

        Product updatedProduct = existedProduct.toBuilder()
                .productId(productUpdateDTO.getProductId())
                .title(productUpdateDTO.getTitle())
                .category(productUpdateDTO.getCategory())
                .qualityCondition(productUpdateDTO.getQualityCondition())
                .price(productUpdateDTO.getPrice())
                .description(productUpdateDTO.getDescription())
                .build();

        productRepository.update(updatedProduct);
    }

/*    @Override
    public Map<String,Object> updateProductStatus(ProductStatusDTO productStatusDTO) {
        Map<String, Object> response = new HashMap<>();
        ProductStatus status = productStatusDTO.getStatus();
        String script = productStatusDTO.getScript();

        switch (status){
            case 판매중->{
                updateStatus(productStatusDTO);
                script = "해당 상품이 '판매중'으로 변경되었습니다.";
            }
            case 판매완료 -> {
                updateStatus(productStatusDTO);
                script = "해당 상품이 '판매완료'로 변경되었습니다.";
            }
            case 삭제 -> {
                updateStatus(productStatusDTO);
                script = "해당 상품이 삭제되었습니다.";
                response.put("isdeleted",true);
            }

        }

        Product product = productRepository.findById(productStatusDTO.getProductId());
        response.put("status",String.valueOf(status));
        response.put("script",script);
        return response;
    }*/

    @Override
    public Map<String,Object> updateProductStatus(ProductStatusDTO productStatusDTO) {
        Map<String, Object> response = new HashMap<>();
        ProductStatus status = productStatusDTO.getStatus();

        updateStatus(productStatusDTO);

        if(status == 삭제){
            response.put("isDeleted",true);
        }

        response.put("status",String.valueOf(status));
        response.put("message",status.getMessage());

        return response;
    }

    private void updateStatus(ProductStatusDTO productStatusDTO) {
        ProductStatus status = productStatusDTO.getStatus();
        long productId = productStatusDTO.getProductId();

        Product existedProduct = productRepository.findById(productId);
        Product updateProduct;
        if(ProductStatus.삭제 == status){
             updateProduct = existedProduct.toBuilder()
                    .status(status.name())
                    .validatedYn("n").build();
        }else{
             updateProduct = existedProduct.toBuilder()
                    .status(status.name())
                    .validatedYn(existedProduct.getValidatedYn()).build();
        }

        productRepository.updateStatusAndValidation(updateProduct);
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
