package com.myweb.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class BoardNoticeVO {
	private int bn_number;
	private String bn_title;
	private String bn_writer;
	private String bn_content;
	private String bn_reg_date;
	private String bn_mod_date;
	private int bn_isDel;
}
