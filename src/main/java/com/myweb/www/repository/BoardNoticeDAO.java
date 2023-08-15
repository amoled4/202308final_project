package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BoardNoticeVO;

public interface BoardNoticeDAO {

	List<BoardNoticeVO> selectNoticeList(BoardNoticeVO bnvo);

	int insertNotice(BoardNoticeVO bnvo);

	BoardNoticeVO selectNoticeDetail(int bn_number);

	int updateNotice(BoardNoticeVO bnvo);

	int updateNoticeIsDel(int bn_number);

}
