import React from 'react'
import Header from '../../Header'
import '../../../assets/scss/contents/matching/no_matches.scss'
import { Link } from 'react-router-dom'
import Bubble1 from '../../../assets/img/speech-bubble1.png';
import Bubble2 from '../../../assets/img/speech-bubble2.png';
import Ex from '../../../assets/img/eximg.png'

const othermajor = ({ text }) => {
    return (
        <div className='othermajor'>           
            <Header></Header>
            <div>
                <h1 className="text">크링된 다른 과 수정이를 확인해보세요</h1>
            </div>
            <div className="profileimg profileimg1">
                <img src={Ex} alt="프로필사진" />
                <div className="bubbleimg">
                    <img src={Bubble1} alt="말풍선" />
                    <div className="bubble-text">
                        {text}
                    </div>
                </div>
            </div>

            <div className="profileimg profileimg2">
                <img src={Ex} alt="프로필사진" />
                <div className="bubbleimg">
                    <img src={Bubble2} alt="말풍선" />
                    <div className="bubble-text">
                        {text}
                    </div>
                </div>
            </div>

            <div className="profileimg profileimg3">
                <img src={Ex} alt="프로필사진" />
                <div className="bubbleimg">
                    <img src={Bubble1} alt="말풍선" />
                    <div className="bubble-text">
                        {text}
                    </div>
                </div>
            </div>

        </div>
    )
}

export default othermajor