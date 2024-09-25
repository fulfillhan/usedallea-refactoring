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
public class ZzImServiceImpl implements ZzimService{

    private final ZzimRepository zzimRepository;

    @Override
    public ZzimResponseDTO addZzim(long productId, String userId) {

        ZzimDTO zzimDTO = new ZzimDTO();
        zzimDTO.setUserId(userId);
        zzimDTO.setProductId(productId);

        Zzim newZzim = new Zzim(zzimDTO);
        boolean isAlreadyZzim = isCheckedZzim(newZzim);

        ZzimResponseDTO zzimResponseDTO = new ZzimResponseDTO();

        if(!isAlreadyZzim){
            zzimRepository.save(newZzim); // 찜 저장
            zzimResponseDTO.setZzimStatus(true);// 중복
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
        boolean isAlreadyZzim = isCheckedZzim(zzim);

        //Zzim existedZzim = zzimRepository.findZzim(zzim);

        ZzimResponseDTO zzimResponseDTO = new ZzimResponseDTO();

        if(isAlreadyZzim) {
            zzimRepository.delete(zzim);
            zzimResponseDTO.setZzimStatus(false);
        }else{
            zzimResponseDTO.setZzimStatus(true);
        }

        int zzimCount = zzimRepository.findZzimCount(zzim);
        zzimResponseDTO.setZzimCount(zzimCount);

        return zzimResponseDTO;
    }

    @Override
    public int findZzimCount(long productId) {
        //Zzim existedZzim = zzimRepository.findZzimById(productId);
        ZzimDTO zzimDTO = new ZzimDTO();
        zzimDTO.setProductId(productId);

        Zzim zzim = new Zzim(zzimDTO);
        Zzim existedZzim = zzimRepository.findZzim(zzim);
        return zzimRepository.findZzimCount(existedZzim);
    }

    @Override
    public boolean isZzimAdded(long productId, String userId) {
        ZzimDTO zzimDTO = new ZzimDTO();
        zzimDTO.setProductId(productId);
        zzimDTO.setUserId(userId);

        Zzim zzim = new Zzim(zzimDTO);
        Zzim existingZzim = zzimRepository.findZzimByProductIdAndUserId(zzim);

        if(existingZzim != null){
            return true;
        }
        return false;
    }

    private boolean isCheckedZzim(Zzim zzim){
         Zzim existingZzim = zzimRepository.findZzimByProductIdAndUserId(zzim);
         if(existingZzim != null){
             return true;
         }
        return false;
    }

}
