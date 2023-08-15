package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BoardQnaCommentVO;

public interface BoardQnaCommentDAO {

	int insertQnaComment(BoardQnaCommentVO bqcvo);

	List<BoardQnaCommentVO> selectQnaCommentList(int bq_number);

}
