package com.myweb.www.handler;

import com.myweb.www.domain.ReviewPagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ReviewPagingHandler {
	private int startPage;
	private int endPage;
	private boolean prev, next;
	private int totalCount;
	private ReviewPagingVO rpvo;
	
	public ReviewPagingHandler(ReviewPagingVO rpvo, int totalCount) {
		this.rpvo = rpvo;
		this.totalCount = totalCount;
		this.endPage = (int)(Math.ceil(rpvo.getPageNo() / (rpvo.getQty()*1.0)))*rpvo.getQty();
		this.startPage = endPage -9;
		int realEndPage = (int)Math.ceil((totalCount)*1.0 / rpvo.getQty());
		if(realEndPage<this.endPage) {
			this.endPage = realEndPage;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEndPage;
	}
}
