package com.application.usedallea.product.domain.entity;

import com.application.usedallea.product.dto.ProductRegisterDTO;
import com.application.usedallea.product.service.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Product {

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
    private LocalDateTime updatedAt;
    public static Product from(long imgId, ProductRegisterDTO productDto){
        return  Product.builder()
                .imgId(imgId)
                .sellerId(productDto.getSellerId())
                .title(productDto.getTitle())
                .status(ProductStatus.판매중.name())
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .qualityCondition(productDto.getQualityCondition())
                .category(productDto.getCategory())
                .readCount(productDto.getReadCount())
                .build();
    }

    public void increaseReadCount() {
          this.readCount++;
    }

    public long calculateDaysFromNow(){
        return Duration.between(createdAt, LocalDateTime.now()).toDays();
    }
    public long calculateHoursFromNow(){
        return Duration.between(createdAt, LocalDateTime.now()).toHours();
    }
    public long calculateMinutesFromNow(){
        return Duration.between(createdAt,LocalDateTime.now()).toMinutes();
    }

    public long calculateWeeksFromNow(){
        return Duration.between(createdAt,LocalDateTime.now()).toDays() / 7;
    }

}