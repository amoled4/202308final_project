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
public class BoardLocationImageVO {
	
	private String blf_uuid;
	private int bl_number;
	private String blf_name;
	private String blf_dir;
}
