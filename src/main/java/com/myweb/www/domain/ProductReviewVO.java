package com.myweb.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class ProductReviewVO {
	private int pr_number;
	private int pr_buyer;
	private int pr_seller;
	private int pr_p_number;
	private int pr_score;
	private String pr_content;
	private String pr_reg_date;
	private int pr_isDel;
	
	private MemberImageVO mivo;
}
