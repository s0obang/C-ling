import React from 'react';
import '../../../assets/scss/contents/matching/no_matches.scss';
import { Link } from 'react-router-dom';
import Bubble1 from '../../../assets/img/matching/speech-bubble1.png';
import Bubble2 from '../../../assets/img/matching/speech-bubble2.png';
import Ex from '../../../assets/img/eximg.png';
import Header from '../../Header';



const Samemajor = (props, { text }) => {
    return (
        <div className='samemajor'>
            <Header />
            <div>
                <h1 className="text">크링된 수정이를 확인해보세요</h1>
            </div>
            <Link to="/matchprofile">
                <div className="profileimg profileimg1">
                        <img src={Ex} alt="프로필사진" />
                        <div className="bubbleimg">
                            <img src={Bubble1} alt="말풍선" />
                            <div className="bubble-text">
                                20231139 박시현입니다.
                            </div>
                        </div>
                </div>
            </Link>

            <Link to="/matchprofile">
                <div className="profileimg profileimg2">
                        <img src={Ex} alt="프로필사진" />
                        <div className="bubbleimg">
                            <img src={Bubble2} alt="말풍선" />
                            <div className="bubble-text">
                                20051139 사쿠야입니다.
                            </div>
                        </div>
                </div>
            </Link>

            <Link to="/matchprofile">
                <div className="profileimg profileimg3">
                        <img src={Ex} alt="프로필사진" />
                        <div className="bubbleimg">
                            <img src={Bubble1} alt="말풍선" />
                            <div className="bubble-text">
                                20181139 떵쿠시입니다.
                            </div>
                        </div>
                </div>
            </Link>
        </div>
    );
}

export default Samemajor;
