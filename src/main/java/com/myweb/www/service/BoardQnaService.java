package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardQnaVO;

public interface BoardQnaService {

	int registerQna(BoardQnaVO bqvo);

	List<BoardQnaVO> getQnaList(BoardQnaVO bqvo);

	BoardQnaVO getQnaDetail(int bq_number);

}
