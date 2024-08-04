package com.application.usedallea.zzim.dao;

import com.application.usedallea.zzim.dto.ZzimDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ZzimDAO {

    long insertZzim(ZzimDTO zzimDTO);

    int getZzimCount(long productId);

    int getZzimId(ZzimDTO zzimDTO);

    void deleteZzim(ZzimDTO zzimDTO);

    void removeZzim(long productId);
}
