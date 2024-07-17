import React, { useState } from "react";
import BadgeModal from './badgemodal';
import '../../../assets/scss/contents/my/myhome.scss';
import Header from '../../Header'

const Myhome = () => {
    // 모달창 노출 여부 state
    const [modalOpen, setModalOpen] = useState(false);

    // 모달창 노출
    const showModal = () => {
        setModalOpen(true);
    };

    const closeModal = () => {
        setModalOpen(false);
    };

    return (
        <div className="myhome">
            <Header></Header>
            <div>
                <text> ㅎㅇㅎㅇ</text>
            </div>
            <div id = "badge">
                <text onClick={showModal}>관리자에게 문의하기</text>
                <BadgeModal isOpen={modalOpen} onRequestClose={closeModal} />
            </div>
        </div>
    );
}

export default Myhome;