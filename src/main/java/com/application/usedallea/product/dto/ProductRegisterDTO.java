package com.application.usedallea.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRegisterDTO {

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
