package com.myweb.www.handler;

import com.myweb.www.domain.BoardLocationPagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class BoardLocationPagingHandler {
	private int startPage;
	private int endPage;
	private boolean prev, next;
	private int totalCount;
	private BoardLocationPagingVO blpvo;
	
	public BoardLocationPagingHandler(BoardLocationPagingVO blpvo, int totalCount) {
		this.blpvo = blpvo;
		this.totalCount = totalCount;
		this.endPage = (int)(Math.ceil(blpvo.getPageNo() / (blpvo.getPageCnt()*1.0)))*blpvo.getPageCnt();
		this.startPage = endPage -9;
		int realEndPage = (int)Math.ceil((totalCount)*1.0 / blpvo.getQty());
		if(realEndPage<this.endPage) {
			this.endPage = realEndPage;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEndPage;
	}

}
