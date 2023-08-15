<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Modal</title>
	<style type="text/css">
	.modal {
		display: none;
		position: fixed;
		z-index: 999;
		left: 0;
		top: 0;
		width: 100%;
		height: 100%;
		background-color: rgba(0, 0, 0, 0.4);
	}
	
	.modal-content {
		background-color: #fff;
		margin: 15% auto;
		padding: 15px;
		border-radius: 5px;
		max-width: 430px;
	}
	
	.modal-header {
		display: flex;
		justify-content: flex-end;
	}
	
	.close-modal {
		cursor: pointer;
		font-size: 24px;
	}
	
	.modal-body {
		display: flex;
		flex-wrap: wrap;
		justify-content: center;
	}
	
	.option {
		display: flex;
		flex-direction: column;
		align-items: center;
		margin: 15px;
	}
	
	.larger-icon {
		font-size: 36px;
		line-height: 1;
		margin-bottom: 8px;
		color: rgb(201, 127, 232);
		cursor: pointer;
	}
	</style>
</head>
<body>
	<div class="modal">
		<div class="modal-content">
			<div class="modal-header">
				<span class="close-modal material-symbols-outlined">close</span>
			</div>
			<div class="modal-body">
				<div class="option" data-action="showFileUploadWindow()">
					<span class="material-symbols-outlined larger-icon">photo</span> <span
						class="option-label">사진선택</span>
				</div>
				<div class="option" data-action="showMap()">
					<span class="material-symbols-outlined larger-icon">location_on</span>
					<span class="option-label">장소선택</span>
				</div>
				<div class="option" data-action="showRemittanceWindow()">
					<span class="material-symbols-outlined larger-icon">calendar_today</span>
					<span class="option-label">거래약속</span>
				</div>
				<div class="option">
					<span class="material-symbols-outlined larger-icon">payment</span>
					<span class="option-label">양파페이</span>
				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
// 모달 생성 및 닫기
const modal = document.querySelector('.modal');
const closeModal = document.querySelector('.close-modal');

// 옵션
const options = document.querySelectorAll('.option');

// 파일 업로드 (열기만)
function showFileUploadWindow() {
  const fileInput = document.createElement('input');
  fileInput.type = 'file';
  fileInput.accept = 'image/*';
  fileInput.click();

  fileInput.addEventListener('change', function(event) {
    const selectedFile = event.target.files[0];
    console.log(">>> file : " + selectedFile);
    console.log(">>> file.type : " + selectedFile.type);
    
    if (selectedFile.type.startsWith("image/")) {
    	
    } else {
    	alert("이미지 파일만 선택 가능합니다.");
    }
  });
}

// 지도
function showMap() {
  
  console.log('지도 보기');
}

// 달력
function showRemittanceWindow() {

  console.log('달력 보기');
}

// 모달 여는 버튼 설정
const openModalBtn = document.getElementById('modalBtn');
openModalBtn.addEventListener('click', function() {
  modal.style.display = 'block';
});

// 모달 닫는 버튼 설정
closeModal.addEventListener('click', function() {
  modal.style.display = 'none';
});

window.addEventListener('click', function(event) {
  if (event.target === modal) {
    modal.style.display = 'block';
  }
});

options.forEach(option => {
  const action = option.getAttribute('data-action');
  if (action) {
    option.addEventListener('click', function() {
      eval(action);
      modal.style.display = 'none'; 
    });
  }
});
</script>

</html>