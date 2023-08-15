<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>양파마켓 | 지역게시판</title>
<link rel="stylesheet" type="text/css" href="/resources/css/all.css">
<link rel="stylesheet" type="text/css" href="/resources/css/board_location/register.css">
<link rel="icon" type="image/png" href="/resources/image/favicon.png">
<style>
</style>
</head>
<body>
	<jsp:include page="../layout/header.jsp"></jsp:include>		
	<div id="container">
		
		<div class="inner-box">
		<span class="attachment-text">양파마켓 동네생활</span> 
		<span class="attachment-text">>글쓰기</span><br>
		</div>
	
		<div class="inner-box">
			<h3>글쓰기</h3>			
		</div>
		
		<form action="/board_location/register" method="post" enctype="multipart/form-data">
		<input type="hidden" name="bl_location" class="location-sub" value="${ses.m_address }">
		<input type="hidden" name="bl_writer" value="${ses.m_nick_name }">
		<input type="hidden" name="bl_m_number" value="${ses.m_number }">
		<div class="inner-box">
			<div class="loc-select"><label for="loc-select">주제</label></div>
			<select name="bl_subject" id="loc-select" class="border-gray">
			    <option disabled>주제를 선택해주세요</option>
			    <option value="동네질문">동네질문</option>
			    <option value="동네사건사고">동네사건사고</option>
			    <option value="분실/실종센터">분실/실종센터</option>
			    <option value="생활정보">생활정보</option>
			    <option value="해주세요">해주세요</option>
			</select>
		</div>
		
		<div class="inner-box">
			<div class="loc-content"><label for="loc-content">제목</label></div>
 			<input class="border-gray" id="loc-content" name="bl_title"
 			placeholder="제목을 입력해주세요">
		</div>		
		
		<div class="inner-box">
			<div class="loc-content"><label for="loc-content">본문</label></div>
 			<textarea class="border-gray" id="loc-content" name="bl_content" 
 			rows="20" placeholder="우리 동네 관련된 질문이나 이야기를 해보세요."></textarea>
		</div>		
		
		<div class="inner-box">
			<span>사진첨부</span><br>
			<span class="attachment-text">사진을 첨부하면 정확한 답변을 받을 수 있어요</span><br>
			<div class="file-container">
			  <div class="file-box">
			  	
		            <input type="file" id="file" name="files" class="file-input" onchange="setDetailImage(event);" multiple>
       			
			  </div>
			</div>	
		</div>
		
		<div class="inner-box">
		  <div class="button-container">
		    <button class="background-purple submit-button">등록</button>
		    <a href="javascript:window.history.back();"><button type="button" class="background-gray cancel-button submit-button">취소</button></a>
		  </div>
		</div>
				
		</form>
		
	</div>
	
	
	
	
	
	<jsp:include page="../layout/footer.jsp"></jsp:include>	
	<script type="text/javascript" src="/resources/js/board_location/register.js"></script>
</body>
</html>