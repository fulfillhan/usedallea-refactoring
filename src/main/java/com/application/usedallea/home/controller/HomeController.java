package com.application.usedallea.home.controller;


import com.application.usedallea.member.v2.service.UserService;
import com.application.usedallea.product.dto.HomePageProductDTO;
import com.application.usedallea.product.service.ProductService;
import com.application.usedallea.utils.Pagination;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/usedallea")
@AllArgsConstructor
public class HomeController {

    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/header")
    public String toHomePageheader() {
        return "common/header";
    }

    @GetMapping("/home")
    public String toHomePage(Model model,
                             @RequestParam(name = "searchWord", defaultValue = "") String searchWord,
                             @RequestParam(name = "currentPageNumber", defaultValue = "1") int currentPageNumber) {

        int onePageProductCount = 10;

        Map<String, String> searchCountMap = new HashMap<>();
        searchCountMap.put("searchWord", searchWord);

        int totalProductCount = productService.getTotalProductCount(searchCountMap);

        Pagination pagination = new Pagination(onePageProductCount, currentPageNumber, totalProductCount);

        Map<String, Object> searchInfoMap = new HashMap<>();
        searchInfoMap.put("searchWord", searchWord);
        searchInfoMap.put("startProductIdx", pagination.getStartProductIdx());
        searchInfoMap.put("onePageProductCnt", onePageProductCount);
        List<HomePageProductDTO> productList = productService.getProductList(searchInfoMap);
        for (HomePageProductDTO productDTO : productList) {
            List<String> imgUUIDList = productService.getImgUUIDList(productDTO.getProductId());
            if (!imgUUIDList.isEmpty()) {
                String firstImgUUID = imgUUIDList.get(0);
                productDTO.setFirstImgUUID(firstImgUUID);
            }
        }

        model.addAttribute("productList", productList);
        model.addAttribute("allProductCnt", totalProductCount);
        model.addAttribute("allPageCnt", pagination.getAllPageCount());
        model.addAttribute("startPage", pagination.getStartPage());
        model.addAttribute("endPage", pagination.getEndPage());
        model.addAttribute("onePageProductCnt", onePageProductCount);
        model.addAttribute("currentPageNumber", currentPageNumber);


        return "/common/main";

    }
}
