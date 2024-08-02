import React from 'react'
import '../../../assets/scss/contents/crew/crew.scss';
import Header from '../../Header'
import left from '../../../assets/img/crew/left.png';
import PassList from './PassList';
import { useNavigate } from 'react-router-dom';

const Mycrew = () => {
    const navigate = useNavigate();

    const handleBackClick = () => {
        navigate(-1);
    };
    return (
        <div className='mycrew-page'>
            <Header />
            <div className="wrap">
                <div className="title">
                    <img src={left} alt="뒤로가기" onClick={handleBackClick} />
                    <h1>컴퓨터공학과 제 13대 학생회</h1>
                </div>
                <div className="recrew-notice">
                    <h2 className='sub-title'>작성한 리크루팅 공고</h2>
                    <div className="notice-list">
                        <div className="notice">
                            <div className="inner">
                                <div className="day">2030/04/05</div>
                                <div className="notice-title">컴퓨터공학과 제 13대 학생회 부원을 모집합니다. </div>
                                <button className="del">삭제하기</button>
                            </div>
                            <div className="line"></div>
                        </div>
                    </div>
                </div>
                <div className="recrew">

                    <h2 className='sub-title'>리크루팅 현황</h2>
                    <div className="recrew-wrap">
                        <h3>현재 리크루팅 진행중인 공고 : <span>컴퓨터공학과 제 13대 학생회 부원 모집</span></h3>
                        <h3>모집 일정 : <span>2030/04/05 ~ 2030/05/05 </span></h3>
                        <h3>선발 계획 : <span>2차</span></h3>

                    </div>
                    <select className="plan">
                        <option value="1">1차</option>
                        <option value="2">2차</option>
                    </select>
                    <PassList />
                </div>
            </div>

        </div>
    )
}

export default Mycrew
