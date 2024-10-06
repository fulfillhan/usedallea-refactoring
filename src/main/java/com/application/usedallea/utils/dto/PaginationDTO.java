package com.application.usedallea.utils.dto;

import com.application.usedallea.home.dto.HomePageProductDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaginationDTO {

    private List<HomePageProductDTO> productList;
    private int totalProductCount;
    private int allPageCnt;
    private int startPage;
    private int endPage;

}

