import React, { useState, useRef } from 'react';
import Modal from 'react-modal';
import '../../../assets/scss/contents/my/badgeRequest.scss'
import { IoIosCloseCircleOutline } from "react-icons/io";
import styled from "styled-components";

Modal.setAppElement('#root'); // 접근성 설정

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

const BadgeRequest = ({ isOpen, onRequestClose }) => {
  const imageInput = useRef();
  const [fileName, setFileName] = useState('');

  // 버튼클릭시 input태그에 클릭이벤트를 걸어준다. 
  const onClickImageUpload = () => {
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

  return (
    <Modal 
        isOpen={isOpen}
        onRequestClose={onRequestClose}
        className="modalRequest"
    >
        <IoIosCloseCircleOutline id="modalClose" onClick={onRequestClose} />
        <div id="modaldiv">
            <form action="post" className="form">
                <div className='formcolumn'><span className ="formtext">이름</span><input className="input" type="text" /></div>
                <div className='formcolumn'><span className ="formtext">학과</span><input className="input" type="text" /></div>
                <div className='formcolumn'><span className ="formtext">학번</span><input className="input" type="text" /></div>
                <div className='formcolumn'><span className ="formtext">직책</span><input className="input" type="text" /></div>
            </form>
            <FileInputWrapper>
              <input
                type="file"
                accept="image/jpg, image/png, image/jpeg"
                ref={imageInput}
                className="fileInput"
                onChange={handleFileChange}
              />
              <div className="btnUpload" onClick={onClickImageUpload}>
                인증할 이미지 선택
              </div>
              <div className="fileName">{fileName}</div>
            </FileInputWrapper>
            <div className="submitBox">
                <button className="btnSubmit">수락</button>
            </div>
        </div>
    </Modal>
  );
}

export default BadgeRequest;
