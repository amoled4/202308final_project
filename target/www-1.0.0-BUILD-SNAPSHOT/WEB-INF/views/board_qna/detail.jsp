<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("replaceChar", "\n"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>양파마켓 | 1:1문의하기</title>
<link rel="icon" type="image/png" href="/resources/image/favicon.png">
<link rel="stylesheet" type="text/css" href="/resources/css/all.css">
<link rel="stylesheet" type="text/css" href="/resources/css/board_qna/detail.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<style>

</style>	
</head>
<body>
	<jsp:include page="../layout/header.jsp"></jsp:include>
		
	
	<div id="container">
		
		<div id="inner-box">
		<span class="attachment-text">양파마켓 고객센터</span> 
		<span class="attachment-text">>문의내역</span><br>
		</div>
	
		<div id="inner-box">
			<h3>${bqvo.bq_title }</h3>
		</div>
		
		
		<div id="inner-box">
		<hr>
		</div>
		
		<div id="inner-box">
			<div id="writer-box">
				<div id="writer-profile">
					<img src="/resources/image/basicprofile.jpeg" alt="Image">
				</div>
				<div id="writer-info">
					<div id="nick-info">
						<span class="nickname">${bqvo.bq_writer }</span>
					</div>
					<div id="reg-info">
						<span class="reg_time">${bqvo.bq_reg_date }</span>
					</div>
				</div>
			</div>
			<div id="content-box">
				<span>${fn:replace(bqvo.bq_content, replaceChar, "<br/>") }</span>
			</div>
		</div>
		
		<div id="inner-box">
		<hr>
		</div>
		
	<!-- 	<form>
		<div id="inner-box">
			<div id="comment_box" >
				<div id="comment-info">
					<div id="writer-profile">
						<img src="/resources/image/basicprofile.jpeg" alt="Image">
					</div>
					<div id="writer-comment">
		 				<textarea class="border-gray" id="loc-content" name="loc-content" 
		 				rows="7" maxlength="200" placeholder="문의 내용에 추가"></textarea>
					</div>
				</div>
				<div id="comment-btn">
					<a><button class="background-purple submit-button">제출</button></a>
				</div>
			</div>
		</div>
		</form> -->
		
		<!-- 관리자 로그인 시 보이는 답변 화면 -->
		<c:if test="${ses.m_isAdmin eq 1 }">
			<div id="inner-box">
				<div id="comment_box" >
					<div id="comment-info">
						<div id="writer-profile">
							<img src="/resources/image/basicprofile.jpeg" alt="Image">
							<input type="hidden" id="cmtWriter" name="bqc_writer" value="${ses.m_nick_name }">
						</div>
						<div id="writer-comment">
			 				<textarea class="border-gray" id="loc-content" name="loc-content" 
			 				rows="7" placeholder="문의 내용에 추가"></textarea>
						</div>
					</div>
					<div id="comment-btn">
						<button class="background-purple submit-button" id="postCmt">답변하기</button>
					</div>
				</div>
			</div>
		</c:if>
		
		
		<!-- 답변완료 후 보이는 화면 -->
		<div id="cmtListArea">
		<div id="inner-box">
			<div id="writer-box">
				<div id="writer-profile">
					<img src="/resources/image/basicprofile.jpeg" alt="Image">
				</div>
				<div id="writer-info">
					<div id="nick-info">
						<span class="nickname">Admin</span>
					</div>
					<div id="reg-info">
						<span class="reg_time">15:26</span>
					</div>
				</div>
			</div>
			<div id="content-box">
			</div>
		</div>
		</div>
		
		
	</div>
<!-- <span>안녕하세요, 양파마켓입니다.<br>
				양파마켓은 지역을 기반으로 상호 간에 거래할 수 있는 플랫폼입니다.<br>
				<br>
				① 이메일/ SNS 계정 연동을 통한 회원 가입 및 로그인<br>
				② 원하는 카테고리 선택하여 전문가 탐색 혹은 사이트 상단에 '검색'을 통한 판매 서비스 검색<br>
				③ 전문가 판매 서비스 선택하여 '문의 남기기' 혹은 '연락처 보기'를 통해 문의<br>
				④ 전문가와 서비스에 대한 사전 견적 협의 및 결제<br>
				<br>
				추가로, 서비스 이용에 대한 자세한 내용은 FAQ를 통해 확인하실 수 있습니다.<br>
				이용하시는데 참고 부탁드리며, 문의하신 내용에 도움이 되었으면 합니다.<br>
				<br>
				다른 문의사항이 있으실 경우 언제든지 크몽 고객센터로 연락 부탁드립니다.<br>
				<br>
				감사합니다.
				</span>
 -->

	<jsp:include page="../layout/footer.jsp"></jsp:include>
	<script type="text/javascript" src="/resources/js/board_qna_comment/qnaComment.js"></script>
	<script type="text/javascript">
		const bq_number = `<c:out value="${bqvo.bq_number}"/>`;
	</script>
	<script type="text/javascript">
		getCommentList(bq_number);
	</script>
</body>
</html>