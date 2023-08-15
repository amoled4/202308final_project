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
public class ProductVO {
	
	private int p_number;
	private int m_number;
	private String p_name;
	private int p_price;
	private String p_category;
	private String p_info;
	private String p_reg_date;
	private String p_mod_date;
	private int p_like;
	private String p_location;
	private int p_status;
	private boolean p_isDel;
	private String p_pay;
	private int p_read_count;
	
	
}
