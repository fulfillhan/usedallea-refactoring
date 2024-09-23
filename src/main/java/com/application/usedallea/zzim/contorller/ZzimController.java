package com.application.usedallea.zzim.contorller;


import com.application.usedallea.zzim.dto.ZzimResponseDTO;
import com.application.usedallea.zzim.service.ZzimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products/{productId}")
@RequiredArgsConstructor
public class ZzimController {

    private final ZzimService zzimService;

    // 찜 추가
    @PostMapping("/zzim")
    public ResponseEntity<ZzimResponseDTO> add(@PathVariable long productId,
                                               @SessionAttribute(name = "userId", required = false) String userId){
        System.out.println("productId = "+productId);
         ZzimResponseDTO zzimDTO= zzimService.addZzim(productId,userId);

        return ResponseEntity.ok().body(zzimDTO);
    }

    //찜 삭제
    @DeleteMapping("/zzim")
    public ResponseEntity<ZzimResponseDTO> delete(@PathVariable long productId,
                                                  @SessionAttribute(name = "userId", required = false) String userId){

        ZzimResponseDTO zzimDTO = zzimService.deleteZzim(productId,userId);

       return ResponseEntity.ok().body(zzimDTO);
    }
}
