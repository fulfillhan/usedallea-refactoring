package com.application.usedallea.img.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class Img {
    private long imgId;

    private long imgSeq;

    private String imgUUID;

    private String originalName;

    public static Img buildImg(Long imgId, long imgSeq, String originalName,String imgUUID){
        return Img.builder()
                .imgId(imgId)
                .imgSeq(imgSeq)
                .imgUUID(imgUUID)
                .originalName(originalName)
                .build();
    }

}
