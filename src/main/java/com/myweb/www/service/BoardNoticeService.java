package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardNoticeVO;

public interface BoardNoticeService {

	List<BoardNoticeVO> getNoticeList(BoardNoticeVO bnvo);

	int registerNotice(BoardNoticeVO bnvo);

	BoardNoticeVO detailNotice(int bn_number);

	int updateNotice(BoardNoticeVO bnvo);

	int removeNotice(int bn_number);
	
}
