package com.myweb.www.service;

import java.util.Map;

import com.myweb.www.domain.MemberDTO;
import com.myweb.www.domain.MemberVO;

public interface MemberService {



	MemberVO isMember(String m_mail, String m_pw);
	//(회원가입 닉네임 중복체크)
	int nicknameCheck1(String m_nick_name);
	//(회원정보수정)닉네임 중복체크
	int nicknameCheck(String m_nick_name, int m_number);
	//이메일 중복체크
	int emailCheck(String m_mail);

	int signUp(MemberDTO mdto);
	
	//detail페이지
	MemberDTO getMemberDetails(int m_number);
	
	//modify
	void updateMember(MemberDTO memberDTO);
	
	int updateMemberStatus(Map<String, Object> params);
	
	
	

	







}
