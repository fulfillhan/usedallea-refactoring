package com.application.usedallea.home.controller;

import com.application.usedallea.member.v2.service.UserService;
import com.application.usedallea.product.service.ProductService;
import com.application.usedallea.utils.Pagination;
import com.application.usedallea.utils.dto.PaginationDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usedallea")
@AllArgsConstructor
public class HomeController {

    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/home")
    public String toHomePage(Model model,
                             @RequestParam(name = "searchWord", defaultValue = "") String searchWord,
                             @RequestParam(name = "currentPageNumber", defaultValue = "1") int currentPageNumber) {

        int onePageProductCount = 10;

        PaginationDTO homePageProducts = productService.getHomePageAllProducts(searchWord, currentPageNumber, onePageProductCount);

        model.addAttribute("productList", homePageProducts.getProductList());
        model.addAttribute("allProductCnt", homePageProducts.getTotalProductCount());
        model.addAttribute("allPageCnt", homePageProducts.getAllPageCnt());
        model.addAttribute("startPage", homePageProducts.getStartPage());
        model.addAttribute("endPage", homePageProducts.getEndPage());
        model.addAttribute("onePageProductCnt", onePageProductCount);
        model.addAttribute("currentPageNumber", currentPageNumber);

        return "/common/main";

    }
}
