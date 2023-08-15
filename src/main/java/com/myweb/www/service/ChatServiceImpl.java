package com.myweb.www.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.ChatMessageDTO;
import com.myweb.www.domain.ChatMessageImageVO;
import com.myweb.www.domain.ChatMessageVO;
import com.myweb.www.domain.ChatRoomDTO;
import com.myweb.www.domain.ChatRoomVO;
import com.myweb.www.domain.MemberVO;
import com.myweb.www.domain.ProductDTO;
import com.myweb.www.repository.ChatDAO;
import com.myweb.www.repository.MemberDAO;
import com.myweb.www.repository.ProductDAO;
import com.myweb.www.repository.ProductImageDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {
	
	@Inject
	private ChatDAO cdao;
	@Inject
	private MemberDAO mdao;
	@Inject
	private ProductDAO pdao;
	@Inject
	private ProductImageDAO pidao;

	@Override
	public List<ChatRoomDTO> getChatList(MemberVO sesMvo) {
		log.info(">>> getChatList" );
		
		List<ChatRoomDTO> listCdto = new ArrayList<ChatRoomDTO>();
		
		List<ChatRoomVO> listCrvo = cdao.selectChatRoom(sesMvo);
		
		for(ChatRoomVO crvo : listCrvo) {
			String lastMessage = cdao.selectLastMessage(crvo);
			int notReadCount = cdao.countNotReadMessage(crvo, sesMvo);
			MemberVO sender_mvo = mdao.selectMemberWithNumber(crvo.getCr_seller());
			MemberVO receiver_mvo = mdao.selectMemberWithNumber(crvo.getCr_buyer());
			
			listCdto.add(new ChatRoomDTO(crvo, lastMessage, notReadCount, sender_mvo, receiver_mvo));
		}
		
		log.info(listCdto.toString());
		
		return listCdto;
	}

	@Override
	public int modifyReadDate(Integer cr_number, Integer cm_sender) {
		return cdao.updateReadDate(cr_number, cm_sender);
	}

	@Override
	public ChatMessageDTO getMessage(int cr_number, int sessionM_number) {
//		cdao.updateReadDate(cr_number, sessionM_number);
		
		ProductDTO pdto = new ProductDTO();
		pdto.setPvo(pdao.selectProductWithNumber(cr_number));
		pdto.setPiList(pidao.selectFile(cr_number));

		MemberVO mvo = new MemberVO();
		if (pdto.getPvo().getM_number() == sessionM_number) {
			mvo = mdao.selectMemberWithNumber(cdao.selectBuyer(cr_number));
		} else {
			mvo = mdao.selectMemberWithNumber(cdao.selectSeller(cr_number));
		}

		List<ChatMessageVO> listCmvo = new ArrayList<ChatMessageVO>();
		listCmvo = cdao.selectMessage(cr_number);

		ChatMessageDTO cmdto = new ChatMessageDTO();
		cmdto.setMvo(mvo);
		cmdto.setPdto(pdto);
		cmdto.setListCmvo(listCmvo);
		
		return cmdto;
	}

	@Override
	public void insertMessage(ChatMessageVO cmvo) {
    	log.info(">>> insertMessage()");
    	log.info(cmvo.toString());
    	
		cdao.insertMessage(cmvo);
		cdao.updateSendDate(cmvo);
	}


	@Override
	public int registerChatRoom(ChatRoomVO crvo) {
		return cdao.insertChatRoom(crvo);
		
	}

	@Override
	public int registerChatImage(ChatMessageImageVO cmivo) {
		log.info(">>> registerChatImage()");
		
		cdao.insertChatImage(cmivo);
		log.info("cmivo : " + cmivo.toString());
		
		return cmivo.getCm_number();
	}

	@Override
	public ChatMessageImageVO getImage(int cm_number) {
		return cdao.selectImage(cm_number);
	}

}
