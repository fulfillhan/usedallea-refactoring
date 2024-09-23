package com.application.usedallea.product.controller;

import com.application.usedallea.img.dto.ImgRegisterDto;
import com.application.usedallea.product.dto.ProductDetailDTO;
import com.application.usedallea.product.dto.ProductRegisterDto;
import com.application.usedallea.product.service.ProductService;
import com.application.usedallea.zzim.service.ZzimService;
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
    private final ZzimService zzimService;

    @GetMapping("/register-page")
    public String toRegisterPage() {
        return "product/createOrUpdate";
    }
    @PostMapping("/create")
    public String create(@SessionAttribute(name = "userId", required = false) String sellerId,
                         @RequestParam List<MultipartFile> uploadImg,
                         @ModelAttribute ProductRegisterDto productDto,
                         @ModelAttribute ImgRegisterDto productImgDto) throws Exception {
        if (sellerId == null) {
            return "redirect:/login-form";
        }
        productDto.setSellerId(sellerId);
        long productId = productService.saveProduct(uploadImg, productDto, productImgDto);
        return "redirect:/products/"+productId;
    }

    @GetMapping("/{productId}")
    public String Detail(@PathVariable long productId,
                         @SessionAttribute(name = "userId", required = false) String userId, Model model) {

        if (userId == null) {
            return "redirect:/users/login-form";
        }

        ProductDetailDTO productDto = productService.findByProductId(productId, true);
        int zzimCount = zzimService.findZzimCount(productId);
        List<String> imgList = productService.findImgListById(productDto.getImgId());

        model.addAttribute("productDTO", productDto);
        model.addAttribute("zzimCount", zzimCount);
        model.addAttribute("imgUUIDList",imgList);

        return "product/productDetailBySeller";
    }

}

