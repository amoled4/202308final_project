package com.myweb.www.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.BoardLocationDTO;
import com.myweb.www.domain.BoardLocationImageVO;
import com.myweb.www.domain.BoardLocationPagingVO;
import com.myweb.www.domain.BoardLocationVO;
import com.myweb.www.handler.BoardLocationImageHandler;
import com.myweb.www.handler.BoardLocationPagingHandler;
import com.myweb.www.service.BoardLocationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board_location/*")
public class BoardLocationController {
	@Inject
	private BoardLocationService blsv;
	@Inject
	private BoardLocationImageHandler blih;

	@GetMapping("list")
	public void list(BoardLocationPagingVO blpvo, Model m) {
		m.addAttribute("blpvo", blpvo);
		List<BoardLocationDTO> bldto = blsv.locationList(blpvo);
		m.addAttribute("bldto", bldto);
		int totalCount = blsv.getTotalCount(blpvo);
		BoardLocationPagingHandler blph = new BoardLocationPagingHandler(blpvo, totalCount);
		m.addAttribute("blph", blph);
		log.info("글개수 "+totalCount);
		log.info("bldto "+bldto.toString());
	}
		
	@GetMapping("register")
	public void register() {}
	
	@PostMapping("/register")
	public String postRegister(RedirectAttributes rAttr, BoardLocationVO blvo, @RequestParam(name="files")MultipartFile[] files) {
		List<BoardLocationImageVO> blivo = null;
		if(files[0].getSize()>0) {
			blivo = blih.uploadLocationFiles(files);
		}
		BoardLocationDTO bldto = new BoardLocationDTO(blvo, blivo, null);
		int isOk = blsv.boardLocationRegister(bldto);
		rAttr.addFlashAttribute("isOk",isOk);
		return "redirect:/board_location/list";
	}
	
	@GetMapping({"detail","modify"})
	public void detail(@RequestParam("bl_number")int bl_number, Model m) {
		BoardLocationDTO bldto = blsv.selectblList(bl_number);
		m.addAttribute("bldto",bldto);
	}
	
	@PostMapping("/modify")
	public String modify(RedirectAttributes rAttr, BoardLocationVO blvo, @RequestParam(name="files", required = false)MultipartFile[] files) {
		List<BoardLocationImageVO> blivo = null;
		if(files[0].getSize()>0) {
			blivo = blih.uploadLocationFiles(files);
		}
		BoardLocationDTO bldto = new BoardLocationDTO(blvo, blivo, null);
		int isOk = blsv.updateModify(bldto);
		log.info(">>> 글 수정 > "+(isOk>0?"성공":"실패"));
		return "redirect:/board_location/list";
	}
	
	@DeleteMapping(value="/file/{uuid}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> removeFile(@PathVariable("uuid")String blf_uuid){
		return blsv.removeFile(blf_uuid) > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/remove")
	public String remove(RedirectAttributes rAttr, @RequestParam("bl_number")int bl_number) {
		int isOk = blsv.removeIsDel(bl_number);
		log.info(">>> 글 삭제 > "+(isOk>0?"성공":"실패"));
		return "redirect:/board_location/list";
	}
}