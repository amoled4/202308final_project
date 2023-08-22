// 주소 ㅇㅇ군/구만 잘라내서 DB 보내기

let location_spans = document.getElementsByClassName('location-sub');
for (let i = 0; i < location_spans.length; i++) {
    let location_span = location_spans[i];
    let location_text = location_span.value;
    console.log(location_text);
    let endIndex;
    if (location_text.includes("구")) {
        endIndex = location_text.indexOf("구") + 1;
    } else if (location_text.includes("군")) {
        endIndex = location_text.indexOf("군") + 1;
    }

    let location_sub = location_text.substring(3, endIndex);
    location_span.value = location_sub;
    console.log(location_sub);
    console.log(location_span.value);
}

// file upload 시 썸네일 생성 및 엑스 버튼 누르면 썸네일과 input에 입력된 파일도 같이 삭제
function setDetailImage(event) {
    const fileInput = document.getElementById('file');
    const fileDiv = document.querySelector('.file-box');

    for (let image of event.target.files) {
        let reader = new FileReader();

        let div = document.createElement("div");
        let img = document.createElement("img");
        let button = document.createElement("button");
        img.style.width = "180px";
        img.style.height = "180px";
        button.type = "button";
        button.className = "file-del";

        div.className = "file-box";
        div.appendChild(img);
        div.appendChild(button);

        reader.onload = function (event) {
            img.src = event.target.result;
        };
        reader.readAsDataURL(image);

        button.addEventListener('click', function () {
            // 파일 목록에서 삭제
            const filesArray = Array.from(fileInput.files);
            const updatedFiles = filesArray.filter(file => file !== image);

            const dataTransfer = new DataTransfer();
            updatedFiles.forEach(file => dataTransfer.items.add(file));
            fileInput.files = dataTransfer.files;

            // 썸네일 삭제
            div.remove();
        });

        fileInput.addEventListener('click', () => {
            div.remove();
        })

        fileDiv.appendChild(div);
    }
}

// DB에서 이미지 삭제
async function removeFileToserver(uuid) {
    try {
        const url = '/board_location/file/' + uuid;
        const config = {
            method: 'delete'
        };
        const response = await fetch(url, config);
        const result = await response.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

// 기존 등록된 이미지를 삭제하는 함수
function deleteFile(button) {
    const div = button.parentNode;
    let uuid = button.dataset.uuid;
    console.log(uuid);

    // DB에서 데이터 삭제
    removeFileToserver(uuid).then(result => {
        console.log(result);
        if (result > 0) {
            // 썸네일 삭제
            div.remove();
        }
    })
}