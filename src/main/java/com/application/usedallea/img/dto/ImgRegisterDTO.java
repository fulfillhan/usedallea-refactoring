package com.application.usedallea.img.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ImgRegisterDTO {

    long imgId;

    String imgUUID;

    String originalName;

    LocalDateTime createdAt;
}
