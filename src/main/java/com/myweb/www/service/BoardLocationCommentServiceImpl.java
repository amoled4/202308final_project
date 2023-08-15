package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.BoardLocationCommentDTO;
import com.myweb.www.domain.BoardLocationCommentVO;
import com.myweb.www.domain.BoardLocationVO;
import com.myweb.www.domain.MemberImageVO;
import com.myweb.www.domain.ProductImageVO;
import com.myweb.www.repository.BoardLocationCommentDAO;
import com.myweb.www.repository.BoardLocationDAO;
import com.myweb.www.repository.MemberImageDAO;

@Service
public class BoardLocationCommentServiceImpl implements BoardLocationCommentService {
	@Inject
	private BoardLocationCommentDAO blcdao;
	@Inject
	private BoardLocationDAO bldao;
	@Inject
	private MemberImageDAO midao;


	@Override
	public int postLocationComment(BoardLocationCommentVO blcvo) {
		// TODO Auto-generated method stub
		return blcdao.insertLocationComment(blcvo);
	}

	@Override
	public List<BoardLocationCommentVO> getLocationComment(int bl_number) {
		// TODO Auto-generated method stub
		return blcdao.selectLocationCommentList(bl_number);
	}




}
