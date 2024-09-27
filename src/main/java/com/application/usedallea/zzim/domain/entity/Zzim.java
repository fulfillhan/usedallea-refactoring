package com.application.usedallea.zzim.domain.entity;

import com.application.usedallea.zzim.dto.ZzimDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Zzim {

        private  long zzimId;
        private String userId;
        private long productId;
        private LocalDateTime createdAt;

        public Zzim(long productId,String userId){
                this.productId = productId;
                this.userId = userId;
        }


}
