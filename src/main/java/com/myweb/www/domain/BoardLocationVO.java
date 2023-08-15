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
public class BoardLocationVO {

	private int bl_number;
	private String bl_location;
	private String bl_title;
	private String bl_writer;
	private String bl_content;
	private String bl_reg_date;
	private String bl_mod_date;
	private int bl_isDel;
	private String bl_subject;
	private int bl_m_number;
	
}
