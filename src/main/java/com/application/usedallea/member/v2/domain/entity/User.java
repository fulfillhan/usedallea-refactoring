package com.application.usedallea.member.v2.domain.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class User {
    private String userId;

    private String nickname;

    private String password;

    private String name;

    private String activeYn;

    private String email;

    private String emailStsYn;

    private String phoneNumber;

    private String smsStsYn;

    private String roadAddress;

    private String jibunAddress;

    private String namujiAddress;

    private String zipCode;

    private String personalInfYn;


}
