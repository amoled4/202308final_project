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

import com.myweb.www.domain.BoardQnaCommentVO;
import com.myweb.www.service.BoardQnaCommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board_qna_comment/*")
@Controller
public class BoardQnaCommentController {
	@Inject
	private BoardQnaCommentService bqcsv;
	
	@PostMapping(value="/post", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> postQnaComment(@RequestBody BoardQnaCommentVO bqcvo){
		int isOk = bqcsv.postQnaComment(bqcvo);
		log.info(">>> 답변 등록 > "+(isOk>0?"성공":"실패"));
		return isOk>0? new ResponseEntity<String>("1", HttpStatus.OK) :
			new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/{bq_number}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<BoardQnaCommentVO>> getQnaComment(@PathVariable("bq_number")int bq_number){
		List<BoardQnaCommentVO> qnaCommentList = bqcsv.getQnaComment(bq_number);
		return new ResponseEntity<List<BoardQnaCommentVO>>(qnaCommentList, HttpStatus.OK);
	}
	
}
