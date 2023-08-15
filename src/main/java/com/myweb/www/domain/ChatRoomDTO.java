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
public class ChatRoomDTO {
	private ChatRoomVO crvo;
	private String lastMessage;
	private Integer notReadCount;
	private MemberVO senderMvo;
	private MemberVO receiverMvo;
}
