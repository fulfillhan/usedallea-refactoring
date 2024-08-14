package com.application.usedallea.member.v2.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserModifyDto {

    private String userId;

    private String nickname;

    private String email;

    private String emailStsYn;

    private String phoneNumber;

    private String smsStsYn;

    private LocalDateTime updatedAt;

    private String roadAddress;

    private String jibunAddress;

    private String namujiAddress;

    private String zipCode;
}
