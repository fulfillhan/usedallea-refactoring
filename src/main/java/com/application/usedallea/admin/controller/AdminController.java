package com.application.usedallea.admin.controller;

import com.application.usedallea.admin.dto.AdminDTO;
import com.application.usedallea.admin.service.AdminService;
import com.application.usedallea.member.v1.dto.MemberDTO;
import com.application.usedallea.member.v1.service.MemberService;
import com.application.usedallea.product.dto.ProductDTO;
import com.application.usedallea.product.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ProductService productService;


    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request, @RequestBody AdminDTO adminDTO) {
        String validateLogin = "n";
        if (adminService.login(adminDTO)) {
            HttpSession session = request.getSession();
            session.setAttribute("adminId", adminDTO.getAdminId());
            validateLogin = "y";
        }
        return validateLogin;
    }

    @GetMapping("/userList")
    public String userList (Model model,
                        @RequestParam(name = "onePageViewCnt", defaultValue = "20") int onePageViewCnt,
                        @RequestParam(name="currentPageNumber", defaultValue = "1") int currentPageNumber) {



        //전체 사용자 인원수
        int allUserCnt = memberService.getAllUserCnt();

        int allPageCnt = allUserCnt / onePageViewCnt;
        if(allUserCnt % onePageViewCnt  != 0 ){
            allPageCnt ++;
        }

        int startPage = (currentPageNumber-1)/10 *10 +1;
        if(startPage == 0){
            startPage =1;
        }

        int endPage = startPage + 9;
        if(endPage > allPageCnt){
            endPage = allPageCnt;
        }
        if(endPage == 0){
            endPage = 1;
        }

        int startViewIdx = (currentPageNumber-1)*onePageViewCnt;

        //회원 목록을 가지고 와야함.
        Map<String, Integer> searchMap = new HashMap<>();
        searchMap.put("startViewIdx",startViewIdx);
        searchMap.put("onePageViewCnt",onePageViewCnt);
        List<MemberDTO> memberList = memberService.memberList(searchMap);
        //저체 회원의 상품의 갯수
        for (MemberDTO member : memberList){
            String sellerId = member.getUserId();
            int productCnt = productService.getProducCntByUser(sellerId);
            member.setProductCnt(productCnt);

            if(member.getActiveYn().equals("n")) member.setActiveYn("비할성화");
            if(member.getActiveYn().equals("y")) member.setActiveYn("활성화");
        }

      model.addAttribute("memberList", memberList);
      model.addAttribute("allUserCnt", allUserCnt);
      model.addAttribute("allPageCnt", allPageCnt);
      model.addAttribute("startPage", startPage);
      model.addAttribute("endPage", endPage);
      model.addAttribute("startViewIdx", startViewIdx);
      model.addAttribute("currentPageNumber",currentPageNumber);
      model.addAttribute("onePageViewCnt",onePageViewCnt);



        return "admin/userListPage";
    }

    @PostMapping("/removeUser")
    @ResponseBody
    public String removeUser(@RequestParam("userId") String userId) {

        try {
            memberService.removeUser(userId);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "errors";
        }
    }



    @GetMapping("/productList")
    public String productList(Model model,
                              @RequestParam(name="searchWord", defaultValue = "") String searchWord,
                              @RequestParam(name="currentPageNumber",defaultValue = "1")int currentPageNumber){


        int onePageViewCnt = 10;

        // 검색하는 단어
        Map<String,String> searchCntMap = new HashMap<>();
        searchCntMap.put("searchWord",searchWord);
        //searchCntMap.put("status",status);

        //전체 상품 갯수
        int allProductCnt = productService.getAllProductCntByAdmin(searchCntMap);

        int startProductIdx = (currentPageNumber-1)*onePageViewCnt;

        int allPageCnt = allProductCnt / onePageViewCnt;
        if(allPageCnt % onePageViewCnt != 0){
            allPageCnt++;
        }

        int startPage = (currentPageNumber - 1) / 10 * 10 + 1;
        if(startPage == 0){
            startPage = 1;
        }

        int endPage = startPage + 9;
        if (endPage > allPageCnt) {
            endPage = allPageCnt;
        }
        if(endPage == 0){
            endPage = 1;
        }

        //전체 등록된 상품 목록 리스트 가져오기
        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("searchWord",searchWord);
        //searchMap.put("status", status);
        searchMap.put("startProductIdx", startProductIdx);
        searchMap.put("onePageViewCnt", onePageViewCnt);
        List<ProductDTO> productList = productService.getProductListByAdmin(searchMap);

        for (ProductDTO products : productList) {
            List<String> productImgUUIDs = productService.getImgUUIDList(products.getProductId());
            if (!productImgUUIDs.isEmpty()) {
                String firstImgUUID = productImgUUIDs.get(0);
                products.setFirstImgUUID(firstImgUUID);
            }
        }
        model.addAttribute("allProductCnt",allProductCnt);
        model.addAttribute("productList",productList);
        model.addAttribute("allPageCnt", allPageCnt);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("onePageViewCnt", onePageViewCnt);
        model.addAttribute("currentPageNumber", currentPageNumber);
        model.addAttribute("startProductIdx",startProductIdx);


        return "admin/productListPage";
    }

    /*@PostMapping("/removeProduct")
    @ResponseBody
    public String removeProduct(@RequestParam("productId") long productId){

        try {
            productService.removeProduct(productId);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "errors";
        }*/
    //}



}


