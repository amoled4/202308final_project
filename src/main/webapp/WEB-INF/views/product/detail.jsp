<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.myweb.www.domain.ProductVO" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("replaceChar", "\n"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>양파마켓 | 글보기</title>
<link rel="stylesheet" type="text/css" href="/resources/css/product/detail.css">
<link rel="stylesheet" type="text/css" href="/resources/css/all.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,1,0" />
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=28524d982cffea013a5f7c234898c8df&libraries=services"></script>

</head>
<body>
	<jsp:include page="../layout/header.jsp"></jsp:include>
	<c:set var="pvo" value="${pdto.pvo }"></c:set>
	<c:set var="flist" value="${pdto.piList }"></c:set>
	<c:set var="plvo" value="${pdto.plvo }"></c:set>
	<c:set var="mvo" value="${pdto.mdto.mvo }"></c:set>
	<c:set var="mivo" value="${pdto.mdto.mivo }"></c:set>
	<c:set var="crvo" value="${prdto.crvo }"></c:set>
	<c:set var="prvo" value="${prdto.prvo }"></c:set>
	<c:set var="prvol" value="${prdto.prvol }"></c:set>
	<!-- 전체 틀 -->
	<div id="detail-wrap">
		<div id="detail-category">
			<div class="detail-category2">
				<div class="detail-cate-home"><span class="material-symbols-outlined detail-cate-home">home</span>홈</div>
				<div class="detail-category2"><span class="material-symbols-outlined detail-cate-home">chevron_right</span></div>
				<div id="detail-cate-wrap">
					<div id="detatil-cate-wrapper">
						<div id="detail-cate">
							${pvo.p_category }
							<span class="material-symbols-outlined arrow-down">expand_more</span>
						</div>
						<div class="detail-cate-scroll">
							<a class="scroll-detail" onclick="location.href='/product/list?p_category=전자기기'">전자기기</a>
							<a class="scroll-detail" onclick="location.href='/product/list?p_category=아웃도어, 스포츠'">아웃도어, 스포츠</a>
							<a class="scroll-detail" onclick="location.href='/product/list?p_category=자동차용품, 공구'">자동차용품, 공구</a>
							<a class="scroll-detail" onclick="location.href='/product/list?p_category=가구, 조명'">가구, 조명</a>
							<a class="scroll-detail" onclick="location.href='/product/list?p_category=유아, 완구'">유아, 완구</a>
							<a class="scroll-detail" onclick="location.href='/product/list?p_category=생활, 주방, 건강'">생활, 주방, 건강</a>
							<a class="scroll-detail" onclick="location.href='/product/list?p_category=패션, 잡화'">패션, 잡화</a>
							<a class="scroll-detail" onclick="location.href='/product/list?p_category=사무, 취미'">사무, 취미</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="top-wrap">
			<hr>
			<br>
			<!-- 거래 상태 표시 -->
			<c:if test="${pvo.m_number eq ses.m_number }">
				<div>
					<select name="p_status" class="product-status" onchange="changeStatus(this)">
		 	 			<option disabled>판매상태</option>
		  				<option value="0" <c:if test="${pvo.p_status eq 0 }">selected="selected"</c:if>>구매가능</option>
		  				<option value="1" <c:if test="${pvo.p_status eq 1 }">selected="selected"</c:if>>예약중</option>
		  				<option value="2" <c:if test="${pvo.p_status eq 2 }">selected="selected"</c:if>>거래완료</option>
					</select>
				</div>
			</c:if>
					
			<div id="detail-left">
			<div class="slider_img">
			<div class="slider_inner">
			<c:forEach items="${flist }" varStatus="status" var="flist">
				<div class="detail-img">
					<img alt="없음" src="/resources/fileUpload/${flist.pi_dir }/${flist.pi_uuid }_${flist.pi_name }" width="300px" height="300px">
				</div>
			</c:forEach>
			</div>
			</div>
				<div class="slider-dot">
                	<!-- <a href="#" class="dot active">이미지1</a>
                	<a href="#" class="dot">이미지2</a>
                	<a href="#" class="dot">이미지3</a>
               		<a href="#" class="dot">이미지4</a>
               	 	<a href="#" class="dot">이미지5</a> -->
            	</div>
			</div>
			<div id="detail-right">
				<div id="title-wrap">
					<input type="hidden" value="${pvo.p_number }"> 
					<div id="detail-title">${pvo.p_name }</div>
					<div id="price-wrap">
						<div id="detail-price"><fmt:formatNumber value="${pvo.p_price }" pattern="#,###,###" /><span style="font-size:24px">원</span></div>
						<div id="detail-pay">${pvo.p_pay }</div>
					</div>
				</div>
				<div id="little-wrap">
					<!-- 찜 개수 -->
					<div id="detail-like">
						<span class="material-symbols-outlined md-15">favorite</span>
						${pvo.p_like }
					</div>
					<!-- 조회수 -->
					<div id="detail-cnt">
						<span class="material-symbols-outlined md-15">visibility</span>
						${pvo.p_read_count }
					</div>
					<!-- 등록일 -->
					<div id="detail-date">
						<span class="material-symbols-outlined md-15">schedule</span>
						<fmt:parseDate value="${pvo.p_reg_date }" var="regDate" pattern="yyyy-MM-dd HH:mm:ss" />
						<fmt:formatDate value="${regDate }" pattern="yyyy-MM-dd" />
					</div>
				</div>
				
				<div class="btn-wrap">
					<a href="javascript:window.history.back();"><button type="button" class="background-gray detail-btn">다른 상품 찾기</button></a>
					<c:choose>
						<c:when test="${ses.m_number ne pvo.m_number && ses.m_number ne null }">
							<c:choose>
								<c:when test="${plvo eq null }">
									<button type="button" id="p_like" class="background-gray detail-btn" onclick="updateLike(event)">찜</button>
								</c:when>
								<c:when test="${plvo ne null }">
									<button type="button" id="p_like" class="background-gray detail-btn" onclick="updateLike(event)">찜 해제</button>
								</c:when>
							</c:choose>
							<button type="button" class="background-purple detail-btn" onclick="createChatRoom(${param.p_number})">채팅하기</button>
							<c:if test="${ses.m_number eq crvo.cr_buyer && pvo.p_number eq crvo.cr_number }">
							    <c:if test="${prvo.pr_buyer ne ses.m_number}">
							        <button type="button" class="background-purple detail-btn" onclick="reviewModal()">후기 쓰기</button>
							   </c:if>
							  	 
							</c:if>			
						</c:when>
						
						<c:when test="${ses.m_number eq pvo.m_number && ses.m_number ne null }">
							<a href="/product/modify?p_number=${pvo.p_number }"><button type="button" class="background-gray detail-btn">수정하기</button></a>
							<button type="button" class="background-gray detail-btn" id="removeBtn">삭제하기</button>
							<button type="button" class="background-purple detail-btn" onclick="location.href='/chat/main'">내 채팅방</button>
						</c:when>
					</c:choose>
				</div>
			</div>
		</div>
		<div class="modal-content"></div>
		<br>
		<br>
		<hr>
		<div id="bottom-wrap">
			<div id="both-wrap">
				<div id="info-wrap">
					<div class="detail-pro-info"><b>상품 정보</b></div>
					<div id="detail-info">
						${fn:replace(pvo.p_info, replaceChar, "<br/>") }
					</div>
				</div>
				<div id="product-member-wrap">
					<div class="detail-pro-info"><b>상점정보</b></div>
					<c:if test="${ses.m_number eq null }">
						<div>로그인하셔야 확인 가능합니다.</div>
					</c:if>
					<c:if test="${ses.m_number ne null }">
					<div id="pro-mem-wrap">
						<div id="product-store">
							<div id="product-profile" onclick="location.href='/product/store?m_number=${mvo.m_number}'">
								<a class="pro-file-photo">
									<!-- 회원이 프로필 사진을 등록 안했을 경우 -->
									<c:if test="${empty mivo.mi_name}">
										<img alt="프로필 사진" src="/resources/image/profile.png" style="width:48px;height:48px;">
									</c:if>
									<!-- 회원이 프로필 사진을 등록 했을 경우 -->
									<c:if test="${not empty mivo.mi_name}">
										<img alt="프로필 사진" src="/resources/fileUpload/${mivo.mi_dir}/${mivo.mi_uuid}_th_${mivo.mi_name}" style="width:48px;height:48px;">
									</c:if>
								</a>
							</div>
							
							<div>
								<div class="pointer" onclick="location.href='/product/store?m_number=${mvo.m_number}'">${mvo.m_nick_name }</div>
								<%-- <div><a class="store-cnt">상품 ${productCount} 개</a></div> --%>
							</div>
						</div>
						<div>
							<div id="store-review">상점후기 <span id="review-cnt">${fn:length(prvol) }</span></div>
						</div>
						<!-- 상점후기 -->
						<c:forEach items="${prvol }" var="prvoList" begin="0" end="2">
							<div>
								<div class="product-review">
									<a class="review-profile" href="/product/store?m_number=${prvoList.pr_buyer }">
									<!-- 회원이 프로필 사진을 등록 안했을 경우 -->
									<c:if test="${empty prvoList.mivo.mi_name}">
										<img alt="프로필 사진" src="/resources/image/profile.png" style="width:32px;height:32px">
									</c:if>
									<!-- 회원이 프로필 사진을 등록 했을 경우 -->
									<c:if test="${not empty prvoList.mivo.mi_name}">
										<img alt="프로필 사진" src="/resources/fileUpload/${prvoList.mivo.mi_dir}/${prvoList.mivo.mi_uuid}_th_${prvoList.mivo.mi_name}" style="width:32px;height:32px">
									</c:if>
									</a>
									<div class="review-info">
										<div class="reviewer">
											<a class="review-nick" href="/product/store?m_number=${prvoList.pr_buyer }">${prvoList.pr_buyer }</a>
										</div>
										<div class="review-score">
											<div class="star-score">
											<c:forEach begin="1" end="${prvoList.pr_score }">
												<img alt="별점이미지" src="/resources/image/star.png" style="width:15px;height:14px">
											</c:forEach>
											</div>
										</div>
										<div class="review-detail">${prvoList.pr_content }</div>
									</div>
								</div>
							</div>
						</c:forEach>
						<!-- 상점후기 끝 -->
					</div>
					</c:if>
				</div>
			</div>
			<div id="location-wrap">
				<div id="location-detail-wrap">
					<div id="detail-loca"><b>거래 희망 장소</b></div>
					<div id="detail-detail-loca">&gt; <input type="text" id="getLoca" readonly="readonly" value="${pvo.p_location }" style="border:none;"></div>
				</div>
					<div id="detail-map"></div>
			</div>
		</div>
	</div>
	
<jsp:include page="../layout/footer.jsp"></jsp:include>
<script type="text/javascript" src="/resources/js/product/detail.js"></script>
<script type="text/javascript">
		var p_number = ${param.p_number};
		var sessionMemberNumber = '<c:out value="${ses.m_number }"></c:out>';
</script>
</body>
</html>