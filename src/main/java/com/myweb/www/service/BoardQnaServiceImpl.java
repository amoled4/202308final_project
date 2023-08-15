package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.BoardQnaVO;
import com.myweb.www.repository.BoardQnaDAO;

@Service
public class BoardQnaServiceImpl implements BoardQnaService {
	@Inject
	private BoardQnaDAO bqdao;

	@Override
	public int registerQna(BoardQnaVO bqvo) {
		// TODO Auto-generated method stub
		return bqdao.insertQna(bqvo);
	}

	@Override
	public List<BoardQnaVO> getQnaList(BoardQnaVO bqvo) {
		// TODO Auto-generated method stub
		return bqdao.selectQnaList(bqvo);
	}

	@Override
	public BoardQnaVO getQnaDetail(int bq_number) {
		// TODO Auto-generated method stub
		return bqdao.selectQnaDetail(bq_number);
	}
}
