package com.application.usedallea.zzim.service;

import com.application.usedallea.zzim.dto.ZzimDTO;


public interface ZzimService {

    ZzimDTO addZzim(ZzimDTO zzimDTO);

    ZzimDTO deleteZzim(ZzimDTO zzimDTO);

    int findZzimCount(long productId);

    boolean isCheckedZzim(long zzimId,String userId);


}


