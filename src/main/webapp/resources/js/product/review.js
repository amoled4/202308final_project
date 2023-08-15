// review 조건 채워지면 버튼 색 바뀌기
function checkReview() {
    const stars = document.querySelector('input[name="pr_score"]:checked');
    const rvContent = document.getElementById('rvContent');
    const rvBtn = document.getElementById('rvBtn');
  
    if (stars != null && rvContent.value.length >= 5) {
      rvBtn.disabled = false;
    } else {
      rvBtn.disabled = true;
    }
  }
  
  // 별점과 리뷰 내용의 입력 상태 변화를 체크하는 이벤트 리스너 등록
  document.getElementById('rvBtn').disabled = true;
  document.querySelectorAll('input[name="pr_score"]').forEach(star => {
    star.addEventListener('click', checkReview);
  });
  rvContent.addEventListener('keyup', checkReview);
  
