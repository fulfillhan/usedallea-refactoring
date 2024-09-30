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

/*    @Override
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
    }*/

    public ZzimDTO addZzim(long productId, String userId) {
        //찜이 이미 존재하는지 확인
        ZzimDTO zzimDTO = new ZzimDTO();
        Zzim newZzim = new Zzim(productId, userId);
        Zzim zzim = zzimRepository.findByProductIdAndUserId(newZzim);
        if (zzim == null) {
            zzimRepository.save(newZzim);
            zzimDTO.setZzimStatus("y");
        }
        int zzimCount = zzimRepository.countByProductId(productId);
        zzimDTO.setZzimCount(zzimCount);

        return zzimDTO;
    }


    @Override
    public ZzimDTO deleteZzim(ZzimDTO zzimDTO) {

        Zzim zzim = new Zzim(zzimDTO.getProductId(), zzimDTO.getUserId());

        //boolean isAlreadyZzim = isCheckedZzim(existedZzim.getProductId(),existedZzim.getUserId());
        Zzim existedZzim = zzimRepository.findByProductIdAndUserId(zzim);
        if (existedZzim != null) {  // 이미 찜한 상태라면 삭제
            zzimRepository.delete(existedZzim);
            zzimDTO.setZzimStatus("n");
        }
        int zzimCount = zzimRepository.countByProductId(zzim.getProductId());
        zzimDTO.setZzimCount(zzimCount);

        return zzimDTO;
    }

    @Override
    public int findZzimCount(long productId) {
        return zzimRepository.countByProductId(productId);
    }

    @Override
    public boolean isCheckedZzim(long productId, String userId) {
        Zzim zzim = new Zzim(productId, userId);
        Zzim existedZzim = zzimRepository.findByProductIdAndUserId(zzim);
        if (existedZzim != null) {
            return true;
        }
        return false;
    }

    @Override
    public ZzimResponseDTO findZzimStatus(long productId, String userId) {
        ZzimResponseDTO zzimResponseDTO = new ZzimResponseDTO();
        Zzim zzim = new Zzim(productId, userId);
        Zzim existedZzim = zzimRepository.findByProductIdAndUserId(zzim);
        zzimResponseDTO.setZzimId(existedZzim.getZzimId());
        return zzimResponseDTO;
    }


}
