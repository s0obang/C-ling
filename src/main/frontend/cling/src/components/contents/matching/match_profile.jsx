import React, { useState } from 'react';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import '../../../assets/scss/contents/matching/match_profile.scss';
import { Link, useNavigate } from 'react-router-dom';
import Bubble2 from '../../../assets/img/matching/speech-bubble2.png';
import SmallBubble2 from '../../../assets/img/matching/small-speech-bubble2.png';
import Ex from '../../../assets/img/eximg.png';
import Header from '../../Header';

const MatchProfile = ({ text }) => {
    const [isButtonDisabled, setIsButtonDisabled] = useState(false); // 버튼의 활성화/비활성화 상태를 관리
    const navigate = useNavigate(); 

    const handleClick = () => {
        setIsButtonDisabled(true);
        setTimeout(() => {
            navigate('/match');
        }, 1000);
    };

    return (
        <div className='matchprofile'>
            <Header />
            <div className="content">
                <div className="circle circle1"></div>
                <div className="circle circle2"></div>
                <div className="circle circle3"></div>

                <div className="profilecontent">
                    <div className="profileimg profileimg2">
                        <img src={Ex} alt="프로필사진" />
                        <div className="bubbleimg">
                            <img src={Bubble2} alt="말풍선" />
                            <div className="bubble-text">
                                20235539 시온입니다.
                            </div>
                        </div>
                        <div className="smallbubbleimg">
                            <img src={SmallBubble2} alt="말풍선" />
                            <div className="bubble-text">
                                의상 디자인학과
                            </div>
                        </div>
                    </div>
                    <div>
                        <button
                            className={`btn ${isButtonDisabled ? 'disabled ' : ''}`} // 버튼의 활성화/비활성화 상태에 따른 클래스 적용
                            type="button"
                            value="link"
                            onClick={handleClick}
                            disabled={isButtonDisabled}
                        >link</button>
                    </div>
                </div>

            </div>
            <div className={`blur ${isButtonDisabled ? ' ' : 'hide'}`}>
                <div className='toggle' >
                    링크 신청이 완료 되었습니다.
                </div>
            </div>
        </div>
    );
}

export default MatchProfile;
