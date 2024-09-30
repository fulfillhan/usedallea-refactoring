package com.application.usedallea.zzim.contorller;


import com.application.usedallea.zzim.dto.ZzimDTO;
import com.application.usedallea.zzim.dto.ZzimResponseDTO;
import com.application.usedallea.zzim.service.ZzimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ZzimController {

    private final ZzimService zzimService;


    @GetMapping("/status/{productId}")
    public ResponseEntity<ZzimResponseDTO> showZzimStatus(@PathVariable long productId,
                                                          @SessionAttribute(name = "userId", required = false) String userId){

        ZzimResponseDTO zzimDTO = zzimService.findZzimStatus(productId,userId);
        return ResponseEntity.ok(zzimDTO);
    }

    // 찜 추가
    @PostMapping("/like")
    public ResponseEntity<ZzimDTO> add(@PathVariable long productId,
                                               @SessionAttribute(name = "userId", required = false) String userId){

         ZzimDTO zzimDTO= zzimService.addZzim(productId,userId);
       // ZzimDTO zzimDTO= zzimService.addZzim(productId,userId);

        return ResponseEntity.ok(zzimDTO);
    }

    //찜 삭제
    @DeleteMapping("/unlike")
    public ResponseEntity<ZzimDTO> delete(@RequestBody ZzimDTO zzimDTO){

        String userId = zzimDTO.getUserId();
        long productId = zzimDTO.getProductId();
        ZzimDTO zzimResponseDTO = zzimService.deleteZzim(productId, userId);

       return ResponseEntity.ok(zzimResponseDTO);
    }
}
