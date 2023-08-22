<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>양파마켓 | 목록</title>
<link rel="stylesheet" type="text/css" href="/resources/css/all.css">
<link rel="stylesheet" type="text/css" href="/resources/css/product/list.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,1,0" />
</head>
<body>
	<jsp:include page="../layout/header.jsp"></jsp:include>
	<!-- 전체 감싸는 div -->
	<div id="list-wrapper">
		<div id="cate-wrap">
			<span><b>카테고리</b></span><br>
		</div>
			<div class="border-gray category" onclick="location.href='/product/list?p_category=전자기기'">전자기기</div>
			<div class="border-gray category" onclick="location.href='/product/list?p_category=아웃도어, 스포츠'">아웃도어, 스포츠</div>
			<div class="border-gray category" onclick="location.href='/product/list?p_category=자동차용품, 공구'">자동차용품, 공구</div>
			<div class="border-gray category" onclick="location.href='/product/list?p_category=가구, 조명'">가구, 조명</div> <br>
			<div class="border-gray category" onclick="location.href='/product/list?p_category=유아, 완구'">유아, 완구</div>
			<div class="border-gray category" onclick="location.href='/product/list?p_category=생활, 주방, 건강'">생활, 주방, 건강</div>
			<div class="border-gray category" onclick="location.href='/product/list?p_category=패션, 잡화'">패션, 잡화</div>
			<div class="border-gray category" onclick="location.href='/product/list?p_category=사무, 취미'">사무, 취미</div>
		<br>

		<div id="result-wrap">
		<!-- 검색했을 경우에만 나타남 -->
		<c:if test="${ppvo.keyword != null }">
			<span><span style="color: #C97FE8"><b>${ppvo.keyword }</b></span>의 검색결과 <span style="color: gray">${pph.totalCount } 개</span></span>
		</c:if>
		</div><br>
		<!-- 상품 리스트 -->
		<div>
		
		<c:forEach items="${productList }" var="productList">
			<c:set value="${productList.piList }" var="piList"></c:set>
			<div class="product-wrapper" onclick="location.href='/product/detail?p_number=${productList.pvo.p_number }'">
				<div class="product-photo">
					<img alt="없음" src="/resources/fileUpload/${productList.piList[0].pi_dir }/${productList.piList[0].pi_uuid }_th_${productList.piList[0].pi_name }" class="product-photo">
					<!-- 구매가능, 예약중, 거래완료 -->
					<div class="product-status">
					<c:if test="${productList.pvo.p_status eq 0 }">
						<img alt="" src="/resources/image/purchase.png">
					</c:if>
					<c:if test="${productList.pvo.p_status eq 1 }">
						<img alt="" src="/resources/image/reservation.png">
					</c:if>
					<c:if test="${productList.pvo.p_status eq 2 }">
						<img alt="" src="/resources/image/completed.png">
					</c:if>
					</div>
				</div>
				<div style="display: none">${productList.pvo.p_number }</div>
				<div class="product-title">${productList.pvo.p_name }</div>
				<div class="product-price"><b><fmt:formatNumber value="${productList.pvo.p_price }" pattern="#,###,###" /> <span style="font-size:12px">원</span></b></div>
				
				<div class="product-location">
					<span class="material-symbols-outlined">location_on</span>
					<span class="location-sub">${productList.pvo.p_location }</span>
				</div>
			</div>
		</c:forEach>
		</div> <br>
		
		<!-- 페이지네이션 -->
		<div id="page-wrap">
			<!-- 이전페이지 -->
			<c:if test="${pph.prev }">
				<div class="background-gray page-btn" onclick="location.href='/product/list?pageNo=${pph.startPage-1}&qty=${pph.ppvo.qty }&keyword=${pph.ppvo.keyword }'">&lt;</div>
			</c:if>
			<!-- 컨트롤러에서 page 정보를 싣고 와야 함 -->
			<!-- 숫자 페이지 -->
			<c:forEach begin="${pph.startPage }" end="${pph.endPage }" var="i">
				<div class="background-gray page-btn" onclick="location.href='/product/list?pageNo=${i}&qty=${pph.ppvo.qty}&keyword=${pph.ppvo.keyword }'">${i }</div>
			</c:forEach>
			<!-- 다음페이지 -->
			<c:if test="${pph.next }">
				<div class="background-gray page-btn" onclick="location.href='/product/list?pageNo=${pph.endPage+1}&qty=${pph.ppvo.qty}&keyword=${pph.ppvo.keyword }'">&gt;</div>
			</c:if>
		</div>
	</div>
<jsp:include page="../layout/footer.jsp"></jsp:include>
<script type="text/javascript" src="/resources/js/product/list.js"></script>
</body>
</html>