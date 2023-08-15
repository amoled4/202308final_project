package com.myweb.www.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ProductPagingVO extends ProductVO {
	private int pageNo;
	private int qty;
	private int pageCnt;  // 페이지네이션 개수
	
	private String p_category;
	
	private String type;
	private String keyword;
	
	public String[] getTypeToArray() {
		return this.type == null ? new String[] {} : this.type.split("");
	}
	
	public ProductPagingVO() {
		this(1,15,10);
	}

	public ProductPagingVO(int pageNo, int qty, int pageCnt) {
		this.pageNo = pageNo;
		this.qty = qty;
		this.pageCnt = pageCnt;
	}
	
	public int getPageStart() {
		return (this.pageNo-1)*qty;
	}
	
}
