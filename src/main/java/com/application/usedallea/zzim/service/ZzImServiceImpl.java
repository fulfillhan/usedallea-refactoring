package com.application.usedallea.zzim.service;

import com.application.usedallea.zzim.domain.entity.Zzim;
import com.application.usedallea.zzim.domain.repository.ZzimRepository;
import com.application.usedallea.zzim.dto.ZzimDTO;
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

        ZzimDTO zzimDTO = new ZzimDTO();
        zzimDTO.setUserId(userId);
        zzimDTO.setProductId(productId);
        Zzim newZzim = new Zzim(zzimDTO);

        boolean isAlreadyZzim = isCheckedZzim(productId, userId);

        ZzimResponseDTO zzimResponseDTO = new ZzimResponseDTO();

        if (!isAlreadyZzim) {
            zzimRepository.save(newZzim); // 찜 저장
            zzimResponseDTO.setZzimStatus(false);// 중복
        } else {
            zzimResponseDTO.setZzimStatus(true);
        }

        int zzimCount = zzimRepository.findZzimCount(newZzim);
        zzimResponseDTO.setZzimCount(zzimCount);

        return zzimResponseDTO;
    }

    @Override
    public ZzimResponseDTO deleteZzim(long productId, String userId) {
        ZzimDTO zzimDTO = new ZzimDTO();
        zzimDTO.setUserId(userId);
        zzimDTO.setProductId(productId);
        Zzim zzim = new Zzim(zzimDTO);

        boolean isAlreadyZzim = isCheckedZzim(productId, userId);

        ZzimResponseDTO zzimResponseDTO = new ZzimResponseDTO();
        Zzim existedZzim = zzimRepository.findZzim(zzim);

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
        ZzimDTO zzimDTO = new ZzimDTO();
        zzimDTO.setProductId(productId);
        zzimDTO.setUserId(userId);
        Zzim zzim = new Zzim(zzimDTO);

        Zzim existedZzim = zzimRepository.findZzim(zzim);
        return zzimRepository.findZzimCount(existedZzim);
    }

    @Override
    public boolean isCheckedZzim(long productId, String userId) {
        ZzimDTO zzimDTO = new ZzimDTO();
        zzimDTO.setProductId(productId);
        zzimDTO.setUserId(userId);
        Zzim zzim = new Zzim(zzimDTO);

        Zzim existedZzim = zzimRepository.findZzim(zzim);
        if (existedZzim != null) {
            return true;
        }
        return false;
    }

}
