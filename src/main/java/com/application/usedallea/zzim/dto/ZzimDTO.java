package com.application.usedallea.zzim.dto;

import com.application.usedallea.zzim.domain.entity.Zzim;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZzimDTO {
    private  long zzimId;
    private String userId;
    private long productId;
    private  int zzimCount;
    private String zzimStatus;

    public ZzimDTO set(Zzim zzim){
        ZzimDTO zzimDTO = new ZzimDTO();
        zzimDTO.setZzimId(zzim.getZzimId());
        zzimDTO.setUserId(zzim.getUserId());
        zzimDTO.setProductId(zzim.getProductId());
        return zzimDTO;
    }

}

