
import React, { useState } from 'react';
import Modal from 'react-modal';
import '../../../assets/scss/contents/my/badgeManage.scss'
import { IoIosCloseCircleOutline } from "react-icons/io"

Modal.setAppElement('#root'); // 접근성 설정

const BadgeManage = ({ isOpen, onRequestClose })=>{

    return (
        <Modal 
            isOpen={isOpen}
            onRequestClose={onRequestClose}
            className="modalManage"
        >
            <IoIosCloseCircleOutline id="modalClose" onClick={onRequestClose} />
            <div id="modaldiv">
                <div id="badgeBox">
                    <div className="badgelist">
                        <div className="badgenamediv">
                            <span className="badgename">컴퓨터공학과 아무개</span>
                        </div>
                        <button className="del">삭제하기</button>
                    </div>
                    <div className="badgelist">
                        <div className="badgenamediv">
                            <span className="badgename">컴퓨터공학과 아무개</span>
                        </div>
                        <button className="del">삭제하기</button>
                    </div>
                   
                </div>
                
                <div className="submitBox">
                    <button className="btnSubmit">저장</button>
                </div>
            </div>
        </Modal>
    );
}

export default BadgeManage