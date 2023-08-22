<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/css/all.css">
<link rel="stylesheet" type="text/css" href="/resources/css/board_location/detail.css">
<link rel="icon" type="image/png" href="/resources/image/favicon.png">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<title>양파마켓 | 지역게시판</title>
<style>

</style>	
</head>
<body>
<jsp:include page="../layout/header.jsp"></jsp:include>		
	
	<div id="container">	
	
		<div id="inner-box">
			<span class="attachment-tag">${bldto.blvo.bl_subject }</span> 
		</div>
		
		<div id="inner-box">
			<div id="writer-box">
				<div id="writer-profile">
					<!-- 회원이 프로필 사진을 등록 안했을 경우 -->
					<c:if test="${empty bldto.mdto.mivo.mi_name}">
						<img alt="프로필 사진" src="/resources/image/profile.png" style="width:40px;height:40px;">
					</c:if>
					<!-- 회원이 프로필 사진을 등록 했을 경우 -->
					<c:if test="${not empty bldto.mdto.mivo.mi_name}">
						<img alt="프로필 사진" src="/resources/fileUpload/${bldto.mdto.mivo.mi_dir}/${bldto.mdto.mivo.mi_uuid}_th_${bldto.mdto.mivo.mi_name}" style="width:40px;height:40px;">
					</c:if>
				</div>
				<div id="writer-info">
					<div id="nick-info">
						<span class="nickname">${bldto.blvo.bl_writer }</span>
					</div>
					<div id="reg-info">
						<span class="reg_location">${bldto.blvo.bl_location }</span>
						<span class="reg_dot">·</span>
						<span class="reg_time">${bldto.blvo.bl_reg_date }</span>
					</div>
				</div>
			</div>
		</div>
		
		<div id="inner-box">
			<span class="loc-title">${bldto.blvo.bl_title }</span> 
		</div>
		
		<div id="inner-box">
			<span class="loc-content">
				${bldto.blvo.bl_content }
			</span> 
		</div>
				
		<!-- <hr>		
		<div id="inner-box">
			<div id="icon-box">
				<a href="#">
					<span class="material-symbols-outlined">
					favorite
					</span>
				</a>
				<span class="material-text">공감하기</span>
				<span class="material-text">1</span>
				<a href="#">
					<span class="material-symbols-outlined">
					sms
					</span>
				</a>
				<span class="material-text">댓글</span>
				<span class="material-text">1</span>
			</div>
		</div>	 -->		
		<hr class="last_hr">
		
		<div id="cmtListArea">
		<!-- <div id="inner-box">
		<div id="writer-container">
			<div id="writer-box2">
				<div id="writer-profile">
					<img src="/resources/image/cat2.png" alt="Image">
				</div>
				<div id="writer-info">
					<div id="nick-info">
						<span class="nickname">하하하하하하하하하하</span>
					</div>
					<div id="reg-info">
						<span class="reg_location">계양구</span>
						<span class="reg_dot">·</span>
						<span class="reg_time">14:23</span>
					</div>
				</div>
			</div>
			<div id="comment">SKY 동물병원 규모가 좀 커요!</div>
		</div>
		</div> -->
		</div>
	
		<div id="inner-box">
			<div id="comment-box" class="border-gray" >
				<div id="comment-info">
						<input type="hidden" id="cmtWriter" name="blc_writer" value="${ses.m_nick_name }">
					<div id="writer-comment">
		 				<textarea class="border-gray" id="loc-content" name="loc-content" 
		 				rows="4" maxlength="200" placeholder="내용을 입력해주세요"></textarea>
					</div>
				</div>
				<div id="comment-btn">
					<button class="background-purple submit-button" id="postCmt">댓글쓰기</button>
				</div>
			</div>
		</div>
		
		<div id="inner-box">
		  <div class="button-container">
		  <c:if test="${ses.m_number eq bldto.blvo.bl_m_number }">
			    <a href="/board_location/modify?bl_number=${bldto.blvo.bl_number }"><button type="button" class="background-gray submit-button">수정</button></a>
			    <a href="/board_location/remove?bl_number=${bldto.blvo.bl_number }"><button type="button" class="background-gray cancel-button submit-button">삭제</button></a>
		    </c:if>
		  </div>
		</div>
		
		
	</div>
	
	<jsp:include page="../layout/footer.jsp"></jsp:include>
	<script type="text/javascript" src="/resources/js/board_location_comment/locationComment.js"></script>
	<script type="text/javascript">
		const bl_number = `<c:out value="${bldto.blvo.bl_number}"/>`;
	</script>
	<script type="text/javascript">
		getCommentList(bl_number);
	</script>
</body>
</html>