package com.application.usedallea.member.v1.dao;

import org.apache.ibatis.annotations.Mapper;

import com.application.usedallea.member.v1.dto.MemberDTO;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberDAO {

	void registerMember(MemberDTO memberDTO);

	String dupleCheckId(String userId);

	MemberDTO getLoginData(String userId);

	MemberDTO getMemberDetail(String userId);

	void updateMember(MemberDTO memberDTO);

	void updateDeleteMember(String userId);

	int getAllUserCnt();

    List<MemberDTO> getMemberList(Map<String, Integer> searchMap);

	void removeUser(String userId);
}
