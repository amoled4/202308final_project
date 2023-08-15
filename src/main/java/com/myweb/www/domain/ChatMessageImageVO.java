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
public class ChatMessageImageVO {
	private String cmi_uuid;
	private Integer cm_number;
	private String cmi_name;
	private String cmi_dir;
}
