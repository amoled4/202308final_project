<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>양파마켓 | 로그인</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="/resources/css/all.css">
	<link rel="stylesheet" type="text/css" href="/resources/css/member/signin.css">
</head>
<body>

	
	<div id="container">
		<div id="innerContainer">
			<a href="/" id="logoContainer">
				<img alt="로고" src="/resources/image/logo.png" id="logoImage">
				<span id="logoText">양파마켓</span>
			</a>
			
			<form action="/member/signin" method="post">
				<div>
					<input type="text" id="idInput" name="m_mail" class="border-gray" placeholder="아이디@example.com">
					<input type="password" id="pwInput" name="m_pw" class="border-gray" placeholder="비밀번호">
				</div>
				
				<button type="submit" id="subBtn" class="background-purple">로그인</button>
			</form>
			
			<a href="/member/signup" id="signupBtn">회원가입</a>
			
			<p id="signSnsText">SNS 계정으로 간편 로그인/회원가입</p>
			
			<div id="signSnsBtn">
			  <a href="javascript:kakaoLogin();">
			    <div class="snsCircle snsCircle-yellow">
			      <img src="/resources/image/kakao-logo.png" alt="소셜 로고">
			    </div>
			  </a>
			  <a id="naverIdLogin_loginButton" href="javascript:void(0)" >
			    <div class="snsCircle snsCircle-green">
			      <img src="/resources/image/naver-logo.png" alt="소셜 로고">
			    </div>
			  </a>
			  <a href="javascript:;" class="facebook">
			    <div class="snsCircle snsCircle-blue">
			      <img src="/resources/image/facebook-logo.png" alt="소셜 로고">
			    </div>
			  </a>
			</div>
		</div>
	</div>
	
	<!-- 카카오 로그인 -->
	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
	<script>
	//28524d982cffea013a5f7c234898c8df
	window.Kakao.init("28524d982cffea013a5f7c234898c8df");
		
	function kakaoLogin(){
		window.Kakao.Auth.login({
			scope:'profile_nickname, account_email, gender',
			success: function(authObj){
				console.log(authObj);
				window.Kakao.API.request({
					url:'/v2/user/me',
					success: res => {
						const kakao_account = res.kakao_account;
						console.log(kakao_account);
					}
				});
			}
		});
	}
	</script>
		
	<!-- 네이버 로그인 누르면 오류난다고 뜸 ! 조장님 해결부탁... -->
	<!-- https://tyrannocoding.tistory.com/60 -->
	<script src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js" charset="utf-8"></script>
	<script>	
	var naverLogin = new naver.LoginWithNaverId(
			{
				clientId: "ksXXxhvQsGUNnMbZTnlR", //내 애플리케이션 정보에 cliendId를 입력해줍니다.
				callbackUrl: "http://localhost:8088", // 내 애플리케이션 API설정의 Callback URL 을 입력해줍니다.
				isPopup: false,
				callbackHandle: true
			}
		);	
	
	naverLogin.init();
	
	window.addEventListener('load', function () {
		naverLogin.getLoginStatus(function (status) {
			if (status) {
				var email = naverLogin.user.getEmail(); // 필수로 설정할것을 받아와 아래처럼 조건문을 줍니다.    		
				console.log(naverLogin.user);    		
	            if( email == undefined || email == null) {
					alert("이메일은 필수정보입니다. 정보제공을 동의해주세요.");
					naverLogin.reprompt();
					return;
				}	            
			} else {
				console.log("callback 처리에 실패하였습니다.");
			}
		});
	});
	
	
	var testPopUp;
	function openPopUp() {
	    testPopUp= window.open("https://nid.naver.com/nidlogin.logout", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,width=1,height=1");
	}
	function closePopUp(){
	    testPopUp.close();
	}
	
	function naverLogout() {
		openPopUp();
		setTimeout(function() {
			closePopUp();
			}, 1000);	
	}
	
	//아이디, 비밀번호 오류메세지 출력
	<c:if test="${not empty errorMessage}">
   		alert('${errorMessage}');
	</c:if>
	</script>
	
	
	<!-- 페이스북 로그인... 이것도 something went wrong 뜸..ㅎ -->
	<script>
	$(document).ready(function() {
		$('.facebook').click(function() {
			let facebook_client_id = "1442104653028465";
			let facebook_redirect_uri = "http://localhost:8088/";
			let facebook_state = Math.floor(Math.random() * 10000);
			location.href = 'https://www.facebook.com/v15.0/dialog/oauth?client_id=' + facebook_client_id + '&scope=public_profile,email&redirect_uri=' + facebook_redirect_uri + '&state=' + facebook_state;
		});
	});
	</script>
	
	<script>
	window.fbAsyncInit = function() {
		window.fbAsyncInit = function() {
			FB.init({
				appId      : '1442104653028465',
				cookie     : true,
				xfbml      : true,
				version    : 'v17.0'
			});
			
			FB.AppEvents.logPageView();   
		};

		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id)) return;
			js = d.createElement(s); js.id = id;
			js.src = "https://connect.facebook.net/en_US/sdk.js";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));

		FB.getLoginStatus(function(response) {
			statusChangeCallback(response);
		});

		{
			status: 'connected',
			authResponse: {
				accessToken: '...',
				expiresIn: '...',
				signedRequest: '...',
				userID: '...'
			}
		}

		<fb:login-button 
			scope="public_profile,email"
			onlogin="checkLoginState();">
		</fb:login-button>

		function checkLoginState() {
			FB.getLoginStatus(function(response) {
				statusChangeCallback(response);
			});
		}
		</script>
	

</body>
</html>
