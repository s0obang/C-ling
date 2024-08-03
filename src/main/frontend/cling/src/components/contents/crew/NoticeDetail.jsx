import React from 'react'
import Header from '../../Header';
import left from '../../../assets/img/crew/left.png';
import '../../../assets/scss/contents/crew/notice.scss';
import ex from '../../../assets/img/crew/ex1.png';
import ex2 from '../../../assets/img/crew/ex2.png';
import ex3 from '../../../assets/img/crew/ex3.png';
import { useNavigate } from 'react-router-dom';

import { motion, AnimatePresence } from 'framer-motion';
const NoticeDetail = () => {
    const navigate = useNavigate();
    const handleBackClick = () => {
        navigate(-1);
    };

    const fadeIn = {
        hidden: { opacity: 0 },
        visible: { opacity: 1, transition: { duration: 1 } }
    };
    return (
        <div className='noticedetail'>
            <Header />
            <div className="wrap"
             variants={fadeIn}
             initial="hidden"
             animate="visible">
                <div className="title">
                    <img src={left} alt="뒤로가기" onClick={handleBackClick} />
                    <h1>컴퓨터공학과 제 13대 학생회 부원을 모집합니다. </h1>
                </div>
                <div className="notice">

                    <div className="sub">
                        <div className="day">
                            <h2>모집일정</h2>
                            <div className="today">20232322</div>
                            <div className="halfline"></div>
                            <div className="endday">20232322</div>
                        </div>
                        <div className="plan-select">
                            <h2>선발계획</h2>
                            <div className="plan"><span>1</span>차</div>
                        </div>
                    </div>
                    <div className="contents">
                        <div className="img-box">
                            <img src={ex} alt="" />
                            <img src={ex2} alt="" />
                            <img src={ex3} alt="" /><img src={ex} alt="" />
                            <img src={ex2} alt="" />
                            <img src={ex3} alt="" />
                        </div>
                        <div className="text-box">이것은 지원 모집 글이고요 아무래도 사진이 들어가겠죠 흠 어떻게 사진을 넣으면 좋으려남이것은 지원 모집 글이고요 아무래도 사진이 들어가겠죠 흠 어떻게 사진을 넣으면 좋으려남이것은 지원 모집 글이고요 아무래도 사진이 들어가겠죠 흠 어떻게 사진을 넣으면 좋으려남이것은 지원 모집 글이고요 아무래도 사진이 들어가겠죠 흠 어떻게 사진을 넣으면 좋으려남이것은 지원 모집 글이고요 아무래도 사진이 들어가겠죠 흠 어떻게 사진을 넣으면 좋으려남이것은 지원 모집 글이고요 아무래도 사진이 들어가겠죠 흠 어떻게 사진을 넣으면 좋으려남이것은 지원 모집 글이고요 아무래도 사진이 들어가겠죠 흠 어떻게 사진을 넣으면 좋으려남이것은 지원 모집 글이고요 아무래도 사진이 들어가겠죠 흠 어떻게 사진을 넣으면 좋으려남</div>
                        <div className="file">
                            <h2>•</h2>
                            <div className="filename">와기지원서양식.hwp</div>
                        </div>

                    </div>
                    <div className="line"></div>
                    <div id="btnBox">
                        <button className="send-file">
                            파일 첨부하기
                        </button>
                        <button className="send">
                            지원하기
                        </button>
                    </div>
                </div>
            </div>
            <div className="empty"></div>
            <div className="empty"></div>
        </div>
    )
}

export default NoticeDetail
