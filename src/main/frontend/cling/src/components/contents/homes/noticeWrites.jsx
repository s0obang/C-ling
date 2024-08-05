import React, { useState, useRef } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import Header from '../../Header';
import '../../../assets/scss/contents/homes/noticeWrites.scss';
import styled from 'styled-components';

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

  .fileNames {
    margin-left: 10px;
    list-style-type: none;
    padding: 0;
  }

  .fileNames li {
    margin-bottom: 5px;
  }
`;

const NoticeWrites = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const imageInput = useRef();
  const [fileNames, setFileNames] = useState([]);

  const onClickImageUploads = () => {
    imageInput.current.click();
  };

  const handleFileChange = (e) => {
    const files = Array.from(e.target.files);
    if (files.length > 5) {
      alert('최대 5개의 이미지만 첨부할 수 있습니다.');
      return;
    }
    setFileNames(files.map(file => file.name));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (fileNames.length === 0) {
      alert('배너에 필요한 이미지를 첨부해주세요.');
    } else {
      // 제출 로직
      alert('전송이 완료되었습니다.');
      navigate(-1);
    }
  };

  return (
    <div id="noticeWrites">
      <Header />
      <form action="post" className="form">
        <input id="title" type="text" placeholder="제목을 작성하세요" />
        <textarea id="contents" placeholder="내용을 입력하세요" />
      </form>
      <div id="btnBox">
        <FileInputWrapper>
          <input
            type="file"
            accept="image/jpg, image/png, image/jpeg"
            ref={imageInput}
            className="fileInput"
            multiple
            onChange={handleFileChange}
          />
          <div className="btnUpload" onClick={onClickImageUploads}>
            <button className="button1">이미지 업로드</button>
          </div>
          <ul className="fileName1">
            {fileNames.map((fileName, index) => (
              <li key={index}>{fileName}</li>
            ))}
          </ul>
        </FileInputWrapper>
        <button className="button2" onClick={handleSubmit}>
          작성완료
        </button>
      </div>
    </div>
  );
};

export default NoticeWrites;
