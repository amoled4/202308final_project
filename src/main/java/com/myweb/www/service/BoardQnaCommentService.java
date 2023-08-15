package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardQnaCommentVO;

public interface BoardQnaCommentService {

	int postQnaComment(BoardQnaCommentVO bqcvo);

	List<BoardQnaCommentVO> getQnaComment(int bq_number);

}
