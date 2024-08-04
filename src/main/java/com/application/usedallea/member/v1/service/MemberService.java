package com.application.usedallea.member.v1.service;

import com.application.usedallea.member.v1.dto.MemberDTO;

import java.util.List;
import java.util.Map;

public interface MemberService {

	void registerMember(MemberDTO memberDTO);

	String dupleCheckId(String userId);

	boolean login(MemberDTO memberDTO);

	MemberDTO getMemberDetail(String userId);

	void updateMember(MemberDTO memberDTO);

	void updateDelete(String userId);

	int getAllUserCnt();

    List<MemberDTO> memberList(Map<String, Integer> searchMap);

	void removeUser(String userId);
}
