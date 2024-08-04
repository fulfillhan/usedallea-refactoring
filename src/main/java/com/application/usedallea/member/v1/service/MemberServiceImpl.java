package com.application.usedallea.member.v1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.usedallea.member.v1.dao.MemberDAO;
import com.application.usedallea.member.v1.dto.MemberDTO;

import java.util.List;
import java.util.Map;


@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MemberDAO memberDAO;
	
	private static Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

	@Override
	public void registerMember(MemberDTO memberDTO) {
		
		if(memberDTO.getSmsstsYn() == null) memberDTO.setSmsstsYn("n");
		if(memberDTO.getEmailstsYn() == null) memberDTO.setEmailstsYn("n");
		if(memberDTO.getPersonalInfoYn() == null) memberDTO.setPersonalInfoYn("n");
		
		memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
		System.out.println(memberDTO);
		memberDAO.registerMember(memberDTO);
	}

	@Override
	public String dupleCheckId(String userId) {
		String validateId = "y";
		if(memberDAO.dupleCheckId(userId) != null) {  //id가 있다.
			validateId = "n";
		}
		return validateId;
	}

	@Override
	public boolean login(MemberDTO memberDTO) {
		//입력한 아이디의 패스워드, 활성여부 확인하여 있으면 로그인가능
		MemberDTO loginData = memberDAO.getLoginData(memberDTO.getUserId());
		if(loginData != null) {
			if(passwordEncoder.matches(memberDTO.getPassword(), loginData.getPassword()) && loginData.getActiveYn().equals("y")) {
				return true;   //로그인 되어 있는 상태
			}
		}
		return false;
	}

	@Override
	public MemberDTO getMemberDetail(String userId) {

		return memberDAO.getMemberDetail(userId);
	}

    @Override
    public void updateMember(MemberDTO memberDTO) {

		if(memberDTO.getSmsstsYn() == null) memberDTO.setSmsstsYn("n");
		if(memberDTO.getEmailstsYn() == null) memberDTO.setEmailstsYn("n");

        memberDAO.updateMember(memberDTO);
    }

	@Override
	public void updateDelete(String userId) {
		memberDAO.updateDeleteMember(userId);
	}

	@Override
	public int getAllUserCnt() {
		return memberDAO.getAllUserCnt();
	}

	@Override
	public List<MemberDTO> memberList(Map<String, Integer> searchMap) {
		return memberDAO.getMemberList(searchMap);
	}

	@Override
	public void removeUser(String userId) {

		memberDAO.removeUser(userId);
	}

}
