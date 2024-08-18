package com.application.usedallea.member.v2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRegisterDTO {

    private String userId;

    private String nickname;

    private String password;

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
