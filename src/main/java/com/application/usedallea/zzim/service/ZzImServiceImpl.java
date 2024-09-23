package com.application.usedallea.zzim.service;

import com.application.usedallea.zzim.domain.entity.Zzim;
import com.application.usedallea.zzim.domain.repository.ZzimRepository;
import com.application.usedallea.zzim.dto.ZzimDTO;
import com.application.usedallea.zzim.dto.ZzimResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
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
            zzimRepository.save(newZzim);
            int zzimCount = zzimRepository.findZzimCount(newZzim);
            zzimResponseDTO.setZzimCount(zzimCount);
            zzimResponseDTO.setStatus("y");
        }

        return zzimResponseDTO;
    }

    @Override
    public ZzimResponseDTO deleteZzim(long productId, String userId) {
        ZzimDTO zzimDTO = new ZzimDTO();
        zzimDTO.setUserId(userId);
        zzimDTO.setProductId(productId);

        Zzim existedZzim = zzimRepository.findzzimById(productId);
        boolean isAlreadyZzim = isCheckedZzim(existedZzim);

        ZzimResponseDTO zzimResponseDTO = new ZzimResponseDTO();

        if(isAlreadyZzim){
            zzimRepository.delete(existedZzim);
            int zzimCount = zzimRepository.findZzimCount(existedZzim);
            zzimResponseDTO.setZzimCount(zzimCount);
            zzimResponseDTO.setStatus("n");
        }

        return zzimResponseDTO;
    }

    @Override
    public int findZzimCount(long productId) {
        Zzim existedZzim = zzimRepository.findzzimById(productId);
        return zzimRepository.findZzimCount(existedZzim);
    }

    private boolean isCheckedZzim(Zzim zzim){
         Zzim existingZzim = zzimRepository.findZzimByProductIdAndUserId(zzim);
         if(existingZzim != null){
             return true;
         }
        return false;
    }

}
