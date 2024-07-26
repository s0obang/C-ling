import React, { useState } from "react";
import Slider from "react-slick";
import '../../../assets/scss/contents/my/wantConnect.scss'
import { ImCircleLeft, ImCircleRight } from "react-icons/im";
import BGCHAT from '../../../assets/img/chat_img.png'
import IMG from '../../../assets/img/testimg2.png'

const WantConnect = () => {

    const [currentSlide, setCurrentSlide] = useState(0);
    
    const settings = {
        dots: true,
        infinite: true,
        speed: 500,
        slidesToShow: 1,
        slidesToScroll: 1,
        beforeChange: (oldIndex, newIndex) => setCurrentSlide(newIndex),
        nextArrow: <ImCircleRight className="btn_RL" />,
        prevArrow: <ImCircleLeft className="btn_RL" />
      };
    
    return (
        <div id="wantPage">
            <span id = "title"> 연결을 원하는 수정이들 </span>
            
            <div id="boxs">
                <ImCircleLeft className="btn_RL" />
                <div id = "wantprofil">  </div>
                <ImCircleRight className="btn_RL" />
                <div id="circle">2</div>
            </div>
            <div id="yesOrNo">
                <button className="button">수락</button>
                <button className="button">거절</button>
            </div>
            <div id="introduce">
                <img src={IMG} alt="img" id="img" />
                <img src={BGCHAT} alt="img" id="chatBg1" />
                <img src={BGCHAT} alt="img" id="chatBg2" />
                <span id="intro1">20231133 김준희 입니다.</span>
                <span id="intro2">컴퓨터공학과</span>
            </div>
        </div>
        
    )
}

export default WantConnect;