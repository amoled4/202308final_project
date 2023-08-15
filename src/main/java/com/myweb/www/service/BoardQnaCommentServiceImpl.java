package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.BoardQnaCommentVO;
import com.myweb.www.repository.BoardQnaCommentDAO;

@Service
public class BoardQnaCommentServiceImpl implements BoardQnaCommentService {
	@Inject
	private BoardQnaCommentDAO bqcdao;

	@Override
	public int postQnaComment(BoardQnaCommentVO bqcvo) {
		// TODO Auto-generated method stub
		return bqcdao.insertQnaComment(bqcvo);
	}

	@Override
	public List<BoardQnaCommentVO> getQnaComment(int bq_number) {
		// TODO Auto-generated method stub
		return bqcdao.selectQnaCommentList(bq_number);
	}
}
