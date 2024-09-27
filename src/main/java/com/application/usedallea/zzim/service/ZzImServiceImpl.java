package com.application.usedallea.zzim.service;

import com.application.usedallea.zzim.domain.entity.Zzim;
import com.application.usedallea.zzim.domain.repository.ZzimRepository;
import com.application.usedallea.zzim.dto.ZzimResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ZzImServiceImpl implements ZzimService {

    private final ZzimRepository zzimRepository;

    @Override
    public ZzimResponseDTO addZzim(long productId, String userId) {
        ZzimResponseDTO zzimResponseDTO = new ZzimResponseDTO();

        Zzim newZzim = new Zzim(productId, userId);
        boolean isAlreadyZzim = isCheckedZzim(productId, userId);

        if (isAlreadyZzim) {
            zzimResponseDTO.setZzimStatus(true);
        } else {
            zzimRepository.save(newZzim);
            zzimResponseDTO.setZzimStatus(false);
        }

        int zzimCount = zzimRepository.findZzimCount(newZzim);
        zzimResponseDTO.setZzimCount(zzimCount);

        return zzimResponseDTO;
    }

    @Override
    public ZzimResponseDTO deleteZzim(long productId, String userId) {
        ZzimResponseDTO zzimResponseDTO = new ZzimResponseDTO();
        Zzim zzim = new Zzim(productId, userId);
        Zzim existedZzim = zzimRepository.findZzim(zzim);

        boolean isAlreadyZzim = isCheckedZzim(productId, userId);
        if (isAlreadyZzim) {  // 이미 찜한 상태라면 삭제
            zzimRepository.delete(existedZzim);
            zzimResponseDTO.setZzimStatus(true);  // 찜 삭제됨
        } else {
            zzimResponseDTO.setZzimStatus(false);  // 찜이 존재하지 않음
        }

        int zzimCount = zzimRepository.findZzimCount(existedZzim);
        zzimResponseDTO.setZzimCount(zzimCount);

        return zzimResponseDTO;
    }

    @Override
    public int findZzimCount(long productId, String userId) {
        Zzim zzim = new Zzim(productId, userId);
        Zzim existedZzim = zzimRepository.findZzim(zzim);
        return zzimRepository.findZzimCount(existedZzim);
    }

    @Override
    public boolean isCheckedZzim(long productId, String userId) {
        Zzim zzim = new Zzim(productId, userId);
        Zzim existedZzim = zzimRepository.findZzim(zzim);
        if (existedZzim != null) {
            return true;
        }
        return false;
    }

}
