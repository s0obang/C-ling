import React, { useState } from 'react';
import '../../../assets/scss/contents/crew/crew.scss';
import Header from '../../Header';
import left from '../../../assets/img/crew/left.png';
import Folder from '../../../assets/img/crew/Folder.png';
import PassList from './PassList';
import { Link, useNavigate } from 'react-router-dom';
import { motion, AnimatePresence } from 'framer-motion';
import axios from 'axios'


const Mycrew = () => {
    const [selectedPlan, setSelectedPlan] = useState('2'); // 기본값으로 2차를 설정
    const navigate = useNavigate();

    const handleBackClick = () => {
        navigate(-1);
    };
    const fadeIn = {
        hidden: { opacity: 0 },
        visible: { opacity: 1, transition: { duration: 1 } }
    };

    const getPlanOptions = () => {
        if (selectedPlan === '2') {
            return (
                <>
                    <option value="1">1차</option>
                    <option value="2">2차</option>
                </>
            );
        } else {
            return (
                <option value="1">1차</option>
            );
        }
    };
    const listDown = () => {

        axios.get('http://13.48.207.238:1234/downloadStudentList', {
            headers: {
               Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
                'Content-Type': 'text/plain'
            },
            responseType: 'blob', 
            params: {
                recruitingDepartment: 'cs',
                step: '2'
            }
        })
            .then(res => {
                if (res.status === 200) {
                    const url = window.URL.createObjectURL(new Blob([res.data], { type: 'text/plain' }));
                    const link = document.createElement('a');
                    link.href = url;
                    link.setAttribute('download', 'student_list.txt'); 
                    document.body.appendChild(link);
                    link.click();
                    document.body.removeChild(link);
                }
            })
            .catch(err => {
                console.error(err);
             
            });
    };


    return (
        <div className='mycrew-page'>
            <Header />
            <motion.div className="wrap"
                variants={fadeIn}
                initial="hidden"
                animate="visible">
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
                                <Link className="notice-title">컴퓨터공학과 제 13대 학생회 부원을 모집합니다.</Link>
                                <button className="del">삭제하기</button>
                            </div>
                            <div className="line"></div>
                        </div>
                        <div className="notice">
                            <div className="inner">
                                <div className="day">2030/04/05</div>
                                <Link className="notice-title">컴퓨터공학과 제 13대 학생회 부원을 모집합니다.</Link>
                                <button className="del">삭제하기</button>
                            </div>
                            <div className="line"></div>
                        </div>
                        <div className="notice">
                            <div className="inner">
                                <div className="day">2030/04/05</div>
                                <Link className="notice-title">컴퓨터공학과 제 13대 학생회 부원을 모집합니다.</Link>
                                <button className="del">삭제하기</button>
                            </div>
                            <div className="line"></div>
                        </div>

                    </div>
                    <Link to='/noticeWrite' className='towrite'>글 작성</Link>
                </div>
                <div className="recrew">
                    <h2 className='sub-title'>리크루팅 현황</h2>
                    <div className="recrew-wrap">
                        <h3>현재 리크루팅 진행중인 공고 : <span>컴퓨터공학과 제 13대 학생회 부원 모집</span></h3>
                        <h3>모집 일정 : <span>2030/04/05 ~ 2030/05/05 </span></h3>
                        <h3>선발 계획 : <span>2차</span></h3>
                    </div>
                    <select className="plan"  >
                        {getPlanOptions()}
                    </select>
                    <PassList />
                    <button className="send">합/불 입력 완료</button>
                    <p className="send-text">※ 합/불 입력 완료 이후  지원자에게 메일이 발송되며  합/불 수정이 불가능합니다. </p>
                </div>
                <div className="list-down">
                    <h2 className="sub-title">최종 합격자 명단 다운로드</h2>
                    <div className="file">
                        <img src={Folder} alt="Folder" />
                        <div className="filename" onClick={listDown}>컴퓨터공학과 제 13대 학생회 부원을 모집합니다.</div>
                    </div>
                </div>
            </motion.div>

            <div className="empty"></div>
            <div className="empty"></div>
        </div>
    );
};

export default Mycrew;
