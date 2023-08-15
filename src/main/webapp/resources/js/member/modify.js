
// 닉네임 중복 확인

window.addEventListener('load', function() {
    var nickInput = document.getElementById("nick");
    var originalNick = document.getElementById("nick").value;

	
    // 페이지 로딩 시 nickValid 값과 nickValid1 값을 true로 설정
    document.getElementById('nickValid').value = "true";
    document.getElementById('nickValid1').value = "true";
	
    nickInput.addEventListener('input', function() {
        var nickValue = nickInput.value;
	
        // 닉네임 길이 체크 (2~15자)
        if (nickValue.length < 2 || nickValue.length > 15) {
            document.getElementById('nickValid').value = "false";
        } else {
            document.getElementById('nickValid').value = "true";
            document.getElementById("nickError").textContent = "";
        }
	
        // 사용자가 닉네임을 변경한 경우
        if (nickValue !== originalNick) {
            // nickValid1 값을 false로 설정
            document.getElementById('nickValid1').value = "false";
        }
    });

    nickInput.addEventListener('blur', function() {
        var nickValue = nickInput.value;

        if (nickValue !== originalNick) {
            // 닉네임 중복 검사를 수행
            $.ajax({
                type: "post",
                async: true,
                url: "/member/nicknameCheck",
                data: {
                    m_nick_name: nickValue,
                    m_number: $("#memberNumber").val()
                },
                success: function(data) {
                	var xmlString = new XMLSerializer().serializeToString(data);
                	
                	// XML 응답 파싱
		            var parser = new DOMParser();
		            var xmlDoc = parser.parseFromString(xmlString, "text/xml");
		            
		            // 'Integer' 태그의 값 얻기
            		var intValue = parseInt(xmlDoc.getElementsByTagName("Integer")[0].childNodes[0].nodeValue);
                    
                    if(intValue === 0) {
                        // 닉네임이 중복되지 않는 경우
                        document.getElementById('nickValid1').value = "true";
                        document.getElementById("nickError1").textContent = "";
                        $(".successNameChk").text("사용가능한 닉네임입니다.");
						$(".successNameChk").css("color", "green");
                    } else {
                        // 닉네임이 중복되는 경우
                        document.getElementById('nickValid1').value = "false";
                        $(".successNameChk").text("사용중인 닉네임입니다");
						$(".successNameChk").css("color", "red");
                    }
                },
                error: function(e) {
                    alert("닉네임 중복 검사 중 오류 발생");
                }
            });
        } else {
            // 사용자가 닉네임을 원래 값으로 다시 변경한 경우
            // nickValid1 값을 true로 설정
            document.getElementById('nickValid1').value = "true";
            document.getElementById("nickError1").textContent = "";
        }
    });
});

// 비밀번호 변경

//비밀번호 조건 (영어 + 숫자 조합)
document.addEventListener('DOMContentLoaded', function() {
     var passwordReg = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
 
     // 변경 비밀번호 확인
     document.getElementById('changePw').addEventListener('blur', function() {
         var newPassword = document.getElementById('changePw').value;
         
     
         if (!passwordReg.test(newPassword) && newPassword.length > 0) { // newPassword의 길이가 0보다 클 경우에만 코드 실행
             document.querySelector('.newPasswordAlert').innerText = "영문, 숫자를 모두 포함한 8자 이상이어야 합니다.";
             document.querySelector('.newPasswordAlert').style.color = "red";
             document.getElementById('userpwValid').value = "false";
         } else {
             document.querySelector('.newPasswordAlert').innerText = "";
             document.getElementById('userpwValid').value = "true";
         }
     });
 
     // 변경 비밀번호와 변경 비밀번호 확인 일치
     document.getElementById('changePwChk').addEventListener('blur', function() {
         var newPassword = document.getElementById('changePw').value;
         var passwordCheck = document.getElementById('changePwChk').value;
     
         if (passwordCheck.length > 0) { // passwordCheck의 길이가 0보다 클 경우에만 코드 실행
             if (passwordCheck === newPassword) {
                 document.querySelector('.successPwChk').innerText = "비밀번호가 일치합니다.";
                 document.querySelector('.successPwChk').style.color = "green";
                 document.getElementById('userpwMatch').value = "true";
             } else {
                 document.querySelector('.successPwChk').innerText = "비밀번호가 일치하지 않습니다.";
                 document.querySelector('.successPwChk').style.color = "red";
                 document.getElementById('userpwMatch').value = "false";
             }
         }
     });
 });
 


// 기존 데이터들 불러오기(프로필 사진 및 주소)

//프로필 사진 업로드
const regExp = new RegExp("\.(exe|sh|bat|msi|dll|js)$");
const regExpImg = new RegExp("\.(jpg|jpeg|png|gif|bmp)$");
const maxSize = 1024*1024*20;

// 파일 유효성 검사
function fileSizeValidation(fileName, fileSize){
     if(regExp.test(fileName)){
         return 0;
     }else if(fileSize > maxSize){
         return 0;
     }else if(!regExpImg.test(fileName)){ //이미지 파일이 아니면 첨부 X
         return 0;
     }else {
         return 1;
     }
 }

 document.addEventListener('DOMContentLoaded', function() {
     var imagePathElement = document.getElementById('imagePath');
     var imageWrapper = document.getElementById('imageWrapper');
     var previewImage = document.getElementById('imagePreview');
     var mapAddress = document.getElementById('mapAddress').value;

    //기존 이미지가 있으면 display : block / 없으면 (이미지 로딩에 오류가 있으면) none
	var tempImage = new Image();

	tempImage.onload = function() {
	    imageWrapper.style.display = 'block';
	}
	
	tempImage.onerror = function() {
	    imageWrapper.style.display = 'none';
	}
	
	tempImage.src = previewImage.src;

	
    
    // 기존 주소가 있는 경우 주소맵 표시
    if (mapAddress !== "") {
        geocoder.addressSearch(mapAddress, function(results, status) {
            if (status === daum.maps.services.Status.OK) {
                var result = results[0];
                var coords = new daum.maps.LatLng(result.y, result.x);
                mapContainer.style.display = "block";
                map.relayout();
                map.setCenter(coords);
                marker.setPosition(coords)
                
                // 여기서 m_address 필드에 주소 설정
                document.getElementById("m_address").value = mapAddress;
            }
        });
    }

     var handleImageChange = function(e){
          document.getElementById('submitBtn').disabled = false;
          const fileObject = e.target.files;
  
          let isOk = 1;
          let file = fileObject[0];
          let vaildResult = fileSizeValidation(file.name, file.size);
          isOk *= vaildResult;
  
          if(isOk != 0) { 
              document.getElementById('displayImagePath').value = file.name;
          }
  
          if(isOk == 0){ 
              document.getElementById('submitBtn').disabled = true;
          }
  
          if (file && file.type.startsWith("image")) {
              var reader = new FileReader();
  
              reader.onload = function(e) {
                  document.getElementById('imagePreview').src = e.target.result;
  
                  var imageWrapper = document.getElementById('imageWrapper');
                  imageWrapper.style.display = 'block';
              }
  
              reader.readAsDataURL(file);
          }
      }
  
      imagePathElement.removeEventListener('change', handleImageChange);
      imagePathElement.addEventListener('change', handleImageChange);
  
      document.getElementById('removeImage').addEventListener('click', function() {
          var previewImage = document.getElementById('imagePreview');
          previewImage.src = '';
  
          var imagePath = document.getElementById('imagePath');
          imagePath.value = '';
  
          var displayImagePath = document.getElementById('displayImagePath');
          displayImagePath.value = '';
  
          var imageWrapper = document.getElementById('imageWrapper');
          imageWrapper.style.display = 'none';
      });
  });

//주소
var mapContainer = document.getElementById('kakaoMap'); // 지도를 표시할 div
    
mapOption = {
    center: new daum.maps.LatLng(37.45034013579847 , 126.70286743807637), // 지도의 중심좌표
    draggable: false,
    level: 3 // 지도의 확대 레벨
};

//지도를 미리 생성
var map = new daum.maps.Map(mapContainer, mapOption);
//주소-좌표 변환 객체를 생성
var geocoder = new daum.maps.services.Geocoder();
//마커를 미리 생성
var marker = new daum.maps.Marker({
    position: new daum.maps.LatLng(37.45034013579847 , 126.70286743807637),
    map: map
});

function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            const incheon = {};

            var addr = data.address; // 최종 주소 변수
            
            var si = addr.split(" ")[0];
            var gungu = addr.split(" ")[1];

            console.log("si : " + si);
            console.log("gungu : " + gungu);
            
            if(addr.substring(0, addr.indexOf(" ")) != "인천") {
                alert("인천 외 지역 미지원");
                document.getElementById("mapAddress").value = "";
                mapContainer.style.display = "none";
            } else {
                // 주소 정보를 해당 필드에 넣는다.
                document.getElementById("mapAddress").value = addr;
                // 'm_address'에 주소 설정
            	document.getElementById("m_address").value = addr;
                // 주소로 상세 정보를 검색
                geocoder.addressSearch(data.address, function(results, status) {
                    // 정상적으로 검색이 완료됐으면
                    if (status === daum.maps.services.Status.OK) {
                        var result = results[0]; //첫번째 결과의 값을 활용

                        // 해당 주소에 대한 좌표를 받아서
                        var coords = new daum.maps.LatLng(result.y, result.x);
                        // 지도를 보여준다.
                        mapContainer.style.display = "block";
                        map.relayout();
                        // 지도 중심을 변경한다.
                        map.setCenter(coords);
                        // 마커를 결과값으로 받은 위치로 옮긴다.
                        marker.setPosition(coords)
                    }
                });
            }
        }
    }).open();
}


// 기존 약관 불러오기 (추가할 것)


//회원탈퇴

function removeMember() {
    if(confirm("회원 탈퇴를 진행하시겠습니까?")) {
        $.ajax({
            type: "POST",
            url: "/member/remove",
            success: function(response) {
                alert('탈퇴가 정상적으로 완료되었습니다.');
                window.location.href = '/member/signin';  // 로그인 페이지로 이동
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert('회원 탈퇴에 실패하였습니다. 다시 시도해주세요.');
                console.log(jqXHR, textStatus, errorThrown);
            }
        });
    }
}

// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

// 회원정보수정 유효성 검사



function validateForm() {
    console.log('validateForm 기능 called');

    // 이전 오류(error-message) 지우기 (수정된 사항 지우기)
    var errorMessages = document.querySelectorAll('.error-message');
    for (var i = 0; i < errorMessages.length; i++) {
        errorMessages[i].textContent = '';
        errorMessages[i].style.color = '';
        errorMessages[i].style.fontSize = '';
    }

    var firstErrorField = null;
    var firstErrorFieldMessage = null;

    if (document.getElementById("oldUserpwValid").value !== "true") {
        document.getElementById("oldPasswordError").textContent = "* 기존 비밀번호가 일치하지 않습니다.";
        document.getElementById("oldPasswordError").style.color = 'red';
        document.getElementById("oldPasswordError").style.fontSize = '14px';
        firstErrorField = document.getElementById("userpw");
    }

    var changePwValue = document.getElementById("changePw").value;
    if (changePwValue && document.getElementById("userpwValid").value !== "true") {
        document.getElementById("passwordError").textContent = "* 비밀번호는 영문, 숫자를 모두 포함한 8자 이상이어야 합니다";
        document.getElementById("passwordError").style.color = 'red';
        document.getElementById("passwordError").style.fontSize = '14px';
        firstErrorField = document.getElementById("changePw");
    }

    var changePwChkValue = document.getElementById("changePwChk").value;
    if (changePwChkValue && document.getElementById("userpwMatch").value !== "true") {
        document.getElementById("passwordError1").textContent = "* 비밀번호가 일치하지 않습니다";
        document.getElementById("passwordError1").style.color = 'red';
        document.getElementById("passwordError1").style.fontSize = '14px';
        firstErrorField = document.getElementById("changePwChk");
    }


    if (document.getElementById("nickValid").value !== "true") {
        if (!firstErrorField) {
        	document.getElementById("nickError").textContent = "* 닉네임 길이를 확인해주세요";
            document.getElementById("nickError").style.color = 'red';
            document.getElementById("nickError").style.fontSize = '14px';
            firstErrorField = document.getElementById("nick");
        }
    }

    if (document.getElementById("nickValid1").value !== "true") {
        if (!firstErrorField) {
        	document.getElementById("nickError1").textContent = "* 닉네임이 이미 사용중입니다";
            document.getElementById("nickError1").style.color = 'red';
            document.getElementById("nickError1").style.fontSize = '14px';
            firstErrorField = document.getElementById("nick");
        }
    }

    if (!document.getElementById("m_address").value) {
        document.getElementById("mapError").textContent = "* 주소를 입력해주세요";
        document.getElementById("mapError").style.color = 'red';
        document.getElementById("mapError").style.fontSize = '14px';
        firstErrorField = document.getElementById("mapAddress");
    }

    if (firstErrorField) {
        firstErrorField.focus();
        return false;
    }

    console.log("유효성 검사 통과");
    return true;
}

//기존 비밀번호 확인
window.addEventListener('load', function() {
    document.getElementById('innerContainer').addEventListener('submit', function(event) {
        console.log('Submit event 트리거');

        
        event.preventDefault();

        var oldPassword = document.getElementById('userpw').value;
        

        $.ajax({
            type: "POST",
            url: "/member/checkPassword",
            data: {
                m_pw: oldPassword
            },
            dataType: "json",
            async: false,  // Make request synchronous
            success: function(response) {
                if (response === true) {
                    document.getElementById('oldUserpwValid').value = "true";
                } else {
                    document.getElementById('oldUserpwValid').value = "false";
                }

                
                if (validateForm()) {
                    
                    event.target.submit();
                }
            }
        });
    });
});


