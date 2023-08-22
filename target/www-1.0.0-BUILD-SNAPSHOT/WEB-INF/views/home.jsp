<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>양심거래 파트너 양파마켓</title>
	<link rel="shortcut icon" href="/resources/image/favicon.png">
	<link rel="stylesheet" type="text/css" href="/resources/css/all.css">
	<link rel="stylesheet" type="text/css" href="/resources/css/home.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,1,0" />
	<link rel="icon" type="image/png" href="/resources/image/favicon.png">
</head>
<body>
	<jsp:include page="./layout/header.jsp"></jsp:include>
	
	<div id="container">
		
			<div>
				<a href="#"><img alt="메인 이미지" src="/resources/image/메인.png" width="1024px" height="300px"></a>
			</div>
			
			<div id="todayProduct">
				<h1>오늘의 상품</h1>
				<div id="showAllContainer">
					<button class="showAllBox background-purple submit-button" onclick="location.href='/product/list'">보러가기</button>
				</div>
			</div>
				<div id="productContainer">
				<c:forEach items="${productList }" var="productList" begin="0" end="9">
				<c:set value="${productList.piList }" var="piList"></c:set>
					<div onclick="location.href='/product/detail?p_number=${productList.pvo.p_number }'" class="product">
						<div class="product-image">
							<img alt="상품 이미지" src="/resources/fileUpload/${productList.piList[0].pi_dir }/${productList.piList[0].pi_uuid }_th_${productList.piList[0].pi_name }">
						</div>
						
						<div class="product-text">
							<ul>
								<li class="product-title">${productList.pvo.p_name }</li>
								<li class="product-price"><fmt:formatNumber value="${productList.pvo.p_price }" pattern="#,###,###" /> <span style="font-size: 12px;">원</span></li>
							</ul>
						</div>
						
						<div class="product-location">
							<span class="material-symbols-outlined">location_on</span>
							<span class="location-sub">${productList.pvo.p_location }</span>
						</div>
					</div>
					
				</c:forEach>
				</div>
		</div>
	
	
<jsp:include page="./layout/footer.jsp"></jsp:include>
<script type="text/javascript" src="/resources/js/product/list.js"></script>
</body>
</html>
