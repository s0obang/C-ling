import React from "react";
import '../../../assets/scss/contents/my/linkedCrystal.scss'
import BGPROFIL from '../../../assets/img/profil_background.png'
import IMG_PROFIL from '../../../assets/img/testimg.png'

const LinkedCrystal = () => {
    return (
        <div className="linkedprofil">
            <div className="profilcard">
                <div id="title">
                    <span id="username">나와 연결된 수정이들</span>
                </div>
                <div id="profillist">
                    <div className="profil">
                    <img src={IMG_PROFIL} alt="imghuman" className="imghuman" />
                    <img src={BGPROFIL} alt="imgprofil" className="imgprofil" />
                        <div id="info">
                            <div className="infotext">
                                <span className="text1">이름</span>
                                <span className="text2">김준희</span>
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

                    <div className="profil">
                    <img src={IMG_PROFIL} alt="imghuman" className="imghuman" />
                    <img src={BGPROFIL} alt="imgprofil" className="imgprofil" />
                        <div id="info">
                            <div className="infotext">
                                <span className="text1">이름</span>
                                <span className="text2">김준희</span>
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
                </div>
            </div>
        </div>
    )
}

export default LinkedCrystal;