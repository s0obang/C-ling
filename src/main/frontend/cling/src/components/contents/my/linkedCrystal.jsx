import React from "react";
//npm i @lasbe/react-scroll-animation
import { ScrollAnimation } from "@lasbe/react-scroll-animation";
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
                    {users.length === 0 ? (
                        <span className="linkedZero">나와 연결된 수정이가 없습니다 !</span>
                    ) : (
                        users.map((user, index) => (
                            <ScrollAnimation
                            startingPoint="left"  //애니메이션 시작지점
                            duration={1}    //지속시간
                            amount="md" //움직임 양
                            >
                            <div className="profil" key={index}>
                                <img src={IMG_PROFIL} alt="imghuman" className="imghuman" />
                                <img src={BGPROFIL} alt="imgprofil" className="imgprofil" />
                                <div id="info">
                                    <div className="infotext">
                                        <span className="text1">이름</span>
                                        <span className="text2">{user.username}</span>
                                    </div>
                                    <div className="infotext">
                                        <span className="text1">학번</span>
                                        <span className="text2">{user.userkey}</span>
                                    </div>
                                    <div className="infotext">
                                        <span className="text1">학과</span>
                                        <span className="text2">{user.usermajor}</span>
                                    </div>
                                </div>
                            </div>
                            </ScrollAnimation>
                        ))
                    )}
                </div>
            </div>
        </div>
    );
};

export default LinkedCrystal;
