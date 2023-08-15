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
public class BoardLocationCommentVO {
	private int blc_number;
	private int bl_number;
	private String blc_writer;
	private String blc_content;
	private String blc_reg_date;
	private String blc_mod_date;
}
