<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("replaceChar", "\n"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>양파마켓 | 공지사항</title>
<link rel="stylesheet" type="text/css" href="/resources/css/all.css">
<link rel="stylesheet" type="text/css" href="/resources/css/board_notice/detail.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<style>

</style>	
</head>
<body>
	<jsp:include page="../layout/header.jsp"></jsp:include>
		
	
	<div id="container">
		
		<div id="inner-box">
		<span class="attachment-text">양파마켓 고객센터</span> 
		<span class="attachment-text">>공지사항</span><br>
		</div>
	
		<div id="inner-box">
			<h3>${bnvo.bn_title }</h3>			
			<div id="content-box">
				${fn:replace(bnvo.bn_content, replaceChar, "<br/>") }
			</div>
		</div>
		
		<div id="inner-box">
			<div id="comment_box" >
				<div id="comment-btn">
					<span>다른 질문이 있으신가요?</span><br>
					<a href="/board_qna/register"><button type="button" class="background-purple submit-button">문의하기</button></a>
				</div>
			</div>
		</div>
		
		<div id="inner-box">
		<hr>
		</div>
		<c:if test="${ses.m_isAdmin eq 1 }">
			<div id="inner-box">
			  <div class="button-container">
			    <a href="/board_notice/modify?bn_number=${bnvo.bn_number }"><button type="button" class="background-gray submit-button">수정</button></a>
			    <a href="/board_notice/remove?bn_number=${bnvo.bn_number }"><button type="button" class="background-gray cancel-button submit-button">삭제</button></a>
			  </div>
			</div>
		</c:if>
	</div>


	<jsp:include page="../layout/footer.jsp"></jsp:include>	
</body>
</html>