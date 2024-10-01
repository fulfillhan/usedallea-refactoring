package com.application.usedallea.zzim.contorller;

import com.application.usedallea.zzim.dto.ZzimDTO;
import com.application.usedallea.zzim.service.ZzimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/zzim")
@RequiredArgsConstructor
public class ZzimController {

    private final ZzimService zzimService;

    // 찜 추가
    @PostMapping("/like")
    public ResponseEntity<ZzimDTO> add(@RequestBody ZzimDTO zzimDTO) {

        ZzimDTO zzimResponseDTO = zzimService.addZzim(zzimDTO);

        if (zzimResponseDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(zzimResponseDTO);
    }

    //찜 삭제
    @DeleteMapping("/unlike")
    public ResponseEntity<ZzimDTO> delete(@RequestBody ZzimDTO zzimDTO) {

        ZzimDTO zzimResponseDTO = zzimService.deleteZzim(zzimDTO);

        if (zzimResponseDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(zzimResponseDTO);
    }
}
