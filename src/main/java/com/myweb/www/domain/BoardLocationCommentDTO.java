package com.myweb.www.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class BoardLocationCommentDTO {
	private BoardLocationCommentVO blcvo;
	private BoardLocationVO blvo;
	private List<MemberImageVO> mivo;
}
