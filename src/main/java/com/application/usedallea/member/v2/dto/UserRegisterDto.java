package com.application.usedallea.member.v2.dto;

import lombok.Getter;

@Getter
public class UserRegisterDto {

    private String userId;

    private String nickname;

    private String password;  // int 로 바꾸야함

    private String name;

    private String email;

    private String emailstsYn;

    private String phoneNumber;

    private String smsstsYn;

    private String roadAddress;

    private String jibunAddress;

    private String namujiAddress;

    private String zipCode;

    private String personalinfoYn;
}
