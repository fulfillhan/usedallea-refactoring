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
    public ZzimDTO addZzim(long productId, String userId) {
        ZzimDTO zzimDTO = new ZzimDTO();

        Zzim newZzim = new Zzim(productId,userId);
        boolean isAlreadyZzim = isCheckedZzim(productId,userId);

        if (isAlreadyZzim) {  //찜이 있으면
            zzimDTO.setZzimStatus(true);
        }else{
            zzimRepository.save(newZzim);
            zzimDTO.setZzimStatus(false);
        }

        Zzim zzim = zzimRepository.findByProductIdAndUserId(newZzim);
        int zzimCount =findZzimCount(zzim.getProductId()) ;
        zzimDTO.setZzimCount(zzimCount);

        return zzimDTO;
    }

/*    @Override
    public ZzimDTO addZzim(long productId, String userId) {
        ZzimDTO zzimResponseDTO = new ZzimDTO();

        Zzim newZzim = new Zzim(productId, userId);
        boolean isAlreadyZzim = isCheckedZzim(productId, userId);

        if(isAlreadyZzim){


        } else {
            zzimRepository.save(newZzim);
            zzimResponseDTO.setZzimStatus(false);
        }

        int zzimCount = zzimRepository.findZzimCount(newZzim);
        zzimResponseDTO.setZzimCount(zzimCount);

        return zzimResponseDTO;
    }*/

    @Override
    public ZzimDTO deleteZzim(long zzimId,String userId) {
        ZzimDTO zzimDTO = new ZzimDTO();
       // Zzim zzim = new Zzim(productId, userId);
        Zzim existedZzim = zzimRepository.findZzimById(zzimId);

        boolean isAlreadyZzim = isCheckedZzim(existedZzim.getProductId(),userId);
        if (isAlreadyZzim) {  // 이미 찜한 상태라면 삭제
            zzimRepository.delete(existedZzim);
            zzimDTO.setZzimStatus(false);
        }

        int zzimCount = findZzimCount(existedZzim.getProductId());
        zzimDTO.setZzimCount(zzimCount);

        return zzimDTO;
    }

    @Override
    public int findZzimCount(long productId) {
        return zzimRepository.countByProductId(productId);
    }

    @Override
    public boolean isCheckedZzim(long productId, String userId) {
        Zzim zzim = new Zzim(productId,userId);
        Zzim existedZzim =zzimRepository.findByProductIdAndUserId(zzim);
        if (existedZzim != null) {
            return true;
        }
        return false;
    }

    @Override
    public ZzimResponseDTO findZzimStatus(long productId, String userId) {
        ZzimResponseDTO zzimResponseDTO = new ZzimResponseDTO();
        Zzim zzim = new Zzim(productId,userId);
        Zzim existedZzim = zzimRepository.findByProductIdAndUserId(zzim);
        zzimResponseDTO.setZzimId(existedZzim.getZzimId());
        return zzimResponseDTO;
    }


}
