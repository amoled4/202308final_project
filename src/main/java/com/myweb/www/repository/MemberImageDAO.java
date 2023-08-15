package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.MemberImageVO;
import com.myweb.www.domain.ProductReviewVO;
import com.myweb.www.domain.ReviewPagingVO;

public interface MemberImageDAO {

	int insertMemberImage(MemberImageVO mivo);

	MemberImageVO selectMemberImage(int m_number);
	
	//프로필사진 수정
	void updateMemberImage(MemberImageVO mivo);

	// 작성자 : 김경아
	List<MemberImageVO> selectMivo(int m_number);
	List<MemberImageVO> selectRvMivo(ProductReviewVO prvo);
	MemberImageVO getBoardLocationmImage(int bl_m_number);

}
