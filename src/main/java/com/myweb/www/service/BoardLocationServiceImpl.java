package com.myweb.www.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.BoardLocationDTO;
import com.myweb.www.domain.BoardLocationImageVO;
import com.myweb.www.domain.BoardLocationPagingVO;
import com.myweb.www.domain.BoardLocationVO;
import com.myweb.www.domain.MemberDTO;
import com.myweb.www.domain.MemberImageVO;
import com.myweb.www.domain.MemberVO;
import com.myweb.www.repository.BoardLocationDAO;
import com.myweb.www.repository.BoardLocationImageDAO;
import com.myweb.www.repository.MemberDAO;
import com.myweb.www.repository.MemberImageDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardLocationServiceImpl implements BoardLocationService {
	@Inject
	private BoardLocationDAO bldao;
	@Inject
	private BoardLocationImageDAO blidao;
	@Inject
	private MemberDAO mdao;
	@Inject
	private MemberImageDAO midao;

	@Override
	public List<BoardLocationDTO> locationList(BoardLocationPagingVO blpvo) {
		List<BoardLocationDTO> listBldto = new ArrayList<BoardLocationDTO>();
		List<BoardLocationVO> listBlvo = bldao.selectBoardLocation(blpvo);
		for(BoardLocationVO blvo : listBlvo) {
			List<BoardLocationImageVO> blivo = blidao.selectBoardLocationFile(blvo);
			listBldto.add(new BoardLocationDTO(blvo, blivo, null));
			log.info("service "+blvo.toString());
		}
		return listBldto;
	}

	@Override
	public int getTotalCount(BoardLocationPagingVO blpvo) {
		// TODO Auto-generated method stub
		return bldao.getTotalCount(blpvo);
	}

	@Override
	public int boardLocationRegister(BoardLocationDTO bldto) {
		int isOk = bldao.insertBoardLocationRegister(bldto.getBlvo());
		if(bldto.getBlivo()!=null) {
			if(isOk>0&&bldto.getBlivo().size()>0) {
				int bl_number = bldao.selectBlnumber();
				for(BoardLocationImageVO blivo : bldto.getBlivo()) {
					blivo.setBl_number(bl_number);
					isOk *= blidao.insertBoardLocationImage(blivo);
				}
			}
		}
		return isOk;
	}

	@Override
	public BoardLocationDTO selectblList(int bl_number) {
		BoardLocationDTO bldto = new BoardLocationDTO();
		bldto.setBlvo(bldao.selectblList(bl_number));
		bldto.setBlivo(blidao.selectBoardLocationFile(bl_number));
		// 회원 정보 가져오기
		MemberVO mvo = mdao.getBoardLocationMember(bldto.getBlvo().getBl_m_number());
		MemberImageVO mivo = midao.getBoardLocationmImage(bldto.getBlvo().getBl_m_number());
		MemberDTO mdto = new MemberDTO(mvo, mivo);
		bldto.setMdto(mdto);
		return bldto;
	}

	@Override
	public int updateModify(BoardLocationDTO bldto) {
		int isOk = bldao.updateModify(bldto.getBlvo());
		if(bldto.getBlivo()==null) {
			isOk *= 1;
		} else {
			if(isOk>0&&bldto.getBlivo().size()>0) {
				int bl_number = bldto.getBlvo().getBl_number();
				for(BoardLocationImageVO blivo : bldto.getBlivo()) {
					blivo.setBl_number(bl_number);
					isOk *= blidao.insertBoardLocationImage(blivo);
				}
			}
		}
		return isOk;
	}

	@Override
	public int removeFile(String blf_uuid) {
		// TODO Auto-generated method stub
		return blidao.deleteFile(blf_uuid);
	}

	@Override
	public int removeIsDel(int bl_number) {
		// TODO Auto-generated method stub
		return bldao.updateIsDel(bl_number);
	}
}
