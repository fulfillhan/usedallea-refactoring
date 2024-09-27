package com.application.usedallea.zzim.service;

import com.application.usedallea.zzim.domain.entity.Zzim;
import com.application.usedallea.zzim.dto.ZzimResponseDTO;

public interface ZzimService {

    ZzimResponseDTO addZzim(long productId, String userId);

    ZzimResponseDTO deleteZzim(long productId, String userId);

    int findZzimCount(long productId,String userId);

    boolean isCheckedZzim(long productId, String userId);
}


