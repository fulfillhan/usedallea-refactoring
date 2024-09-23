package com.application.usedallea.zzim.service;

import com.application.usedallea.zzim.domain.entity.Zzim;
import com.application.usedallea.zzim.domain.repository.ZzimRepository;
import com.application.usedallea.zzim.dto.ZzimDTO;
import com.application.usedallea.zzim.dto.ZzimResponseDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ZzImServiceImpl implements ZzimService{

    private final ZzimRepository zzimRepository;
    private static final Logger logger = LoggerFactory.getLogger(ZzImServiceImpl.class);

    @Override
    public ZzimResponseDTO addZzim(long productId, String userId) {

        ZzimDTO zzimDTO = new ZzimDTO();
        zzimDTO.setUserId(userId);
        zzimDTO.setProductId(productId);

        Zzim newZzim = new Zzim(zzimDTO);
        boolean isAlreadyZzim = isCheckedZzim(newZzim);  // 찜의 존재여부

        ZzimResponseDTO zzimResponseDTO = new ZzimResponseDTO();

        if(!isAlreadyZzim){
            zzimRepository.save(newZzim);
            int zzimCount = zzimRepository.findZzimCount(productId);
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
            int minusZzimCount = zzimRepository.findZzimCount(productId);
            zzimResponseDTO.setZzimCount(minusZzimCount);
            zzimResponseDTO.setStatus("n");
        }

        return zzimResponseDTO;
    }

    //상품에 대한 찜이 있는지 확인
    private boolean isCheckedZzim(Zzim zzim){
        boolean isCheckedZzim = false;  // 기존에 찜이 존재하는지

        int zzimCountById = zzimRepository.findZzimCountById(zzim);
        if (zzimCountById > 1) {
            isCheckedZzim=true;
        }
        return isCheckedZzim;
    }

}
