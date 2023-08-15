package com.myweb.www.controller;


import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.MemberDTO;
import com.myweb.www.domain.MemberVO;
import com.myweb.www.domain.ProductDTO;
import com.myweb.www.domain.ProductImageVO;
import com.myweb.www.domain.ProductLikeVO;
import com.myweb.www.domain.ProductPagingVO;
import com.myweb.www.domain.ProductReviewDTO;
import com.myweb.www.domain.ProductReviewVO;
import com.myweb.www.domain.ProductVO;
import com.myweb.www.domain.ReviewPagingVO;
import com.myweb.www.handler.ProductImageHandler;
import com.myweb.www.handler.ProductPagingHandler;
import com.myweb.www.handler.ReviewPagingHandler;
import com.myweb.www.service.MemberService;
import com.myweb.www.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/product/*")
public class ProductController {
	@Inject
	private ProductService psv;
	@Inject
	private ProductImageHandler pihd;
	@Inject
	private MemberService memberService;
	
	
	
	@GetMapping("/list")
	public void list(ProductPagingVO ppvo, Model m) {
		m.addAttribute("ppvo",ppvo);
		List<ProductDTO> pdto = psv.listFile(ppvo);
		m.addAttribute("productList", pdto);
		int totalCount = psv.getTotalCount(ppvo);
		ProductPagingHandler pph = new ProductPagingHandler(ppvo, totalCount);
		m.addAttribute("pph", pph);
	}
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String postRegister(RedirectAttributes rAttr, ProductVO pvo, @RequestParam(name="files")MultipartFile[] files) {
		List<ProductImageVO> piList = null;
		if(files[0].getSize()>0) {
			piList = pihd.uploadFiles(files);
		}
		ProductDTO pdto = new ProductDTO(pvo, null, piList, null);
		int isOk = psv.productRegister(pdto);
		rAttr.addFlashAttribute("isOk", isOk);
		return "redirect:/product/list";
	}
	
	@GetMapping("/detail")
	public void detail(@RequestParam("p_number")int p_number, Model m, HttpSession ses) {
		
		MemberVO sesMvo = (MemberVO) ses.getAttribute("ses");
		ProductDTO pdto = new ProductDTO();
		ProductReviewDTO prdto = new ProductReviewDTO();
		psv.readCount(p_number);

		if (sesMvo == null) { // 로그인 안 되었을 때 
			pdto = psv.detail(p_number); 
		} else { // 로그인 되었을 때 
			pdto = psv.detail(p_number, sesMvo.getM_number()); 
			prdto = psv.getReview(p_number, sesMvo.getM_number());
		}
		log.info(">>> pdto = " + pdto.toString());
		m.addAttribute("pdto", pdto);
		
		log.info(">>> prdto = " + prdto.toString());
		m.addAttribute("prdto", prdto);
	}
	
	@PostMapping(value = "/status", consumes = "application/json")
	public ResponseEntity<Integer> status(@RequestBody Map<String, Integer> data) {
		ProductVO pvo = new ProductVO();
		for (String key : data.keySet()) {
			if(key.equals("p_number")) {
				pvo.setP_number(data.get(key));
			} else if(key.equals("p_status")) {
				pvo.setP_status(data.get(key));
			}
		}
		int isOk = psv.updateStatus(pvo);
		return ResponseEntity.ok(isOk);
	}
	
	@PostMapping(value = "/like", consumes = "application/json")
	public ResponseEntity<String> postDetail(@RequestBody Map<String, Integer> data) {
		ProductLikeVO plvo = new ProductLikeVO();
			
		for (String key : data.keySet()) {
			if(key.equals("p_number")) {
				plvo.setP_number(data.get(key));
			} else if(key.equals("sessionMemberNumber")) {
				plvo.setM_number(data.get(key));
			}
		}
		boolean isLiked = psv.insertLikeMember(plvo);
		if (isLiked) {
	        return ResponseEntity.ok("liked"); // 찜되었다는 응답
	    } else {
	        return ResponseEntity.ok("unliked"); // 찜 해제되었다는 응답
	    }
	}
	
	@GetMapping("/modify")
	public void modify(@RequestParam("p_number")int p_number, Model m) {
		ProductDTO pdto = psv.detail(p_number);
		m.addAttribute("pdto", pdto);
	}
	
	@PostMapping("/modify")
	public String postModify(RedirectAttributes rAttr, ProductVO pvo, @RequestParam(name="files", required = false)MultipartFile[] files) {
		List<ProductImageVO> piList = null;
		if(files[0].getSize()>0) {
			piList = pihd.uploadFiles(files);
		}
		ProductDTO pdto = new ProductDTO(pvo, null, piList, null);
		int isOk = psv.updateModify(pdto);
		log.info(">>> 글 수정 > "+(isOk>0?"성공":"실패"));
		return "redirect:/product/list";
	}

	@DeleteMapping(value="/file/{uuid}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> removeFile(@PathVariable("uuid")String pi_uuid){
		return psv.removeFile(pi_uuid) > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/remove")
	public String remove(RedirectAttributes rAttr, @RequestParam("p_number")int p_number) {
		int isOk = psv.removeIsDel(p_number);
		log.info(">>> 글 삭제 > "+(isOk>0?"성공":"실패"));
		return "redirect:/product/list";
	}
	
	// 상점 정보 보기
	@GetMapping("/store")
	public void getStore(@RequestParam("m_number")int m_number, ReviewPagingVO rpvo, Model m, HttpServletRequest request) {
		m.addAttribute("rpvo", rpvo);
		MemberDTO member = memberService.getMemberDetails(m_number);
	    List<ProductDTO> productList = psv.getProductByMember(m_number);
	    List<ProductReviewDTO> reviewList = psv.getReviewList(rpvo);
	    m.addAttribute("member", member);
	    m.addAttribute("productList", productList);
	    m.addAttribute("reviewList", reviewList);
	    int totalCount = psv.getTotalRvCount(rpvo);
	    log.info(">>> totalrv > "+totalCount);
	    
	    ReviewPagingHandler rph = new ReviewPagingHandler(rpvo, totalCount);
	    m.addAttribute("rph", rph);
	}
	
	@GetMapping("/review")
	public void getReview(@RequestParam("p_number")int p_number, Model m, HttpSession ses) {
		MemberVO sesMvo = (MemberVO) ses.getAttribute("ses");
		ProductReviewDTO prdto = psv.getReview(p_number, sesMvo.getM_number());
		m.addAttribute("prdto", prdto);
	}
	
	@PostMapping("/review")
	public String postReview(RedirectAttributes rAttr, ProductVO pvo, ProductReviewVO prvo) {
		ProductReviewDTO prdto = new ProductReviewDTO(null, null, prvo, pvo, null, null, null, null);
		int isOk = psv.insertReview(prdto);
		log.info(">>> 리뷰 작성 > "+(isOk>0?"성공":"실패"));
		return "redirect:/product/list";
	}


}