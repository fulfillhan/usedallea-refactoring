package com.application.usedallea.zzim.contorller;


import com.application.usedallea.zzim.dto.ZzimDTO;
import com.application.usedallea.zzim.dto.ZzimResponseDTO;
import com.application.usedallea.zzim.service.ZzimService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Map;

@Controller
@RequestMapping("/product/{productId}/zzim")
@RequiredArgsConstructor
public class ZzimController {

    private final ZzimService zzimService;

    @PostMapping("/add")
    public ResponseEntity<ZzimResponseDTO> add(@PathVariable long productId,
                                                   @SessionAttribute(name = "userId", required = false) String userId){


        //특정 상품 아이디를 기준으로 찜 추가하기
         ZzimResponseDTO zzimResponseDTO= zzimService.addZzim(productId,userId);

        return ResponseEntity.ok().build();
    }


}
