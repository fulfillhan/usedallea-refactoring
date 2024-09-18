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
        Zzim zzim = new Zzim(zzimDTO);

        zzimRepository.add(zzim);

       int zzimCount = zzimRepository.findZzimCountByProductId(productId);

       ZzimResponseDTO zzimResponseDTO = new ZzimResponseDTO();
       zzimResponseDTO.setZzimCount(zzimCount);
       zzimResponseDTO.setStatus("n");

        return zzimResponseDTO;
    }

}
