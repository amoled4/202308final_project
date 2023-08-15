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
public class MemberImageVO {
	private String mi_uuid;
	private String mi_dir;
	private int m_number;
	private String mi_name;
}
