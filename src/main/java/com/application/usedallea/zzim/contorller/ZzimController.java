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

    @PostMapping("/like")
    public ResponseEntity<ZzimDTO> add(@RequestBody ZzimDTO zzimDTO) {

        ZzimDTO zzimResponseDTO = zzimService.addZzim(zzimDTO);

        if (zzimResponseDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(zzimResponseDTO,HttpStatus.OK);
    }

    @DeleteMapping("/unlike")
    public ResponseEntity<ZzimDTO> delete(@RequestBody ZzimDTO zzimDTO) {

        ZzimDTO zzimResponseDTO = zzimService.deleteZzim(zzimDTO);

        if (zzimResponseDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(zzimResponseDTO,HttpStatus.OK);
    }
}
