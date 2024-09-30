package com.application.usedallea.zzim.service;

import com.application.usedallea.zzim.domain.entity.Zzim;
import com.application.usedallea.zzim.dto.ZzimDTO;
import com.application.usedallea.zzim.dto.ZzimResponseDTO;

public interface ZzimService {

    ZzimDTO addZzim(long productId, String userId);

    ZzimDTO deleteZzim(long zzimId,String userId);

    int findZzimCount(long productId);

    boolean isCheckedZzim(long zzimId,String userId);

    ZzimResponseDTO findZzimStatus(long productId, String userId);

}


