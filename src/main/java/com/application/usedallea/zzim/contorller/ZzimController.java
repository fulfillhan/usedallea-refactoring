package com.application.usedallea.zzim.contorller;


import com.application.usedallea.zzim.dto.ZzimResponseDTO;
import com.application.usedallea.zzim.service.ZzimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;


@Controller
@RequestMapping("/product/{productId}/zzim")
@RequiredArgsConstructor
public class ZzimController {

    private final ZzimService zzimService;

    @PostMapping("/add")
    public ResponseEntity<ZzimResponseDTO> add(@PathVariable long productId,
                                               @SessionAttribute(name = "userId", required = false) String userId){

         ZzimResponseDTO zzimDTO= zzimService.addZzim(productId,userId);

        return ResponseEntity.ok(zzimDTO);
    }

    //todo 찜 삭제하기
}
