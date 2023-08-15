package com.myweb.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChatMessageVO {
	private Integer cm_number;
	private Integer cr_number;
	private Integer cm_sender;
	private String cm_content;
	private String cm_send_date;
	private String cm_type;
}
