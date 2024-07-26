import React from "react";
import Header from '../../Header'
import '../../../assets/scss/contents/community/noticeWrite.scss'

const noticeWrite = () => {
    return (
        <div id="noticeWrite">
            <Header></Header>
            <form action="post" className="form">
                <input id="title" type="text" placeholder="제목을 작성하세요" />
                <textarea id="contents" type="textarea" placeholder="내용을 입력하세요">
                </textarea>
            </form>
            <div id="btnBox">
                <button className="button">
                    이미지 추가
                </button>
                <button className="button">
                    작성완료
                </button>
            </div>
        </div>
    )
}

export default noticeWrite;