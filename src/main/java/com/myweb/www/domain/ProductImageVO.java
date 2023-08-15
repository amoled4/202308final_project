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
public class ProductImageVO {
	private String pi_uuid;
	private int p_number;
	private String pi_name;
	private String pi_dir;
}
