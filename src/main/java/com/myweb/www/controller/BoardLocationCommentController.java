package com.myweb.www.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myweb.www.domain.BoardLocationCommentDTO;
import com.myweb.www.domain.BoardLocationCommentVO;
import com.myweb.www.service.BoardLocationCommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board_location_comment/*")
@Controller
public class BoardLocationCommentController {
	@Inject
	private BoardLocationCommentService blcsv;
	
	@PostMapping(value="/post", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> postLocationComment(@RequestBody BoardLocationCommentVO blcvo){
		int isOk = blcsv.postLocationComment(blcvo);
		log.info(">>> 답변 등록 > "+(isOk>0?"성공":"실패"));
		return isOk>0? new ResponseEntity<String>("1", HttpStatus.OK) :
			new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/{bl_number}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<BoardLocationCommentVO>> getLocationComment(@PathVariable("bl_number")int bl_number){
		List<BoardLocationCommentVO> locationCommentList = blcsv.getLocationComment(bl_number);
		return new ResponseEntity<List<BoardLocationCommentVO>>(locationCommentList, HttpStatus.OK);
	}
}
