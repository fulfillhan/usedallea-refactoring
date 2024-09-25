package com.application.usedallea.zzim.contorller;


import com.application.usedallea.zzim.dto.ZzimResponseDTO;
import com.application.usedallea.zzim.service.ZzimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/zzim")
@RequiredArgsConstructor
public class ZzimController {

    private final ZzimService zzimService;

    //찜 화면 조회
 /*   @GetMapping("/status/{productId}")
    public ResponseEntity<ZzimResponseDTO> showStatus(@PathVariable long productId,
                                                      @SessionAttribute(name = "userId", required = false) String userId){

        boolean isZzimStatus = zzimService.isZzimAdded(productId,userId);
        int zzimCount = zzimService.findZzimCount(productId);

        ZzimResponseDTO zzimDTO = new ZzimResponseDTO();
        zzimDTO.setZzimStatus(isZzimStatus);
        zzimDTO.setZzimCount(zzimCount);

        return ResponseEntity.ok(zzimDTO);
    }
*/
    // 찜 추가
    @PostMapping("/{productId}")
    public ResponseEntity<ZzimResponseDTO> add(@PathVariable long productId,
                                               @SessionAttribute(name = "userId", required = false) String userId){

         ZzimResponseDTO zzimDTO= zzimService.addZzim(productId,userId);

        return ResponseEntity.ok(zzimDTO);
    }

    //찜 삭제
    @DeleteMapping("/{productId}")
    public ResponseEntity<ZzimResponseDTO> delete(@PathVariable long productId,
                                                  @SessionAttribute(name = "userId", required = false) String userId){

        ZzimResponseDTO zzimDTO = zzimService.deleteZzim(productId,userId);

       return ResponseEntity.ok(zzimDTO);
    }
}
