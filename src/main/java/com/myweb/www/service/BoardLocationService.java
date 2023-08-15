package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardLocationDTO;
import com.myweb.www.domain.BoardLocationPagingVO;

public interface BoardLocationService {

	List<BoardLocationDTO> locationList(BoardLocationPagingVO blpvo);

	int getTotalCount(BoardLocationPagingVO blpvo);

	int boardLocationRegister(BoardLocationDTO bldto);

	BoardLocationDTO selectblList(int bl_number);

	int updateModify(BoardLocationDTO bldto);

	int removeFile(String blf_uuid);

	int removeIsDel(int bl_number);

}
