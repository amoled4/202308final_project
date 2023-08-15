package com.myweb.www.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.BoardQnaVO;
import com.myweb.www.service.BoardQnaService;

@Controller
@RequestMapping("/board_qna/*")
public class BoardQnaController {
	@Inject
	private BoardQnaService bqsv;

	@GetMapping("list")
	public void list(BoardQnaVO bqvo, Model m) {
		List<BoardQnaVO> list = bqsv.getQnaList(bqvo);
		m.addAttribute("bqvo", list);
	}
	
	@GetMapping("register")
	public void register() {}
	
	@PostMapping("register")
	public String postRegister(RedirectAttributes rAttr, BoardQnaVO bqvo) {
		int isOk = bqsv.registerQna(bqvo);
		rAttr.addFlashAttribute("isOk", isOk);
		return "redirect:/board_qna/list";
	}

	@GetMapping("detail")
	public void detail(@RequestParam("bq_number")int bq_number, Model m) {
		BoardQnaVO bqvo = bqsv.getQnaDetail(bq_number);
		m.addAttribute("bqvo", bqvo);
	}

}
