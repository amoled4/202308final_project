package com.myweb.www.repository;


import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.myweb.www.domain.MemberVO;

@Repository
public interface MemberDAO {

	MemberVO getMember(String m_mail);

	int insertMember(MemberVO member);

	int emailCheck(String m_mail);
	
	int nicknameCheck1(String m_nick_name);

	int nicknameCheck(@Param("m_nick_name") String m_nick_name, @Param("m_number") int m_number);
	
	MemberVO selectMemberWithNumber(int m_number);

	MemberVO getNewMember(String m_mail);

	
	int updateMember(MemberVO member);
	
	int updateMemberStatus(Map<String, Object> params);

	// 작성자 : 김경아
	MemberVO getMemberProduct(int m_number);
	MemberVO getBoardLocationMember(int bl_m_number);


	

	

	

	

}
