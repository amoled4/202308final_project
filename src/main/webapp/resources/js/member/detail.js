
//각 버튼 별 내 상품 리스트, 리뷰, 찜 상품 출력
//function showProductList() {
//    document.querySelector('.productList').style.display = 'block';
//    document.querySelector('.reviewList').style.display = 'none';
//   document.querySelector('.likedProductList').style.display = 'none';
//}
//
//function showReviewList() {
//    document.querySelector('.productList').style.display = 'none';
//    document.querySelector('.reviewList').style.display = 'block';
//    document.querySelector('.likedProductList').style.display = 'none';
//}
//
//function showLikedProductList() {
//    document.querySelector('.productList').style.display = 'none';
//    document.querySelector('.reviewList').style.display = 'none';
//    document.querySelector('.likedProductList').style.display = 'block';
//}

// 상품, 상품 후기 버튼 누르면 내용 바뀜
let tabMenu = document.querySelectorAll('.btn_ac');
let tabInner = document.querySelectorAll('.swiper-container');

tabMenu.forEach((tab, idx) => {
    tab.addEventListener('click', function () {
        // 탭 클릭시 내용 바뀜
        tabInner.forEach((inner) => {
            inner.setAttribute('style', 'display:none');
        });

        // 탭 클릭시 내용 바뀜
        tabInner[idx].setAttribute('style', 'display:block');
    });
})




// 주소 인천 ㅇㅇ군/구까지 잘라내서 나타내기

let location_spans = document.getElementsByClassName('location-sub');

for (let i = 0; i < location_spans.length; i++) {
    let location_span = location_spans[i];
    let location_text = location_span.innerHTML;

    let endIndex;
    if (location_text.includes("구")) {
        endIndex = location_text.indexOf("구") + 1;
    } else if (location_text.includes("군")) {
        endIndex = location_text.indexOf("군") + 1;
    }

    let location_sub = location_text.substring(0, endIndex);
    location_span.innerHTML = location_sub;
}



