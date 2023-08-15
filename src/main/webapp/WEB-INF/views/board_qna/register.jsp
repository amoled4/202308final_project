<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>양파마켓 | 1:1문의하기</title>
<link rel="icon" type="image/png" href="/resources/image/favicon.png">
<link rel="stylesheet" type="text/css" href="/resources/css/all.css">
<link rel="stylesheet" type="text/css" href="/resources/css/board_qna/register.css">
</head>
<body>
	<jsp:include page="../layout/header.jsp"></jsp:include>

	<div id="container">
		
		<div id="inner-box">
		<span class="attachment-text">양파마켓 고객센터</span> 
		<span class="attachment-text">>1:1문의</span><br>
		</div>
	
		<div id="inner-box">
			<h3>1:1문의하기</h3>			
		</div>
		
		<form action="/board_qna/register" method="post" enctype="multipart/form-data">
		<div id="inner-box">
			<input type="hidden" name="bq_writer" value="${ses.m_nick_name }">
			<div class="qna-select"><label for="qna-select">제목</label></div>
			<input class="border-gray" id="loc-content" name="bq_title" placeholder="제목을 입력해주세요">
			<!-- <div class="qna-select"><label for="qna-select">유형선택</label></div>
			<select name="qna" id="qna-select" class="border-gray">
			    <option value="">유형을 선택해주세요</option>
			    <option value="report">거래신고</option>
			    <option value="account">회원/계정</option>
			    <option value="onionPay">양파페이(안전결제)</option>
			    <option value="error">오류/신고/제안</option>
			    <option value="etc">기타</option>
			</select> -->
		</div>
		
		<div id="inner-box">
			<div class="qna-content"><label for="qna-content">문의 내용</label></div>
 			<textarea class="border-gray" id="qna-content" name="bq_content" 
 			rows="10" maxlength="300" placeholder="문의할 내용을 자세히 입력해주세요"></textarea>
		</div>		
		
		<!-- <div id="inner-box">
			<span>사진첨부</span><br>
			<span class="attachment-text">사진을 첨부하면 정확한 답변을 받을 수 있어요</span><br>
			<div class="file-container">
			  <div class="file-box">
				
		            <input type="file" name="qna" accept="image/*" multiple>
       			
			  </div>
			  <div class="file-box"></div>
			  <div class="file-box"></div>
			  <div class="file-box"></div>
			  <div class="file-box"></div>
			</div>	
		</div> -->
		
		<div id="inner-box">
		<button class="background-purple submit-button">제출</button>
		</div>		
		</form>
		
	</div>

	<jsp:include page="../layout/footer.jsp"></jsp:include>	
</body>
</html>