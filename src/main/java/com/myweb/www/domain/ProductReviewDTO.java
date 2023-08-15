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
public class ProductReviewDTO {
	private List<ProductReviewVO> prvol;
	private List<ProductReviewVO> prvol2;
	private ProductReviewVO prvo;
	private ProductVO pvo;
	private List<ProductImageVO> piList;
	private MemberVO mvo;
	private List<MemberImageVO> mivo;
	private ChatRoomVO crvo;
	
	// prvol와 mivo를 병합하는 메서드
	public void mergeLists() {
		List<ProductReviewVO> prvol = getPrvol();
		List<MemberImageVO> mivo = getMivo();
		
		for(ProductReviewVO prvo : prvol) {
			int m_number = prvo.getPr_buyer();
			for(MemberImageVO mivoList : mivo) {
				if(mivoList.getM_number() == m_number) {
					prvo.setMivo(mivoList);
					break;
				}
			}
		}
	}
	
}
