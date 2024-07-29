import React, { useState } from 'react';
import Modal from 'react-modal';
import '../../../assets/scss/contents/my/badgeRequest.scss'
import { IoIosCloseCircleOutline } from "react-icons/io"

Modal.setAppElement('#root'); // 접근성 설정


const BadgeRequest = ({ isOpen, onRequestClose })=>{

    return (
        <Modal 
            isOpen={isOpen}
            onRequestClose={onRequestClose}
            className="modalRequest"
        >
            <IoIosCloseCircleOutline id="modalClose" onClick={onRequestClose} />
            <div id="modaldiv">
                <form action="post" className="form">
                    <div className='formcolumn'><span className ="formtext">이름</span><input className="name" type="text" /></div>
                    <div className='formcolumn'><span className ="formtext">학과</span><input className="major" type="text" /></div>
                    <div className='formcolumn'><span className ="formtext">학번</span><input className="num" type="text" /></div>
                    <div className='formcolumn'><span className ="formtext">직책</span><input className="mark" type="text" /></div>
                </form>
                <span id ="imgselect">인증 이미지 선택</span>
                <div className="submitBox">
                    <button className="btnSubmit">수락</button>
                </div>
            </div>
        </Modal>
    );
}

export default BadgeRequest
