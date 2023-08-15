package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BoardQnaVO;

public interface BoardQnaDAO {

	int insertQna(BoardQnaVO bqvo);

	List<BoardQnaVO> selectQnaList(BoardQnaVO bqvo);

	BoardQnaVO selectQnaDetail(int bq_number);

}
