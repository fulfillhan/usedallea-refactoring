package com.application.usedallea.member.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MemberDTO {
	
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
	private String personalInfoYn;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private int productCnt;
}
