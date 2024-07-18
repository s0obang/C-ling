import React, { useState } from "react";
import BadgeModal from './badgemodal';
import '../../../assets/scss/contents/my/myhome.scss';
import Header from '../../Header'
import Myprofil from "./myprofil";
import LinkedCrystal from "./linkedCrystal";
import WantConnect from "./wantConnect";

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
                <span> <Myprofil /></span>
            </div>
            <div>
                <span> <LinkedCrystal /></span>
            </div>
            <div>
                <span> <WantConnect /></span>
            </div>
            <div id = "badge">
                <span onClick={showModal}>관리자에게 문의하기</span>
                <BadgeModal isOpen={modalOpen} onRequestClose={closeModal} />
            </div>
        </div>
    );
}

export default Myhome;