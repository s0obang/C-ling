import React from "react";
import '../../../assets/scss/contents/my/myprofil.scss'
import BGPROFIL from '../../../assets/img/profil_background.png'
import OFF from '../../../assets/img/btnoff.png'
import ON from '../../../assets/img/btnon.png'

const Myprofil = () => {
    return (
    <div className="myprofil">
        <div className="profilcard">
            <div id="name">
                <span id="username">최수진님</span>
                <div id="badgenamediv">
                    <span id="badgename">컴퓨터공학과 2학년 과대표</span>
                </div>
            </div>
            <img src={BGPROFIL} alt="imgprofil" id="imgprofil" />
            <div id="info">
                <div className="infotext">
                    <span className="text1">이름</span>
                    <span className="text2">최수진</span>
                </div>
                <div className="infotext">
                    <span className="text1">학번</span>
                    <span className="text2">20231133</span>
                </div>
                <div className="infotext">
                    <span className="text1">학과</span>
                    <span className="text2">컴퓨터공학과</span>
                </div>
            </div>
        </div>
        <div id="log">
                <span id="edit">수정하기</span>
                <span> / </span>
                <span id="logout">로그아웃</span>
        </div>
        
        <div className="setdiv">
            <div className="onoff">
                <span className="onofftext">메일 알람</span>
                <div>
                <img src={ON} alt="imgon" id="imgon" />
                </div>
            </div>
            <div className="onoff">
                <span className="onofftext">수정이들과의 연결</span>
                <div>
                <img src={ON} alt="imgon" id="imgon" />
                </div>
            </div>
        </div>
    </div>
    )
}

export default Myprofil;