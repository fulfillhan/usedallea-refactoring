package com.application.usedallea.zzim.service;

import com.application.usedallea.zzim.dto.ZzimDTO;

public interface ZzimService {

    void insertZzim(ZzimDTO zzimDTO);

    int getZzimCount(long productId);

    boolean checkZzim(ZzimDTO zzimDTO);

    void removeZzim(ZzimDTO zzimDTO);
}
