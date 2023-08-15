package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardLocationCommentVO;

public interface BoardLocationCommentService {

	int postLocationComment(BoardLocationCommentVO blcvo);

	List<BoardLocationCommentVO> getLocationComment(int bl_number);

}
