// Qna 답변 등록
async function postCommentToserver(cmtData) {
    try {
        const url = "/board_qna_comment/post";
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
            bq_number: bq_number,
            bqc_writer: document.getElementById('cmtWriter').value,
            bqc_content: cmtText
        };
        console.log(cmtData);
        postCommentToserver(cmtData).then(result => {
            if (result > 0) {
                document.getElementById('loc-content').value = "";
                getCommentList(bq_number);
            }
        })
    }
})

async function spreadCommentFromServer(bq_number) {
    try {
        const response = await fetch("/board_qna_comment/" + bq_number);
        const result = await response.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

function getCommentList(bq_number) {
    spreadCommentFromServer(bq_number).then(result => {
        const div = document.getElementById('cmtListArea');
        if (result.length > 0) {
            div.innerHTML = "";
            for (let bqcvo of result) {
                let inner = `<div id="inner-box">
			                    <div id="writer-box">
				                    <div id="writer-profile">
					                    <img src="/resources/image/basicprofile.jpeg" alt="Image">
				                    </div>
                                    <div id="writer-info">
                                        <div id="nick-info">`;
                inner += `<span class="nickname">${bqcvo.bqc_writer}</span>
					                    </div>
                                        <div id="reg-info">`;
                inner += `<span class="reg_time">${bqcvo.bqc_reg_date}</span>
					                    </div>
                                    </div>
                                </div>`;
                inner += `<div id="content-box"><p style="white-space: pre-line;">${bqcvo.bqc_content}</p></div></div>`;
                // inner.replace(/(?:\r\n|\r|\n)/g, '<br />');
                div.innerHTML += inner;
                
            }
        }
    })
}