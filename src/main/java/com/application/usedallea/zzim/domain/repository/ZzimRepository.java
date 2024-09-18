package com.application.usedallea.zzim.domain.repository;

import com.application.usedallea.zzim.domain.entity.Zzim;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ZzimRepository {

    void add(Zzim zzim);

    int findZzimCountByProductId(long productId);

}
