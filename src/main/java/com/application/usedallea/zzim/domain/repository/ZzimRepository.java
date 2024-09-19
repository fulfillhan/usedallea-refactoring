package com.application.usedallea.zzim.domain.repository;

import com.application.usedallea.zzim.domain.entity.Zzim;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ZzimRepository {

    void save(Zzim zzim);

    int findZzimCountById(Zzim zzim);

    int findZzimCount(long productId);

    Zzim findzzimById(long productId);

    void delete(Zzim zzim);
}
