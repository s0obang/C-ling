import React, { useState } from 'react';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css'; // Import default CSS for react-toastify
import '../../../assets/scss/contents/matching/no_matches.scss';
import { Link } from 'react-router-dom';
import Bubble2 from '../../../assets/img/speech-bubble2.png';
import Ex from '../../../assets/img/eximg.png';
import Header from '../../Header';

const MatchProfile = ({ text }) => {

    // Function to handle button click
    const handleClick = () => {
        // Show the toast notification
        toast("E-mail로 인증번호가 발송되었습니다.", {
            position: "top-center", // Display at the top center
            autoClose: 5000, // Auto close after 5 seconds
            closeOnClick: true, // Allow closing on click
            hideProgressBar: true, // Hide progress bar
            draggable: false, // Prevent drag to close
            pauseOnHover: false, // Do not pause on hover
            closeButton: false, // No close button
            className: 'custom-toast', // Custom class name
        });
    };

    return (
        <div className={"matchprofile"}>
            <Header />
            <div className="profileimg profileimg2">
                <img src={Ex} alt="프로필사진" />
                <div className="bubbleimg">
                    <img src={Bubble2} alt="말풍선" />
                    <div className="bubble-text">
                        {text}
                    </div>
                </div>
            </div>
            <div>
                <input className="btn" type="button" value="Clink 하기" onClick={handleClick} />
            </div>
            {/* Toast container */}
            <ToastContainer />
        </div>
    );
}

export default MatchProfile;

