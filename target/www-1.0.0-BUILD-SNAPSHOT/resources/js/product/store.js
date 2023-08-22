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