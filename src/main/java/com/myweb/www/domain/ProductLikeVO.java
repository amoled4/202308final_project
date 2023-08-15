package com.myweb.www.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ProductLikeVO {
	private int p_number;
	private int m_number;
	private String pl_date;
	
	private boolean liked;
}
