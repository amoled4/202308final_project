<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>양파마켓 | 회원수정</title>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="/resources/css/all.css">
	<link rel="stylesheet" type="text/css" href="/resources/css/member/modify.css">
	
	<!-- 닉네임 / 비밀번호 중복 확인을 위해 jquery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	
	<!-- 프로필 사진 미리보기 X심볼 -->
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,500,0,0" />
	
	<!-- 구글 폰트 -->
	<script src="https://www.google.com/recaptcha/api.js" async defer></script>
	
	<!-- 카카오 지도 -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=28524d982cffea013a5f7c234898c8df&libraries=services"></script>
</head>
<body>
	<div id="container">
		<form action="/member/modify" method="POST" enctype="multipart/form-data" id="innerContainer" onsubmit="return validateForm();">
			<a href="/" id="logoContainer">	
				<img alt="로고" src="/resources/image/logo.png" id="logoImage">
				<span id="logoText">양파마켓</span>
			</a>
		
			<h1>회원수정</h1>
			
			<div id="emailContainer">
				<h2>이메일</h2>
				<div id="emailInput">
					<input type="text" name="m_mail" class="border-gray input-box" value="${member.mvo.m_mail}" placeholder="${member.mvo.m_mail} " disabled="disabled">
				</div>
			</div>
			
			<div id="oldPasswordContainer">
				<h2>기존 비밀번호</h2>
				<div id="oldPasswordError" class="error-message"></div>
				<h6>영문, 숫자를 포함한 8자 이상의 비밀번호를 입력해주세요.</h6>
				<input type="password" id="userpw" name="m_pw" class="border-gray input-box" placeholder="비밀번호">
				<div class="passwordAlert"></div>
				<input type="hidden" id="oldUserpwValid" value="false">
			</div>			
			
			<div id="newPasswordContainer">
				<h2>변경 비밀번호</h2>
				<div id="passwordError" class="error-message"></div>
				<h6>영문, 숫자를 포함한 8자 이상의 비밀번호를 입력해주세요.</h6>
				<input type="password" id="changePw" name="m_chgPw" class="border-gray input-box" placeholder="비밀번호">
				<div class="newPasswordAlert"></div>
				<input type="hidden" id="userpwValid" value="false">
			</div>			
			
			<div id="newPasswordCheckContainer">
				<h2>변경 비밀번호 확인</h2>
				<div id="passwordError1" class="error-message"></div>
				<input type="password" id="changePwChk" name="m_chgPw_check" class="border-gray input-box" placeholder="비밀번호 확인">
				<div class="successPwChk"></div>
				<input type="hidden" id="userpwMatch" value="false">
			</div>
			
			<div id="nickNameContainer">
				<input type="hidden" id="memberNumber" value="${member.mvo.m_number}">
				
				<h2>닉네임</h2>
				<div id="nickError" class="error-message"></div>
				<div id="nickError1" class="error-message"></div>
				<h6>다른 유저와 겹치지 않도록 입력해주세요. (2~15자)</h6>
				<input type="text" id="nick" value="${member.mvo.m_nick_name}" name="m_nick_name" class="border-gray input-box" placeholder="닉네임"  maxlength="15">
				<div class="successNameChk"></div>
				<input type="hidden" id="nickValid" value="false">
    			<input type="hidden" id="nickValid1" value="false">
			</div>
			
			<div id="introduceContainer">
				<h2>자기 소개</h2>
				<textarea name="m_introduct" class="border-gray textarea-box" placeholder="자기 소개를 입력해주세요." maxlength="150">${member.mvo.m_introduct}</textarea>
			</div>
			
			<div id="mapContainer">
				<h2>주소 선택</h2>
				<div id="mapError" class="error-message"></div>
				<div style="display: flex;">
					<input type="text" id="mapAddress" value="${member.mvo.m_address}" class="border-gray input-box" placeholder="주소" disabled="disabled" name="m_address">
					<input type="hidden" id="m_address" name="m_address">
					<input type="button" onclick="execDaumPostcode()" id="mapBtn" class="background-purple" value="주소 검색"><br>
				</div>
				<div id="kakaoMap"></div>
			</div>
			
			<div id="imageContainer">
			    <h2>프로필 사진</h2>
			    <div style="display: flex;">
			        <input type="file" id="imagePath" name="file" style="display: none;" accept="image/*"/>
			        <input type="text" id="displayImagePath" value="${member.mivo.mi_name}" class="border-gray" readonly="readonly">
			        <input type="button" id="imageChoose" class="background-purple" value="파일 선택" onclick="document.getElementById('imagePath').click()">
			    </div>
			    <div id="imageWrapper" style="position: relative; display: none; margin-top: 10px;">
			        <img id="imagePreview" src="/resources/fileUpload/${member.mivo.mi_dir}/${member.mivo.mi_uuid}_th_${member.mivo.mi_name}" alt="selected image"/>
			        <div id="removeImage" style="  cursor: pointer; z-index: 100">
			        	<span class="material-symbols-outlined" style="">disabled_by_default</span>
				   	</div>
				</div>
			</div>
			
			<div id="termsContainer">
				<h2>약관동의</h2>
				<div id="termsInnerContainer" class="border-gray">
					<label for="terms1">
						<input type="checkbox" name="terms" id="terms1" checked="checked" disabled="disabled">
						만 14세 이상입니다 (필수)
					</label>
					<label for="terms2">
						<input type="checkbox" name="terms" id="terms2" checked="checked" disabled="disabled">
						이용약관 (필수)
					</label>
					<label for="terms3">
						<input type="checkbox" name="terms" id="terms3" checked="checked" disabled="disabled">
						개인정보수집 및 이용동의 (필수)
					</label>
					<label for="terms4">
						<input type="checkbox" name="terms" id="terms4">
						개인정보 마케팅 활용 동의 (선택)
					</label>
					<label for="terms5">
						<input type="checkbox" name="terms" id="terms5">
						이벤트, 쿠폰, 메일 및 SMS 등  수신 (선택)
					</label>
				</div>
			</div>
					
			<button type="submit" id="submitBtn" class="background-purple">회원수정하기</button>
			
			<button type="button" onclick="removeMember()" id="removeBtn" class="background-gray">회원탈퇴하기</button>
			
			<p align="center">이미 아이디가 있으신가요? <a href="/member/signin">로그인</a>
		</form>
	</div>
	
	<script type="text/javascript" src="/resources/js/member/modify.js"></script>
	
	
</body>
</html>