package com.application.usedallea.product.controller;

import com.application.usedallea.img.dto.ImgRegisterDto;
import com.application.usedallea.product.dto.ProductRegisterDto;
import com.application.usedallea.product.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/register-page")
    public String toRegisterPage() {
        return "product/createOrUpdate";
    }

    //todo 확인 필요
    @PostMapping("/create")
    public String create(HttpSession session,
                         @RequestParam List<MultipartFile> uploadImg,
                         @ModelAttribute ProductRegisterDto productDto,
                         @ModelAttribute ImgRegisterDto productImgDto) throws Exception {
        String sellerId = (String) session.getAttribute("userId");
        if (sellerId == null) {
            return "redirect:/login-form";
        }
        productDto.setSellerId(sellerId);
        long productId = productService.saveProduct(uploadImg, productDto, productImgDto);
        return "/product/productDetailBySeller"+productId;
    }

    @GetMapping("/{productId}")
    public String Detail(@PathVariable long productId,
                         @SessionAttribute(name = "userId", required = false) String userId, Model model) {

        if (userId == null) {
            return "redirect:/login-form";
        }

        ProductRegisterDto productDto = productService.findByProductId(productId, true);
        //todo 저장한 이미지 여러장 가져오기

        // 뷰로 전달
        model.addAttribute("userId", userId);
        model.addAttribute("productDTO", productDto);

        return "product/productDetailBySeller";
    }

}

