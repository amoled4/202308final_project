package com.myweb.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myweb.www.domain.ProductLikeVO;
import com.myweb.www.domain.ProductPagingVO;
import com.myweb.www.domain.ProductVO;

public interface ProductDAO {

	int getTotalCount(ProductPagingVO ppvo);

	int insertProductRegister(ProductVO pvo);

	int selectPnumber();

	List<ProductVO> selectProduct(ProductPagingVO ppvo);

	ProductVO selectPno(int p_number);

	int updateStatus(ProductVO pvo);

	// 채팅 기능 구현 (작성자 : 조민규)	
	ProductVO selectProductWithNumber(int cr_number);

	int updateReadCount(int p_number);

	int insertLikeMember(ProductLikeVO plvo);

	void updateLike(int p_number);

	ProductLikeVO getLikeRecord(ProductLikeVO plvo);

	void deleteLikeMember(ProductLikeVO plvo);

	void updateUnlike(int p_number);

	ProductLikeVO selectProductLike(@Param("p_number")int p_number, @Param("sesM_number")int sesM_number);

	int updateModify(ProductVO pvo);
	
	int updateIsDel(int p_number);
	
	
	//(작성자 : 안세호)
	List<ProductVO> selectProductsByMember(Integer m_number);
	List<ProductVO> selectLikedProductsByMember(Integer m_number);







	

	
}
