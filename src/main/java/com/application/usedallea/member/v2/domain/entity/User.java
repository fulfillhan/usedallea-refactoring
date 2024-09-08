package com.application.usedallea.member.v2.domain.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder(toBuilder = true)
public class User {
    private String userId;

    private String nickname;

    private String password;

    private String name;

    private String activeYn;

    private String email;

    private String emailstsYn;

    private String phoneNumber;

    private String smsstsYn;

    private String roadAddress;

    private String jibunAddress;

    private String namujiAddress;

    private String zipCode;

    private String personalinfoYn;

    //private LocalDateTime updatedAt;


}
