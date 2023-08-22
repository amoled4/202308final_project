async function postCommentToserver(cmtData) {
    try {
        const url = "/board_location_comment/post";
        const config = {
            method: 'post',
            headers: {
                'content-type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtData)
        };
        const response = await fetch(url, config);
        const result = await response.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

document.getElementById('postCmt').addEventListener('click', () => {
    const cmtText = document.getElementById('loc-content').value;
    if (cmtText == null || cmtText == "") {
        alert("답변을 입력해 주세요.");
        document.getElementById('loc-content').focus();
        return false;
    } else {
        let cmtData = {
            bl_number: bl_number,
            blc_writer: document.getElementById('cmtWriter').value,
            blc_content: cmtText
        };
        console.log(cmtData);
        postCommentToserver(cmtData).then(result => {
            if (result > 0) {
                document.getElementById('loc-content').value = "";
                getCommentList(bl_number);
            }
        })
    }
})

async function spreadCommentFromServer(bl_number) {
    try {
        const response = await fetch("/board_location_comment/" + bl_number);
        const result = await response.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

function getCommentList(bl_number) {
    spreadCommentFromServer(bl_number).then(result => {
        const div = document.getElementById('cmtListArea');
        if (result.length > 0) {
            div.innerHTML = "";
            for (let blcvo of result) {
                let inner = `<div id="inner-box">
                <div id="writer-container">
                    <div id="writer-box2">
                        <div id="writer-profile">
                            <img src="/resources/image/cat2.png" alt="Image">
                        </div>`;
                inner += `<div id="writer-info">
                <div id="nick-info">
                    <span class="nickname">${blcvo.blc_writer}</span>
                </div>`;
                inner += `<div id="reg-info">
                <span class="reg_location">계양구</span>
                <span class="reg_dot">·</span>
                <span class="reg_time">${blcvo.blc_reg_date}</span>
            </div>
        </div>
    </div>`;
                inner += `<div id="comment">${blcvo.blc_content}</div>
                </div>
                </div>`;
                div.innerHTML += inner;
                
            }
        }
    })
}