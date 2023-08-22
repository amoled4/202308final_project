<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>양파마켓 | 거래 후기 보내기</title>
<link rel="stylesheet" type="text/css" href="/resources/css/all.css">
<link rel="stylesheet" type="text/css" href="/resources/css/product/review.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
</head>
<body>
<c:set var="pvo" value="${prdto.pvo }"></c:set>
<c:set var="flist" value="${prdto.piList }"></c:set>
<c:set var="mvo" value="${prdto.mvo }"></c:set>
	<form action="/product/review" method="post">
	<input type="hidden" name="pr_buyer" value="${ses.m_number }">
	<input type="hidden" name="pr_seller" value="${pvo.m_number }">
	<input type="hidden" name="pr_p_number" value="${pvo.p_number }">
		<div id="review-wrap">
			<div id="close" onclick="closeModal()">
				<span class="material-symbols-outlined">close</span>
			</div>
			<div id="rv-product">
				<div id="rv-product-image">
					<img alt="없음" src="/resources/fileUpload/${flist[0].pi_dir }/${flist[0].pi_uuid }_th_${flist[0].pi_name }" style="width:50px; height:50px;">
				</div>
				<div id="rv-product-title">${pvo.p_name }</div>
			</div>
			<div id="rv-text">${ses.m_nick_name } 님, <br> ${mvo.m_nick_name } 님과 거래가 어떠셨나요?</div>
			<div id="rv-star-score">
				<input type="radio" id="5-stars" name="pr_score" value="5"/>
				<label for="5-stars" class="star">★</label>
				<input type="radio" id="4-stars" name="pr_score" value="4"/>
				<label for="4-stars" class="star">★</label>
				<input type="radio" id="3-stars" name="pr_score" value="3"/>
				<label for="3-stars" class="star">★</label>
				<input type="radio" id="2-stars" name="pr_score" value="2"/>
				<label for="2-stars" class="star">★</label>
				<input type="radio" id="1-stars" name="pr_score" value="1"/>
				<label for="1-stars" class="star">★</label>
			</div>
			<div class="rv-content-wrap">
				<textarea rows="5" cols="69" name="pr_content" class="border-gray rv-content" id="rvContent" placeholder="악의적인 내용을 남길 경우 무통보 삭제될 수 있습니다. (5자 이상)"></textarea>
			</div>
			<button class="rv-btn background-purple" id="rvBtn">후기 보내기</button>
		</div>
	</form>
<script type="text/javascript">
		var p_number = ${param.p_number};
</script>
</body>
</html>