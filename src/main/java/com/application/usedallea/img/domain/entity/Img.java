package com.application.usedallea.img.domain.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Img {
    private long imgId;

    private long imgSeq;

    private String imgUUID;

    private String originalName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static Img buildImg(Long imgId, long imgSeq, String originalName,String imgUUID){
        return Img.builder()
                .imgId(imgId)
                .imgSeq(imgSeq)
                .imgUUID(imgUUID)
                .originalName(originalName)
                .build();
    }

}
