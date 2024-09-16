package com.application.usedallea.utils.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaginationDTO {
    // 각각의 필드 확인하여 작성
    //페이징된 상품 목록
    private List<PaginationDTO> productList;
    private int totalProductCount;
    private int allPageCnt;
    private int startPage;
    private int endPage;
    private int onePageProductCnt;
    private int currentPageNumber;

}
