<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>양파마켓 | 글수정</title>
<link rel="stylesheet" type="text/css" href="/resources/css/product/register.css">
<link rel="stylesheet" type="text/css" href="/resources/css/all.css">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=28524d982cffea013a5f7c234898c8df&libraries=services"></script>
</head>
<body>
<jsp:include page="../layout/header.jsp"></jsp:include>
<c:set var="pvo" value="${pdto.pvo }"></c:set>
<c:set var="flist" value="${pdto.piList }"></c:set>
<form id="registerForm" action="/product/modify" method="post" enctype="multipart/form-data" onSubmit="validateForm(event);">
	<div id="register-wrap">
		
		<div id="image-wrap">
			<div class="register-title">상품이미지</div>
			<div id="file-wrap">
				<ul id="file-list" class="file-ul">
					<li class="file-li">
						이미지 등록
						<input type="file" id="file" name="files" onchange="setDetailImage(event);" multiple class="file-input" accept="image/*">
					</li>
					<c:forEach items="${flist }" var="flist">
						<li class="regi-file">
							<img src="/resources/fileUpload/${flist.pi_dir }/${flist.pi_uuid }_th_${flist.pi_name }" style="width:202px; height:202px;">
							<button type="button" class="file-del" data-uuid="${flist.pi_uuid }" onclick="deleteFile(this);"></button>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		
		<div id="title-wrap">
			<div class="register-title">제목</div>
			<div id="errorFlex">
			<div id="all-title">
				<input type="hidden" name="p_number" value="${pvo.p_number }">
				<input class="border-gray title-input" id="nameInput" name="p_name" value="${pvo.p_name }" placeholder="상품 제목을 입력해 주세요." onkeyup="counterTitle(); deleteErrorMsg();" maxlength="20">
				<a href="/" class="input-a">거래금지품목</a>
				<div><span id="countingTitle">0/20</span></div>
			</div>
			<span id="nameError" class="error-message"></span>
			</div>
		</div>
		
		<div id="cate-wrap">
			<div class="register-title">카테고리</div>
			<div class="two-cate">
			<div class="cate-wrapper border-gray">
				<div class="cate-box">
					<ul class="cate-ul">
						<li class="cate-li">
							<button type="button" class="cate-btn" value="전자기기">전자기기</button>
						</li>
						<li class="cate-li">
							<button type="button" class="cate-btn" value="아웃도어, 스포츠">아웃도어, 스포츠</button>
						</li>
						<li class="cate-li">
							<button type="button" class="cate-btn" value="자동차용품, 공구">자동차용품, 공구</button>
						</li>
						<li class="cate-li">
							<button type="button" class="cate-btn" value="가구, 조명">가구, 조명</button>
						</li>
						<li class="cate-li">
							<button type="button" class="cate-btn" value="유아, 완구">유아, 완구</button>
						</li>
						<li class="cate-li">
							<button type="button" class="cate-btn" value="생활, 주방, 건강">생활, 주방, 건강</button>
						</li>
						<li class="cate-li">
							<button type="button" class="cate-btn" value="패션, 잡화">패션, 잡화</button>
						</li>
						<li class="cate-li">
							<button type="button" class="cate-btn" value="사무, 취미">사무, 취미</button>
						</li>
					</ul>
				</div>
			</div>
			<div id="select-cate">	
				<span> 선택한 카테고리 > </span> <input type="text" name="p_category" id="p_category" value="${pvo.p_category }" onblur="deleteErrorMsg();">
				<span id="cateError" class="error-message"></span>
			</div>
			</div>
		</div>
		
		<div id="loca-wrap">
			<div class="register-title regi-location">거래 희망 장소</div>
			<div id="search-loca">
				<input type="text" id="search-address" class="border-gray search-input" name="p_location" value="${pvo.p_location }" placeholder="주소 검색을 눌러 입력해 주세요." onblur="deleteErrorMsg();" readonly="readonly">
				<input type="button" class="background-gray search-loca-btn" onclick="search_loca()" value="주소 검색"><br>
				<span id="locationError" class="error-message"></span>
				<div id="regi-map" style="width:800px;height:300px;margin-top:10px;display:none"></div>
			</div>
		</div>
		
		<div id="price-wrap">
			<div class="register-title">가격</div>
			<div id="price-all">
				<div>
					<input class="border-gray title-input" name="p_price" id="priceInput" value="${pvo.p_price }" placeholder="숫자만 입력해 주세요." onkeyup="deleteErrorMsg();" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
					<span>원</span>
				</div>
				<span id="priceError" class="error-message"></span>
			</div>
		</div>
		
		<div id="info-wrap">
			<div class="register-title">설명</div>
			<div id="info-all">
				<div id="regi-info">
					<textarea rows="10" class="info-text border-gray" name="p_info" id="infoInput" onkeyup="counterInfo(); deleteErrorMsg();" 
					placeholder="여러 장의 상품 사진과 구입 연도, 브랜드, 사용감, 하자 유무 등 구매자에게 필요한 정보를 꼭 포함해 주세요.">${pvo.p_info }</textarea>
					<span id="countingInfo">0/2000</span>
				</div>
				<span id="infoError" class="error-message"></span>
			</div>
		</div>
		
		<div id="pay-wrap">
			<div class="register-title">결제</div>
			<div>
				<label class="pay-label">
					<input type="radio" name="p_pay" value="양파페이" <c:if test="${pvo.p_pay eq '양파페이' }">checked="checked"</c:if>>
					<span>양파페이</span>
  				</label>
  				<label>
					<input type="radio" name="p_pay" value="만나서 현금 결제" <c:if test="${pvo.p_pay eq '만나서 현금 결제' }">checked="checked"</c:if>>
					<span>만나서 현금 결제</span>
				</label>
			</div>
		</div>
		<div id="btn-wrap">
			<button id="productRegiBtn" class="background-purple regi-btn">수정하기</button>
			<a href="javascript:window.history.back();"><button type="button" class="background-gray regi-btn">취소</button></a>
		</div>
	</div>
</form>	

<jsp:include page="../layout/footer.jsp"></jsp:include>

<script type="text/javascript" src="/resources/js/product/register.js"></script>
</body>
</html>