import React from 'react'
import Modal from 'react-modal';
import '../../../assets/scss/contents/my/badgemodal.scss'

Modal.setAppElement('#root'); // 접근성 설정

const BadgeModal = ({ isOpen, onRequestClose })=>{
    
    return (
        <div className="modal">
        <Modal 
            isOpen={isOpen}
            onRequestClose={onRequestClose}
        >
            <form action="post" className="form">
                <input className="name" type="text" />
                <input className="major" type="text" />
                <input className="num" type="text" />
                <input className="mark" type="text" />
                <input className="btn" type="button" value="전송" />
            </form>
        </Modal>
        </div>
    );
}

export default BadgeModal
