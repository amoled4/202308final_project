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
public class MemberVO {
	private int m_number;
	private String m_mail;
	private String m_pw;
	private String m_nick_name;
	private String m_introduct;
	private String m_address;
	private int m_isAdmin;
	private int m_isDel;
	private String m_reg_date;
}
