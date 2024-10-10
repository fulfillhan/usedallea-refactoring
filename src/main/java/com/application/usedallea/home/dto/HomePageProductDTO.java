package com.application.usedallea.home.dto;

import com.application.usedallea.product.domain.entity.Product;
import com.application.usedallea.product.service.ProductStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class HomePageProductDTO {

    private long productId;
    private String sellerId;
    private String price;
    private String description;
    private String qualityCondition;
    private int readCount;
    private String status;
    private String title;
    private long imgId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private long minutesAgo;
    private long daysAgo;
    private long hoursAgo;
    private long weeksAgo;
    private String FirstImgUUID;
    private int zzimCount;

    public static HomePageProductDTO from(Product product) {
        HomePageProductDTO dto = new HomePageProductDTO();
        dto.setProductId(product.getProductId());
        dto.setSellerId(product.getSellerId());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setQualityCondition(product.getQualityCondition());
        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());
        dto.setReadCount(product.getReadCount());
        dto.setStatus(product.getStatus());
        dto.setTitle(product.getTitle());
        dto.setImgId(product.getImgId());
        return dto;
    }
}
