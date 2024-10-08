package com.application.usedallea.product.dto;

import com.application.usedallea.product.domain.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProductDetailDTO {
    private long productId;
    private String sellerId;
    private long imgId;
    private String title;
    private String price;
    private String description;
    private String qualityCondition;
    private String category;
    private String status;
    private int readCount;
    private String validatedYn;
    private LocalDateTime createdAt;
    private long daysAgo;
    private long hoursAgo;


    public static ProductDetailDTO from(Product product){
        ProductDetailDTO dto = new ProductDetailDTO();
        dto.setProductId(product.getProductId());
        dto.setSellerId(product.getSellerId());
        dto.setImgId(product.getImgId());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setQualityCondition(product.getQualityCondition());
        dto.setCategory(product.getCategory());
        dto.setStatus(product.getStatus());
        dto.setReadCount(product.getReadCount());
        dto.setDaysAgo(product.calculateDaysFromNow());
        dto.setHoursAgo(product.calculateHoursFromNow());
        return dto;
    }
}
