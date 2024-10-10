package com.application.usedallea.product.dto;

import com.application.usedallea.product.service.ProductStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductStatusDTO {
    private long productId;
    private ProductStatus status;
}
