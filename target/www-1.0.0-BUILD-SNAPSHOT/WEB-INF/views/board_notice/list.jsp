<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" type="image/png" href="/resources/image/favicon.png">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet" type="text/css" href="/resources/css/all.css">
<link rel="stylesheet" type="text/css" href="/resources/css/board_notice/list.css">
<title>양파마켓 | 공지사항</title>
<style>

</style>
</head>
<body>
	<jsp:include page="../layout/header.jsp"></jsp:include>
		
	
	<div id="container">
		
		<div id="inner-box">
		<span class="attachment-text">양파마켓</span> 
		<span class="attachment-text">>공지사항</span><br>
		</div>
	
		<div id="inner-box">
			<div id="second-inner">
				<h3>공지사항</h3>
				<c:if test="${ses.m_isAdmin eq 1 }">
					<div id="comment-btn">
						<a href="/board_notice/register"><button class="background-purple submit-button">등록하기</button></a>
					</div>
				</c:if>
			</div>		
		</div>
		
		<!-- <div id="inner-box">
			<div id="searchBox" class="border-gray">
				<span id="searchIcon" class="material-symbols-outlined">search</span>
				<input type="text" id="searchInput">
			</div>
		</div> -->
		
		<div id="inner-box">
			<div id="notice">전체공지</div>
			<c:forEach items="${bnvo }" var="bnvo">
				<div id="not-title">
					<span class="material-symbols-outlined">check_circle</span>
					<a href="/board_notice/detail?bn_number=${bnvo.bn_number }"><span>${bnvo.bn_title }</span></a>
				</div>
			</c:forEach>
		</div>
		
		<!-- <div id="showAll">
			<a><span>문서 모두보기</span></a>
		</div>
		
		<div id="inner-box">
			페이지네이션
			<div id="page-wrap">
				이전페이지
				<div class="background-gray page-btn">&lt;</div>
				컨트롤러에서 page 정보를 싣고 와야 함
				숫자 페이지
				<div class="background-gray page-btn">i</div>
				다음페이지
				<div class="background-gray page-btn">&gt;</div>
			</div>
		</div> -->
		
	</div>

	<jsp:include page="../layout/footer.jsp"></jsp:include>	
</body>
</html>