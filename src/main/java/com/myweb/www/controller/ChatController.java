package com.myweb.www.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myweb.www.domain.ChatMessageDTO;
import com.myweb.www.domain.ChatMessageImageVO;
import com.myweb.www.domain.ChatMessageVO;
import com.myweb.www.domain.ChatRoomDTO;
import com.myweb.www.domain.ChatRoomVO;
import com.myweb.www.domain.MemberVO;
import com.myweb.www.domain.ProductVO;
import com.myweb.www.handler.ChatMessageImageHandler;
import com.myweb.www.repository.ProductDAO;
import com.myweb.www.service.ChatService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/chat/*")
public class ChatController {
	
	@Inject
	private ChatService csvc;
	@Inject
	private ProductDAO pdao;
	@Inject
	private ChatMessageImageHandler cmihd;
	@Inject	
    private final SimpMessagingTemplate messagingTemplate = null;
	
	//채팅방 목록 조회
    @GetMapping(value = "/main")
    public void main(Model m, HttpSession ses, 
    		@RequestParam(value = "p_number" , required = false)Integer p_number) {
    	log.info(">>> main() > p_number = " + p_number);
    	
    	MemberVO sesMvo = (MemberVO) ses.getAttribute("ses");
    	m.addAttribute("listCdto", csvc.getChatList(sesMvo));
    	m.addAttribute("selectRoomNumber", p_number);
    }
    
    @GetMapping(value = "/list", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<ChatRoomDTO>> list(HttpSession ses) {
    	MemberVO sesMvo = (MemberVO) ses.getAttribute("ses");
    	
    	List<ChatRoomDTO> listCdto = csvc.getChatList(sesMvo);

		return new ResponseEntity<List<ChatRoomDTO>>(listCdto, HttpStatus.OK);
    }
    
    // 채팅방 개설
    @PostMapping(value = "/register")
    public ResponseEntity<Integer> register(@RequestBody Map<String, Integer> chatRegisterData, HttpSession ses) {
    	ProductVO pvo = null;
    	int sessionMemberNumber = 0;
    	
    	for(String key : chatRegisterData.keySet()) {
    		if(key.equals("p_number"))
    			pvo = pdao.selectPno(chatRegisterData.get(key));
    		if(key.equals("sessionMemberNumber" ))
    			sessionMemberNumber = chatRegisterData.get(key);
    	}
    	
		ChatRoomVO crvo = new ChatRoomVO(pvo.getP_number(), pvo.getM_number(), sessionMemberNumber);
		int isOk = csvc.registerChatRoom(crvo);
				
		return new ResponseEntity<Integer>(isOk, HttpStatus.OK);
    } 
    
    //채팅방 조회
	@GetMapping(value = "/view/{cr_number}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ChatMessageDTO> view(@PathVariable("cr_number")int cr_number, HttpSession ses, Model m) {
		log.info(">>> view()");
		
		MemberVO sesMvo = (MemberVO) ses.getAttribute("ses");
		
		ChatMessageDTO cmdto = csvc.getMessage(cr_number, sesMvo.getM_number());
		
		return new ResponseEntity<ChatMessageDTO>(cmdto, HttpStatus.OK);
	}
	
	// 이미지 업로드
	@PostMapping(value = "/upload", consumes = "multipart/form-data", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> upload(
			@RequestParam("file") MultipartFile file,
			@RequestParam("cmvo") String cmvoString) {
		try {
			log.info(">>> upload()");
			ObjectMapper mapper = new ObjectMapper();

			ChatMessageVO cmvo = mapper.readValue(cmvoString, ChatMessageVO.class);
			csvc.insertMessage(cmvo);
			
			ChatMessageImageVO cmivo = cmihd.uploadFiles(file);
			int cm_number = csvc.registerChatImage(cmivo);
			cmvo.setCm_number(cm_number);

	        messagingTemplate.convertAndSend("/sub/chat/main/" + cmvo.getCr_number(), cmvo);
	        
			return new ResponseEntity<>(Integer.toString(cm_number), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
    
    // 이미지 불러오기
	@GetMapping(value = "/image/{cm_number}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ChatMessageImageVO> image(@PathVariable("cm_number") int cm_number) {
		log.info(">>> image() : ");
		
		ChatMessageImageVO cmivo = csvc.getImage(cm_number);
		log.info(cmivo.toString());
		
		return new ResponseEntity<ChatMessageImageVO>(cmivo, HttpStatus.OK);
	}
	
	// 읽은 시간 업데이트
	@PutMapping(value = "/update", consumes = "application/json")
	public ResponseEntity<HttpStatus> update(@RequestBody ChatMessageVO cmvo) {
		log.info(">>> update()");
		
		int isOk = csvc.modifyReadDate(cmvo.getCr_number(), cmvo.getCm_sender());
		
		return ResponseEntity.ok(HttpStatus.OK);
	}
}
