package com.application.usedallea.zzim.contorller;


import com.application.usedallea.zzim.dto.ZzimResponseDTO;
import com.application.usedallea.zzim.service.ZzimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/product/{productId}/zzim")
@RequiredArgsConstructor
public class ZzimController {

    private final ZzimService zzimService;

    // 찜 추가
    @PostMapping("/add")
    public ResponseEntity<ZzimResponseDTO> add(@PathVariable long productId,
                                               @SessionAttribute(name = "userId", required = false) String userId){

         ZzimResponseDTO zzimDTO= zzimService.addZzim(productId,userId);

        return ResponseEntity.ok(zzimDTO);
    }

    //찜 삭제
    @DeleteMapping("/remove")
    public ResponseEntity<ZzimResponseDTO> remove(@PathVariable long productId,
                                                  @SessionAttribute(name = "userId", required = false) String userId){

        ZzimResponseDTO zzimDTO = zzimService.removeZzim(productId,userId);

       return ResponseEntity.ok(zzimDTO);
    }
}
