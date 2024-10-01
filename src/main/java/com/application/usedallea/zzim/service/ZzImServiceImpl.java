package com.application.usedallea.zzim.service;

import com.application.usedallea.zzim.domain.entity.Zzim;
import com.application.usedallea.zzim.domain.repository.ZzimRepository;
import com.application.usedallea.zzim.dto.ZzimDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ZzImServiceImpl implements ZzimService {

    private final ZzimRepository zzimRepository;

    public ZzimDTO addZzim(ZzimDTO zzimDTO) {

        Zzim newZzim = new Zzim(zzimDTO.getProductId(), zzimDTO.getUserId());

        Zzim zzim = zzimRepository.findByProductIdAndUserId(newZzim);
        if (zzim == null) {
            zzimRepository.save(newZzim);
            zzimDTO.setZzimStatus("y");
        }
        int zzimCount = zzimRepository.countByProductId(zzimDTO.getProductId());
        zzimDTO.setZzimCount(zzimCount);

        return zzimDTO;
    }


    @Override
    public ZzimDTO deleteZzim(ZzimDTO zzimDTO) {

        Zzim zzim = new Zzim(zzimDTO.getProductId(), zzimDTO.getUserId());

        Zzim existedZzim = zzimRepository.findByProductIdAndUserId(zzim);
        if (existedZzim != null) {
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

}
