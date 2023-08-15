package com.myweb.www.handler;

import com.myweb.www.domain.ProductPagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ProductPagingHandler {
	private int startPage;
	private int endPage;
	private boolean prev, next;
	private int totalCount;
	private ProductPagingVO ppvo;
	
	public ProductPagingHandler(ProductPagingVO ppvo, int totalCount) {
		this.ppvo = ppvo;
		this.totalCount = totalCount;
		this.endPage = (int)(Math.ceil(ppvo.getPageNo() / (ppvo.getPageCnt()*1.0)))*ppvo.getPageCnt();
		this.startPage = endPage -9;
		int realEndPage = (int)Math.ceil((totalCount)*1.0 / ppvo.getQty());
		if(realEndPage<this.endPage) {
			this.endPage = realEndPage;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEndPage;
	}
}
