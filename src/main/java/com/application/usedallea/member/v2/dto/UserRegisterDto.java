package com.application.usedallea.member.v2.dto;

import lombok.Getter;

@Getter
public class UserRegisterDto {

    private String userId;

    private String nickname;

    private String password;  // int 로 바꾸야함

    private String name;

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
