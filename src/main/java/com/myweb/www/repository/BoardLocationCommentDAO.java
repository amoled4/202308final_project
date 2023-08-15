package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BoardLocationCommentVO;

public interface BoardLocationCommentDAO {

	int insertLocationComment(BoardLocationCommentVO blcvo);

	List<BoardLocationCommentVO> selectLocationCommentList(int bl_number);

}
