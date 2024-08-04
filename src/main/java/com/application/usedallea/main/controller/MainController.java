package com.application.usedallea.main.controller;


import com.application.usedallea.member.v1.service.MemberService;
import com.application.usedallea.product.dto.ProductDTO;
import com.application.usedallea.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/usedallea")
public class MainController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private ProductService productService;


    @GetMapping("/main")
    public String main(Model model,
                       @RequestParam(name = "searchWord", defaultValue = "") String searchWord,
                       @RequestParam(name = "currentPageNumber", defaultValue = "1") int currentPageNumber) {


        int onePageProductCnt = 10;

        // 검색어의 개수만큼 map에 담는다
        Map<String, String> searchCntMap = new HashMap<>();
        searchCntMap.put("searchWord", searchWord);  // 쿼리에 지역명을 넣을지 고민

        //전체 게시물의 갯수
        int allProductCnt = productService.getAllProductCnt(searchCntMap);

        // 시작하는 게시물의인덱스
        int startProductIdx = (currentPageNumber - 1) * onePageProductCnt;

        // 모든 페이지의 개수
        int allPageCnt = allProductCnt / onePageProductCnt;
        if (allProductCnt % onePageProductCnt != 0) {
            allPageCnt++;
        }
        //시작 페이지의 수
        int startPage = (currentPageNumber - 1) / 10 * 10 + 1;
        if(startPage == 0){
            startPage = 1;
        }

        // 끝 페이지의 수
        int endPage = startPage + 9;
        if (endPage > allPageCnt) {
            endPage = allPageCnt;
        }
        if(endPage == 0){
            endPage = 1;
        }

        // 페이징 처리된 상품 목록 조회
        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("searchWord", searchWord);
        searchMap.put("startProductIdx", startProductIdx);
        searchMap.put("onePageProductCnt", onePageProductCnt);
        List<ProductDTO> productList = productService.getProductList(searchMap);

        //상품 목록에 시간 정보와 이미지 정보 추가
        LocalDateTime now = LocalDateTime.now(); // 현재시간
        for (ProductDTO products : productList) {
            LocalDateTime createdAt = products.getCreatedAt();  //생성시간
            long minutesAgo = Duration.between(createdAt,now).toMinutes();
            long hoursAgo = Duration.between(createdAt, now).toHours();
            long daysAgo = ChronoUnit.DAYS.between(createdAt, now);
            long weeksAgo = ChronoUnit.WEEKS.between(createdAt,now);
            products.setMinutesAgo(minutesAgo);
            products.setDaysAgo(daysAgo);
            products.setHoursAgo(hoursAgo);
            products.setWeeksAgo(weeksAgo);


            //하나의 상품에 여러가지 이미지인경우
            List<String> productImgUUIDs = productService.getImgUUIDList(products.getProductId());
            if (!productImgUUIDs.isEmpty()) {
                String firstImgUUID = productImgUUIDs.get(0);
                products.setFirstImgUUID(firstImgUUID);
            }
        }

        model.addAttribute("productList", productList);  // 게시글 리스트 모델에 담아서 보내기
        model.addAttribute("allProductCnt", allProductCnt);
        model.addAttribute("allPageCnt", allPageCnt);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("onePageProductCnt", onePageProductCnt);
        model.addAttribute("currentPageNumber", currentPageNumber);

        return "common/main";
    }


}
