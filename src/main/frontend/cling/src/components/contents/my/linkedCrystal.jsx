import React from "react";
import '../../../assets/scss/contents/my/linkedCrystal.scss'
import BGPROFIL from '../../../assets/img/profil_background.png'
import IMG_PROFIL from '../../../assets/img/testimg.png'

const LinkedCrystal = () => {

    const users = [
        {
          username: '김준희',
          userkey: '20231133',
          usermajor: '컴퓨터공학과'
        },
        {
          username: '홍길동',
          userkey: '20242913',
          usermajor: '미디어커뮤니케이션'
        },
        {
          username: '최수진',
          userkey: '20239194',
          usermajor: '심리학과'
        }
      ];

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
                                <span className="text2">{users[0].username}</span>
                            </div>
                            <div className="infotext">
                                <span className="text1">학번</span>
                                <span className="text2">{users[0].userkey}</span>
                            </div>
                            <div className="infotext">
                                <span className="text1">학과</span>
                                <span className="text2">{users[0].usermajor}</span>
                            </div>
                        </div>
                    </div>

                    <div className="profil">
                    <img src={IMG_PROFIL} alt="imghuman" className="imghuman" />
                    <img src={BGPROFIL} alt="imgprofil" className="imgprofil" />
                        <div id="info">
                            <div className="infotext">
                                <span className="text1">이름</span>
                                <span className="text2">{users[1].username}</span>
                            </div>
                            <div className="infotext">
                                <span className="text1">학번</span>
                                <span className="text2">{users[1].userkey}</span>
                            </div>
                            <div className="infotext">
                                <span className="text1">학과</span>
                                <span className="text2">{users[1].usermajor}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default LinkedCrystal;