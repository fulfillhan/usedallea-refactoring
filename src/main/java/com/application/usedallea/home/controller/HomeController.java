package com.application.usedallea.home.controller;

import com.application.usedallea.member.v2.service.UserService;
import com.application.usedallea.product.dto.HomePageProductDTO;
import com.application.usedallea.product.service.ProductService;
import com.application.usedallea.utils.Pagination;
import lombok.AllArgsConstructor;
import lombok.Getter;
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
    public String toHomePageheader(){
        return  "common/header";
    }

    @GetMapping("/home")
    public String toHomePage(Model model,
                             @RequestParam(name = "searchWord", defaultValue = "") String searchWord,
                             @RequestParam(name = "currentPageNumber", defaultValue = "1") int currentPageNumber) {

        int onePageProductCount = 10;
        //검색어의 수 만큼 map에 담기
        Map<String, String> searchCountMap = new HashMap<>();
        searchCountMap.put("searchWord", searchWord);

        //전체 개시물의 갯수
        int totalProductCount = productService.getTotalProductCount(searchCountMap);

        Pagination pagination = new Pagination(onePageProductCount, currentPageNumber, totalProductCount);

        Map<String, Object> searchInfoMap = new HashMap<>();
        searchInfoMap.put("searchWord", searchWord);
        searchInfoMap.put("startProductIdx", pagination.getStartProductIdx());
        searchInfoMap.put("onePageProductCnt", onePageProductCount);
        //todo 오류 발생:  to get column 'PRICE' from result set. Cause: java.sql.SQLDataException:
        //: Cannot determine value type from string '15,000'
        List<HomePageProductDTO> productList = productService.getProductList(searchInfoMap);
        for (HomePageProductDTO productDTO : productList) {
            List<String> imgUUIDList = productService.getImgUUIDList(productDTO.getProductId());
            if (!imgUUIDList.isEmpty()) {
                String firstImgUUID = imgUUIDList.get(0);
                productDTO.setFirstImgUUID(firstImgUUID);
            }
            productList.add(productDTO);
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
