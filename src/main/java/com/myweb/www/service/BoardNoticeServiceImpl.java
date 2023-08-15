package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.BoardNoticeVO;
import com.myweb.www.repository.BoardNoticeDAO;

@Service
public class BoardNoticeServiceImpl implements BoardNoticeService {
	@Inject
	private BoardNoticeDAO bndao;

	@Override
	public List<BoardNoticeVO> getNoticeList(BoardNoticeVO bnvo) {
		// TODO Auto-generated method stub
		return bndao.selectNoticeList(bnvo);
	}

	@Override
	public int registerNotice(BoardNoticeVO bnvo) {
		// TODO Auto-generated method stub
		return bndao.insertNotice(bnvo);
	}

	@Override
	public BoardNoticeVO detailNotice(int bn_number) {
		// TODO Auto-generated method stub
		return bndao.selectNoticeDetail(bn_number);
	}

	@Override
	public int updateNotice(BoardNoticeVO bnvo) {
		return bndao.updateNotice(bnvo);
		
	}

	@Override
	public int removeNotice(int bn_number) {
		// TODO Auto-generated method stub
		return bndao.updateNoticeIsDel(bn_number);
	}
}
