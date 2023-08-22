<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
	<link rel="icon" type="image/png" href="/resources/image/favicon.png">
	<link rel="stylesheet" type="text/css" href="/resources/css/layout/header.css">
	<link rel="stylesheet" type="text/css" href="/resources/css/layout/nav.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
	<style>
		.material-symbols-outlined {
			font-variation-settings:
				'FILL' 0,
				'wght' 400,
				'GRAD' 0,
				'opsz' 48
		}
	</style>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
</head>
<body>
	<header>
		<div id="headerContainer">
			<a href="/" id="logoContainer">
				<img alt="로고" src="/resources/image/logo.png" id="logoImage">
				<span id="logoText">양파마켓</span>
			</a>
			<form action="/product/list">
				<div id="searchContainer" class="border-purple">
					<span id="searchIcon" class="material-symbols-outlined">
						search
					</span>
					<input type="text" name="keyword" id="searchInput">
				</div>
			</form>
			<c:if test="${ses.m_mail eq null}">
				<!-- 로그인 전 -->
				<div id="memberContainer">
						<a href="/member/signup">회원가입</a>
						<a href="/member/signin">로그인</a>
				</div>
			</c:if>
			
			<c:if test="${ses.m_mail ne null}">
				<!-- 로그인 후 -->
				<div id="memberContainer1">
						<a href="/product/register">판매하기</a>
						<a href="/member/detail">내 상점</a>
						<a href="/chat/main">
							<!-- 
							<span class="notification">
								<span class="material-symbols-outlined">
									notifications
								</span>
								새로운 메시지가 있습니다!
							</span>
							 -->
							채팅방
						</a>
						<a href="/member/signout">로그아웃</a>
				</div>
			</c:if>
		</div>
	</header>
	
	<nav>
		<div id="navContainer">
			<ul class="menu">
			<li>
			<a href="#" class="material-symbols-outlined">
				menu
			</a>
			<ul class="depth_1">
		      <li><a onclick="location.href='/product/list?p_category=전자기기'">전자기기</a></li>
		      <li><a onclick="location.href='/product/list?p_category=아웃도어, 스포츠'">아웃도어, 스포츠</a></li>
		      <li><a onclick="location.href='/product/list?p_category=자동차용품, 공구'">자동차용품, 공구</a></li>
		      <li><a onclick="location.href='/product/list?p_category=가구, 조명'">가구, 조명</a></li>
		      <li><a onclick="location.href='/product/list?p_category=유아, 완구'">유아, 완구</a></li>
		      <li><a onclick="location.href='/product/list?p_category=생활, 주방, 건강'">생활, 주방, 건강</a></li>
		      <li><a onclick="location.href='/product/list?p_category=패션, 잡화'">패션, 잡화</a></li>
		      <li><a onclick="location.href='/product/list?p_category=사무, 취미'">사무, 취미</a></li>
		    </ul>
		    </li>
			</ul>
			<div id="menuContainer">
				<a href="/board_location/list">양파타운</a>
				<img src="/resources/image/new.png" width="72" height="26" alt="양파타운">
				<a href="/board_qna/register">1:1문의</a>
				<a href="/board_notice/list">공지사항</a>
			</div>
		</div>
	</nav>
</body>

</html>
