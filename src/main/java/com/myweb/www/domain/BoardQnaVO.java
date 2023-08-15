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
public class BoardQnaVO {
	private int bq_number;
	private String bq_title;
	private String bq_writer;
	private String bq_content;
	private String bq_reg_date;
	private int bq_isDel;
	private int bq_status;
}
