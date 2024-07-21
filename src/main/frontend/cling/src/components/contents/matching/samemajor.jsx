import React from 'react';
import '../../../assets/scss/contents/matching/no_matches.scss';
import { Link } from 'react-router-dom';
import Bubble1 from '../../../assets/img/speech-bubble1.png';
import Bubble2 from '../../../assets/img/speech-bubble2.png';
import Ex from '../../../assets/img/eximg.png';
import Header from '../../Header';

const Samemajor = ({ text }) => {
    return (
        <div className='samemajor'>
            <Header />
            <div>
                <h1 className="text">크링된 수정이를 확인해보세요</h1>
            </div>
            <div className="profileimg profileimg1">
                <Link to="/match_profile/1">
                    <img src={Ex} alt="프로필사진" />
                    <div className="bubbleimg">
                        <img src={Bubble1} alt="말풍선" />
                        <div className="bubble-text">
                            {text}
                        </div>
                    </div>
                </Link>
            </div>

            <div className="profileimg profileimg2">
                <Link to="/match_profile/2">
                    <img src={Ex} alt="프로필사진" />
                    <div className="bubbleimg">
                        <img src={Bubble2} alt="말풍선" />
                        <div className="bubble-text">
                            {text}
                        </div>
                    </div>
                </Link>
            </div>

            <div className="profileimg profileimg3">
                <Link to="/match_profile/3">
                    <img src={Ex} alt="프로필사진" />
                    <div className="bubbleimg">
                        <img src={Bubble1} alt="말풍선" />
                        <div className="bubble-text">
                            {text}
                        </div>
                    </div>
                </Link>
            </div>
        </div>
    );
}

export default Samemajor;
