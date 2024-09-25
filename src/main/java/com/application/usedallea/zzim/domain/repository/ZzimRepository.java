package com.application.usedallea.zzim.domain.repository;

import com.application.usedallea.zzim.domain.entity.Zzim;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ZzimRepository {

    void save(Zzim zzim);

    void delete(Zzim zzim);

    int findZzimCount(Zzim zzim);

    //Zzim findZzimById(long productId);

    Zzim findZzim(Zzim zzim);

    Zzim findZzimByProductIdAndUserId(Zzim zzim);
}
