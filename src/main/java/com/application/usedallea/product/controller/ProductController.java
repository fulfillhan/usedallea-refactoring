package com.application.usedallea.product.controller;

import com.application.usedallea.img.dto.ImgRegisterDto;
import com.application.usedallea.product.dto.ProductDetailDTO;
import com.application.usedallea.product.dto.ProductRegisterDto;
import com.application.usedallea.product.dto.ProductUpdateDto;
import com.application.usedallea.product.service.ProductService;
import com.application.usedallea.utils.dto.PaginationDTO;
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
        return "redirect:/products/" + productId;
    }

    @GetMapping("/{productId}")
    public String Detail(@PathVariable long productId,
                         @SessionAttribute(name = "userId", required = false) String userId, Model model) {

        if (userId == null) {
            return "redirect:/users/login-form";
        }

        ProductDetailDTO productDto = productService.findProductDetailWithViewCount(productId, userId, true);
        int zzimCount = zzimService.findZzimCount(productDto.getProductId());
        boolean isZzimAdded = zzimService.isCheckedZzim(productId,userId);
        List<String> imgList = productService.findImgListById(productDto.getImgId());

        model.addAttribute("productDTO", productDto);
        model.addAttribute("zzimCount", zzimCount);
        model.addAttribute("isZzimAdded", isZzimAdded);
        model.addAttribute("imgUUIDList", imgList);

        return "product/productDetailBySeller";
    }

    @GetMapping("/{productId]/update")
    public String update(@PathVariable long productId,Model model,
                         @SessionAttribute(name = "userId", required = false) String userId){

        ProductDetailDTO productDto = productService.findProductDetailWithViewCount(productId,userId,false);
        
        model.addAttribute("productDTO",productDto);
        return "product/createOrUpdate";
    }


    @PostMapping("/{productId}/update")
    public String update(@PathVariable long productId,
                         @SessionAttribute(name = "userId", required = false) String sellerId,
                         @ModelAttribute ProductUpdateDto productUpdateDto){
        productUpdateDto.setProductId(productId);
        productUpdateDto.setSellerId(sellerId);

        productService.updateProuduct(productUpdateDto);

        return "redirect:/product/my-store";
    }

    @GetMapping("/my-store")
    public String toMyStore(Model model, @SessionAttribute(name = "userId", required = false) String sellerId,
                            @RequestParam(name = "searchWord", defaultValue = "") String searchWord,
                            @RequestParam(name = "onePageProductCnt", defaultValue = "10") int onePageProductCount,
                            @RequestParam(name = "currentPageNumber", defaultValue = "1") int currentPageNumber) {

        //페이징 메서드
        PaginationDTO productsBySeller = productService.getProductsBySeller(sellerId, searchWord, onePageProductCount, currentPageNumber);

        model.addAttribute("productListBySeller", productsBySeller.getProductList());
        model.addAttribute("allPageCnt", productsBySeller.getAllPageCnt());
        model.addAttribute("startPage", productsBySeller.getStartPage());
        model.addAttribute("endPage", productsBySeller.getEndPage());
        model.addAttribute("onePageProductCnt", onePageProductCount);
        model.addAttribute("currentPageNumber", currentPageNumber);

        return "product/productManager";
    }
}

