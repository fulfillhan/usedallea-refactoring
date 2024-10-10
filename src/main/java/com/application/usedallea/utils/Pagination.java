package com.application.usedallea.utils;

import com.application.usedallea.utils.dto.PaginationDTO;

public interface Pagination {
    int getStartProductIdx();
    void setPagination(PaginationDTO paginationDTO);

}
