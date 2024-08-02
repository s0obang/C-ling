import React, { useState } from 'react';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css'; // Import default CSS for react-toastify
import '../../../assets/scss/contents/matching/match_profile.scss';
import { Link } from 'react-router-dom';
import Bubble2 from '../../../assets/img/matching/speech-bubble2.png';
import SmallBubble2 from '../../../assets/img/matching/small-speech-bubble2.png';
import Ex from '../../../assets/img/eximg.png';
import Header from '../../Header';

const MatchProfile = ({ text }) => {

    // Function to handle button click
    const handleClick = () => {
        // Show the toast notification
        toast("링크 신청이 완료 되었습니다.", {
            position: "top-center",
            autoClose: 5000,
            closeOnClick: true,
            hideProgressBar: true,
            draggable: false,
            pauseOnHover: false,
            closeButton: false,
            className: 'custom-toast',
        });
    };

    return (
        <div className="matchprofile">
            <Header />
            <div className='content'>
   

                <div className="circle circle1"></div>
                <div className="circle circle2"></div>
                <div className="circle circle3"></div>

                <div className='profilecontent'>
                    <div className="profileimg profileimg2">
                        <img src={Ex} alt="프로필사진" />
                        <div className="bubbleimg">
                            <img src={Bubble2} alt="말풍선" />
                            <div className="bubble-text">
                                {text}
                            </div>
                        </div>
                        <div className="smallbubbleimg">
                            <img src={SmallBubble2} alt="말풍선" />
                            <div className="bubble-text">
                                {text}
                            </div>
                        </div>
                    </div>
                    <div>
                        <input className="btn" type="button" value="Clink 하기" onClick={handleClick} />
                    </div>
                    <ToastContainer />
                </div>
            </div>


        </div>
    );
}

export default MatchProfile;

