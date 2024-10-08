package com.application.usedallea.utils;

import com.application.usedallea.utils.dto.PaginationDTO;

public class PaginationImpl implements Pagination{
    private final int onePageProductCount;  //전체 페이지의 상품 갯수
    private final int currentPageNumber; //현재 페이지
    private final int totalProductCount;  // 모든 상품 갯수

    public PaginationImpl( int onePageProductCount, int currentPageNumber, int totalProductCount) {
        this.onePageProductCount = onePageProductCount;
        this.currentPageNumber = currentPageNumber;
        this.totalProductCount = totalProductCount;
    }

    public int getStartProductIdx() {
        return (currentPageNumber - 1) * onePageProductCount;
    }

    public void setPagination(PaginationDTO paginationDTO){;
        paginationDTO.setAllPageCnt(getAllPageCount());
        paginationDTO.setStartPage(getStartPage());
        paginationDTO.setEndPage(getEndPage());
    }

    private int getAllPageCount() {
        int allPageCount = totalProductCount / onePageProductCount;
        if (totalProductCount % onePageProductCount != 0) {
            allPageCount++;
        }
        return allPageCount;
    }

    private int getStartPage() {
        int startPage = (currentPageNumber - 1) / 10 * 10 + 1;
        if (startPage == 0) {
            startPage = 1;
        }
        return startPage;
    }

    private int getEndPage() {
        int endPage = getStartPage() + 9;
        if (endPage > getAllPageCount()) {
            endPage = getAllPageCount();
        }
        if (endPage == 0) {
            endPage = 1;
        }
        return endPage;
    }

}
