package com.myweb.www.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import org.springframework.stereotype.Service;

import com.myweb.www.domain.ChatRoomVO;
import com.myweb.www.domain.MemberDTO;
import com.myweb.www.domain.MemberImageVO;
import com.myweb.www.domain.MemberVO;
import com.myweb.www.domain.ProductDTO;
import com.myweb.www.domain.ProductImageVO;
import com.myweb.www.domain.ProductLikeVO;
import com.myweb.www.domain.ProductPagingVO;
import com.myweb.www.domain.ProductReviewDTO;
import com.myweb.www.domain.ProductReviewVO;
import com.myweb.www.domain.ProductVO;
import com.myweb.www.domain.ReviewPagingVO;
import com.myweb.www.repository.ChatDAO;
import com.myweb.www.repository.MemberDAO;
import com.myweb.www.repository.MemberImageDAO;
import com.myweb.www.repository.ProductDAO;
import com.myweb.www.repository.ProductImageDAO;
import com.myweb.www.repository.ProductReviewDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
	@Inject
	private ProductDAO pdao;
	@Inject
	private ProductImageDAO pidao;
	@Inject
	private MemberDAO mdao;
	@Inject
	private MemberImageDAO midao;
	@Inject
	private ChatDAO cdao;
	@Inject
	private ProductReviewDAO prdao;


	@Override
	public int getTotalCount(ProductPagingVO ppvo) {
		// TODO Auto-generated method stub
		return pdao.getTotalCount(ppvo);
	}

	@Override
	public int productRegister(ProductDTO pdto) {
		int isOk = pdao.insertProductRegister(pdto.getPvo());
		if(pdto.getPiList()!=null) {
			if(isOk>0 && pdto.getPiList().size()>0) {
				int p_number = pdao.selectPnumber();
				for(ProductImageVO pivo : pdto.getPiList()) {
					pivo.setP_number(p_number);
					isOk *= pidao.insertProductImage(pivo);
				}
			}
		}
		return isOk;
	}

	@Override
	public List<ProductDTO> listFile(ProductPagingVO ppvo) {
		List<ProductDTO> listPdto = new ArrayList<ProductDTO>();
		List<ProductVO> listPvo = pdao.selectProduct(ppvo);
		for(ProductVO pvo : listPvo) {
			List<ProductImageVO> piList = pidao.selectFileList(pvo);
			
			listPdto.add(new ProductDTO(pvo, null, piList, null));
		}
		return listPdto;
	}

	@Override
	public ProductDTO detail(int p_number) {
		ProductDTO pdto = new ProductDTO();
		pdto.setPvo(pdao.selectPno(p_number));
		pdto.setPiList(pidao.selectFile(p_number));
		
		// detail 상점 정보 가져오기
		MemberVO mvo = mdao.getMemberProduct(pdto.getPvo().getM_number());
		MemberImageVO mivo = midao.selectMemberImage(pdto.getPvo().getM_number());
		MemberDTO mdto = new MemberDTO(mvo, mivo);
		pdto.setMdto(mdto);
		log.info("service : "+pdto.toString());
		return pdto;
	}

	@Override
	public ProductDTO detail(int p_number, int sesM_number) {
		ProductDTO pdto = new ProductDTO();
		pdto.setPvo(pdao.selectPno(p_number));
		pdto.setPlvo(pdao.selectProductLike(p_number, sesM_number));
		pdto.setPiList(pidao.selectFile(p_number));
		
		// detail 상점 정보 가져오기
		MemberVO mvo = mdao.getMemberProduct(pdto.getPvo().getM_number());
		MemberImageVO mivo = midao.selectMemberImage(pdto.getPvo().getM_number());
		MemberDTO mdto = new MemberDTO(mvo, mivo);
		pdto.setMdto(mdto);
		return pdto;
	}

	@Override
	public int updateStatus(ProductVO pvo) {
		log.info(">>> pvo = " + pvo.toString());
		
		int isOk = pdao.updateStatus(pvo);
		log.info(">> 구매상태 > "+(isOk>0?"성공":"실패"));
		return isOk;
	}

	@Override
	public int readCount(int p_number) {
		int isOk = pdao.updateReadCount(p_number);
		log.info(">> readcount > "+(isOk>0?"성공":"실패"));
		return isOk;
	}

	@Override
	public boolean insertLikeMember(ProductLikeVO plvo) {
		log.info(">>> plvo = " + plvo.toString());
		
		ProductLikeVO existingLikes = pdao.getLikeRecord(plvo);
		log.info("existingLikes 결과값"+existingLikes);
		if(existingLikes == null) {
			int isOk = pdao.insertLikeMember(plvo);
			if (isOk > 0) {
				pdao.updateLike(plvo.getP_number());
				log.info(">> 찜 > "+(isOk>0?"성공":"실패"));
				return true;
			}
		} else {
			pdao.deleteLikeMember(plvo);
			pdao.updateUnlike(plvo.getP_number());
			return false;
		}
		return false;
	}

	@Override
	public int updateModify(ProductDTO pdto) {
		int isOk = pdao.updateModify(pdto.getPvo());
		if(pdto.getPiList()==null) {
			isOk *= 1;
		} else {
			if(isOk>0&&pdto.getPiList().size()>0) {
				int p_number = pdto.getPvo().getP_number();
				for(ProductImageVO pivo : pdto.getPiList()) {
					pivo.setP_number(p_number);
					log.info(">>> 새로운 파일 삽입 > "+pivo.toString());
					isOk *= pidao.insertProductImage(pivo);
				}
			}
		}
		return isOk;
	}

	@Override
	public int removeFile(String pi_uuid) {
		// TODO Auto-generated method stub
		return pidao.deleteFile(pi_uuid);
	}

	@Override
	public int removeIsDel(int p_number) {
		// TODO Auto-generated method stub
		return pdao.updateIsDel(p_number);
	}
	
	// detail review
	@Override
	public ProductReviewDTO getReview(int p_number, int sesM_number) {
		ProductReviewDTO prdto = new ProductReviewDTO();
		prdto.setPvo(pdao.selectPno(p_number));
		prdto.setPiList(pidao.selectFile(p_number));
		prdto.setPrvo(prdao.selectPrvo(p_number, sesM_number));
		log.info("피알브이오 : "+prdao.selectPrvo(p_number,sesM_number));
		prdto.setPrvol(prdao.selectPrvol(prdto.getPvo().getM_number()));
		prdto.setMivo(midao.selectMivo(prdto.getPvo().getM_number()));

		MemberVO mvo = mdao.getMemberProduct(prdto.getPvo().getM_number());
		prdto.setMvo(mvo);
		ChatRoomVO crvo = cdao.getChat(p_number);
		prdto.setCrvo(crvo);
		prdto.mergeLists();
		log.info("prdto~: "+prdto.toString());
		return prdto;
		
	}
	
	@Override
	public int insertReview(ProductReviewDTO prdto) {
		
		return prdao.insertReview(prdto.getPrvo());
	}
	
	// store 리뷰
	@Override
	public List<ProductReviewDTO> getReviewList(ReviewPagingVO rpvo) {
		List<ProductReviewDTO> listPrdto = new ArrayList<ProductReviewDTO>();
		List<ProductReviewVO> listPrvo = prdao.selectReviewList(rpvo);
		for(ProductReviewVO a : listPrvo) {
			List<MemberImageVO> mivo = midao.selectRvMivo(a);
			listPrdto.add(new ProductReviewDTO(null, null, a, null, null, null, mivo, null));
			log.info("엠아븨오"+mivo.toString());
		}
		return listPrdto;
	}

	// store 리뷰
//	@Override
//	public List<ProductReviewDTO> getReviewList(ReviewPagingVO rpvo) {
//		ProductReviewDTO prdto = new ProductReviewDTO();
//		prdto.setPrvol(prdao.selectReviewList(rpvo));
//		prdto.setMivo(midao.selectRvMivo(rpvo));
//		prdto.mergeLists();
//		log.info("prdto!!: "+prdto.toString());
//		log.info("나와라: "+rpvo.getPr_seller());
//		log.info("rpvo??뭐가들었니 : "+rpvo.toString());
//		
//		return prdto;
//	}


	@Override
	public int getTotalRvCount(ReviewPagingVO rpvo) {
		// TODO Auto-generated method stub
		return prdao.getTotalRvCount(rpvo);
	}
	
	//작성자:안세호(내 상품 리스트 가져오기)
	@Override
	public List<ProductDTO> getProductByMember(Integer m_number) {
	    List<ProductVO> products = pdao.selectProductsByMember(m_number);
	    List<ProductDTO> productList = new ArrayList<>();
	    
	    for (ProductVO product : products) {
	        List<ProductImageVO> images = pidao.selectImagesByProduct(product.getP_number());
	        ProductDTO productDTO = new ProductDTO();
	        productDTO.setPvo(product);
	        productDTO.setPiList(images);
	        productList.add(productDTO);
	    }

	    return productList;
	}
	//작성자:안세호(찜한 게시글 가져오기)
	@Override
	public List<ProductDTO> getLikedProductsByMember(Integer m_number) {
	    List<ProductVO> products = pdao.selectLikedProductsByMember(m_number);
	    List<ProductDTO> likedProductList = new ArrayList<>();
	    
	    for (ProductVO product : products) {
	        List<ProductImageVO> images = pidao.selectImagesByProduct(product.getP_number());
	        ProductLikeVO like = pdao.selectProductLike(product.getP_number(), m_number);
	        
	        ProductDTO productDTO = new ProductDTO();
	        productDTO.setPvo(product);
	        productDTO.setPiList(images);
	        productDTO.setPlvo(like);
	        
	        likedProductList.add(productDTO);
	    }

	    return likedProductList;
	}

	


	
	



	
}
