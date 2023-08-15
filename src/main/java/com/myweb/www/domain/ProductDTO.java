package com.myweb.www.domain;

import java.util.List;

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
public class ProductDTO {
	private ProductVO pvo;
	private ProductLikeVO plvo;
	private List<ProductImageVO> piList;
	private MemberDTO mdto;
}
