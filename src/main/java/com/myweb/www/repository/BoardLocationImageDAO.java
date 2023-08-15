package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BoardLocationImageVO;
import com.myweb.www.domain.BoardLocationVO;

public interface BoardLocationImageDAO {

	List<BoardLocationImageVO> selectBoardLocationFile(BoardLocationVO blvo);
	List<BoardLocationImageVO> selectBoardLocationFile(int bl_number);

	int insertBoardLocationImage(BoardLocationImageVO blivo);

	List<BoardLocationImageVO> selectblImageList(int bl_number);
	
	int deleteFile(String blf_uuid);


}
