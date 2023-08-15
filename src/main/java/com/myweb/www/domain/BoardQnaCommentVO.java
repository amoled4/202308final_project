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
public class BoardQnaCommentVO {
	private int bqc_number;
	private int bq_number;
	private String bqc_writer;
	private String bqc_content;
	private String bqc_reg_date;
}
