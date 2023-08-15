package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.ProductDTO;
import com.myweb.www.domain.ProductLikeVO;
import com.myweb.www.domain.ProductPagingVO;
import com.myweb.www.domain.ProductReviewDTO;
import com.myweb.www.domain.ProductVO;
import com.myweb.www.domain.ReviewPagingVO;

public interface ProductService {

	int getTotalCount(ProductPagingVO ppvo);

	int productRegister(ProductDTO pdto);

	List<ProductDTO> listFile(ProductPagingVO ppvo);

	ProductDTO detail(int p_number);
	ProductDTO detail(int p_number, int sesM_number);

	int updateStatus(ProductVO pvo);

	int readCount(int p_number);

	boolean insertLikeMember(ProductLikeVO plvo);

	int updateModify(ProductDTO pdto);

	int removeFile(String pi_uuid);
	
	//member/detail (작성자:안세호)
	List<ProductDTO> getProductByMember(Integer m_number);
	List<ProductDTO> getLikedProductsByMember(Integer m_number);

	int removeIsDel(int p_number);

	ProductReviewDTO getReview(int p_number, int sesM_number);

	int insertReview(ProductReviewDTO prdto);

	int getTotalRvCount(ReviewPagingVO rpvo);

	List<ProductReviewDTO> getReviewList(ReviewPagingVO rpvo);


}
