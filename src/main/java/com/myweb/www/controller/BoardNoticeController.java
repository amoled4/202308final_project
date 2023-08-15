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

import com.myweb.www.domain.BoardNoticeVO;
import com.myweb.www.service.BoardNoticeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board_notice/*")
public class BoardNoticeController {
	@Inject
	private BoardNoticeService bnsv;
	

	@GetMapping("list")
	public void list(BoardNoticeVO bnvo, Model m) {
		List<BoardNoticeVO> list = bnsv.getNoticeList(bnvo);
		m.addAttribute("bnvo", list);
	}

	@GetMapping("register")
	public void register() {}
	
	@PostMapping("register")
	public String postRegister(RedirectAttributes rAttr, BoardNoticeVO bnvo) {
		int isOk = bnsv.registerNotice(bnvo);
		rAttr.addFlashAttribute("isOk", isOk);
		return "redirect:/board_notice/list";
	}
	
	@GetMapping({"detail","modify"})
	public void deatil(@RequestParam("bn_number")int bn_number, Model m) {
		BoardNoticeVO bnvo = bnsv.detailNotice(bn_number);
		m.addAttribute("bnvo", bnvo);
	}
	
	@PostMapping("modify")
	public String modify(RedirectAttributes rAttr, BoardNoticeVO bnvo) {
		int isOk = bnsv.updateNotice(bnvo);
		log.info(">>> 공지사항 수정 > "+(isOk>0?"성공":"실패"));
		return "redirect:/board_notice/list";
	}
	
	@GetMapping("/remove")
	public String remove(RedirectAttributes rAttr, @RequestParam("bn_number")int bn_number) {
		int isOk = bnsv.removeNotice(bn_number);
		log.info(">>> 공지사항 삭제 > "+(isOk>0?"성공":"실패"));
		return "redirect:/board_notice/list";
	}
	
	
}

