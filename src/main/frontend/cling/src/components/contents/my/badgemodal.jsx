import React, { useState } from 'react';
import Modal from 'react-modal';
import '../../../assets/scss/contents/my/badgemodal.scss'
import BTNMO from '../../../assets/img/btn_modal.png'
import POPUPCOM from '../../../assets/img/popup_complete.png'
import { IoIosCloseCircleOutline } from "react-icons/io"

Modal.setAppElement('#root'); // 접근성 설정


const BadgeModal = ({ isOpen, onRequestClose })=>{
   

    return (
        <Modal 
            isOpen={isOpen}
            onRequestClose={onRequestClose}
            className="modal"
        >
            <IoIosCloseCircleOutline id="modalClose" onClick={onRequestClose} />
            <div id="modaldiv">
                <form action="post" className="form">
                    <div className='formcolumn'><text className ="formtext">이름</text><input className="name" type="text" /></div>
                    <div className='formcolumn'><text className ="formtext">학과</text><input className="major" type="text" /></div>
                    <div className='formcolumn'><text className ="formtext">학번</text><input className="num" type="text" /></div>
                    <div className='formcolumn'><text className ="formtext">직책</text><input className="mark" type="text" /></div>
                </form>
                <text id ="imgselect">인증 이미지 선택</text>
                <div className="btn_modal">
                    <img src={BTNMO} alt="btn_modal"  />
                    
                </div>
                    
            </div>
        </Modal>
    );
}

export default BadgeModal
