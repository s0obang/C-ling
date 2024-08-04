import React, { useState, useRef } from 'react';
import Header from '../../Header'
import '../../../assets/scss/contents/homes/noticeWrites.scss'
import styled from "styled-components";

const FileInputWrapper = styled.div`
  .fileInput {
    display: none;
  }

  .customFileUpload {
    border: 1px solid #ccc;
    display: inline-block;
    padding: 6px 12px;
    cursor: pointer;
  }

  .fileName {
    margin-left: 10px;
  }
`;

const NoticeWrites = () => {
    const imageInput = useRef();
    const [fileName, setFileName] = useState('');
  
    // 버튼클릭시 input태그에 클릭이벤트를 걸어준다. 
    const onClickImageUploads = () => {
      imageInput.current.click();
    };
  
    const handleFileChange = (e) => {
      const file = e.target.files[0];
      if (file) {
        setFileName(file.name);
      } else {
        setFileName('');
      }
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (!fileName) {
          alert('배너에 필요한 이미지를 첨부해주세요.');
        } else {
          // 작성 완료 버튼 누르면 제출
          alert('전송이 완료되었습니다.')
        }
      };

    return (
        <div id="noticeWrite">
            <Header></Header>
            <form action="post" className="form">
                <input id="title" type="text" placeholder="제목을 작성하세요" />
                <textarea id="contents" type="textarea" placeholder="내용을 입력하세요">
                </textarea>
            </form>
            <div id="btnBox">
                
            <FileInputWrapper>
              <input
                type="file"
                accept="image/jpg, image/png, image/jpeg"
                ref={imageInput}
                className="fileInput"
                onChange={handleFileChange}
              />
              <div className="btnUpload" onClick={onClickImageUploads}>
              <button className="button">
                    이미지 추가
                </button>
              </div>
              <div className="fileName">{fileName}</div>
            </FileInputWrapper>
                
                <button className="button" onClick={handleSubmit}>
                    작성완료
                </button>
            </div>
        </div>
    )
}

export default NoticeWrites;