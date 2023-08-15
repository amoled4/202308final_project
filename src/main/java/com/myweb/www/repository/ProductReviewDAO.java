package com.myweb.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myweb.www.domain.ProductReviewVO;
import com.myweb.www.domain.ReviewPagingVO;

public interface ProductReviewDAO {

	int insertReview(ProductReviewVO productReviewVO);

//	List<ProductReviewVO> selectReviewList(int m_number);

	int getTotalRvCount(ReviewPagingVO rpvo);

	List<ProductReviewVO> selectReviewList(ReviewPagingVO rpvo);

	List<ProductReviewVO> selectPrvol(int m_number);

	ProductReviewVO selectPrvo(@Param("p_number")int p_number, @Param("sesM_number")int sesM_number);


}
