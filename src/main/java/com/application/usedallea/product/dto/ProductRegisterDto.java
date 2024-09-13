package com.application.usedallea.product.dto;

import com.application.usedallea.product.domain.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProductRegisterDto {

    private String sellerId;
    private long imgId;
    private String title;
    private String price;
    private String description;
    private String qualityCondition;
    private String category;
    private String status;
    private int readCount;
}
