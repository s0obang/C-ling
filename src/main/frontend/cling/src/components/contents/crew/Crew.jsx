import React, { useState } from 'react';
import '../../../assets/scss/contents/crew/crew.scss';
import Banner from '../../../assets/img/crew/top.png';
import Plus from '../../../assets/img/crew/plus.png';
import { Link } from 'react-router-dom';
import Header from '../../Header';
import { motion, AnimatePresence } from 'framer-motion';

const Crew = () => {
    const [result, setResult] = useState("");
    const [addCrew, setAddCrew] = useState(false);
    const [nullCrew, setNullCrew] = useState(false);
    const [nullPopup, setNullPopup] = useState(false);

    const resultShow = () => {
        // 여기에 resultShow 함수 로직 추가
    };

    const addcrewForm = () => {
        if (nullCrew) {
            setNullPopup(true);
            setTimeout(() => {
                setNullPopup(false);
            }, 1500); 
        } else {
            setAddCrew(!addCrew);
        }
    };

    return (
        <div className='crew-page'>
            <Header />
            <div className="wrap">
                <div className="banner">
                    <img src={Banner} alt="배너" />
                </div>
                <div className="apply">
                    <h2 className='sub-title'>현재 나의 지원 현황</h2>
                    <div className="apply-crew">
                        <div className="crewname">WAGI</div>
                        <div className="result first">1차 합격</div>
                        <div className="result second">2차 합격</div>
                        <div className="link">
                            <Link>지원서 다시보기 /</Link>
                            <Link>공지사항 보기</Link>
                        </div>
                    </div>
                </div>
                <div className="recrewting">
                    <h2 className='sub-title'>리크루팅 중인 크루</h2>
                    <div className="notice-list">
                        <div className="notice">
                            <div className="wrap">
                                <h3 className='name'>NCT WISH</h3>
                                <h3 className='notice-title'>얼굴 괜찮고 키크고 일본어 할 줄 아는 남학생 찾습니다.</h3>
                            </div>
                            <div className="line"></div>
                        </div>
                        <div className="notice">
                            <div className="wrap">
                                <h3 className='name'>NCT WISH</h3>
                                <h3 className='notice-title'>얼굴 괜찮고 키크고 일본어 할 줄 아는 남학생 찾습니다.</h3>
                            </div>
                            <div className="line"></div>
                        </div>
                    </div>
                </div>
                <div className="mycrew">
                    <div className='sub-title'>
                        <h2 className='sub-title'>나의 크루</h2>
                        <button className='addbtn' onClick={addcrewForm}>{`${addCrew ? '완료' : '추가하기'}`}</button>
                    </div>
                    <div className={`${nullCrew ? 'nullcrew' : 'hide'}`}>
                        <h2>현재 내가 관리 중인 크루가 없습니다.</h2>
                        <AnimatePresence>
                            {nullPopup && (
                                <motion.div
                                    className="null-popup"
                                    initial={{ opacity: 0, y: -10 }}
                                    animate={{ opacity: 1, y: 0 }}
                                    exit={{ opacity: 0, y: -10 }}
                                    transition={{ duration: 0.5 }}
                                >
                                    관리자가 부여한 역할이 존재하지 않습니다. <br />
                                    MY 페이지에서 역할 뱃지를 등록하세요
                                </motion.div>
                            )}
                        </AnimatePresence>
                    </div>
                    <div className={`${nullCrew ? 'hide' : 'crew-list'}`}>
                        <div className="crew">
                            <div className="badge">
                                <div className="text">컴퓨터공학과 2학년 과대표</div>
                            </div>
                            <h2 className="crewname">컴퓨터공학과 제 13대 학생회</h2>
                            <Link className='link'>리크루팅 현황 확인하기</Link>
                        </div>
                        <div className={`${addCrew ? 'addcrew' : 'hide'}`}>
                            <img src={Plus} alt="+" />
                            <div className="select-wrap">
                                <select name="crewname" id="crewname">
                                    <option value="컴퓨터공학과 2학년 대표" selected>컴퓨터공학과 2학년 대표</option>
                                    <option value="WAGI 홍보부 운영진">WAGI 홍보부 운영진</option>
                                </select>
                            </div>
                            <input type="text" placeholder='크루명을 입력하세요' />
                            <button>등록하기</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Crew;
