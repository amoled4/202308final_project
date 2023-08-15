package com.myweb.www.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ReviewPagingVO extends ProductReviewVO {
	private int pageNo;
	private int qty;
	
//	private int pr_seller=super.getPr_seller();
	
	public ReviewPagingVO() {
		this(1,10);
	}

	public ReviewPagingVO(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}
	
	public int getPageStart() {
		return (this.pageNo-1)*qty;
	}
}
