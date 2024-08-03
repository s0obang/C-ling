import React, { useState, useEffect } from 'react';
import '../../../assets/scss/contents/crew/crew.scss';
import Banner from '../../../assets/img/crew/top.png';
import Plus from '../../../assets/img/crew/plus.png';
import Trash from '../../../assets/img/crew/Trash.png';
import { Link, useNavigate } from 'react-router-dom';
import Header from '../../Header';
import { motion, AnimatePresence } from 'framer-motion';

const Crew = () => {
    const fadeIn = {
        hidden: { opacity: 0 },
        visible: { opacity: 1, transition: { duration: 1 } }
    };

    const [apply, setApply] = useState([
        { id: 1, name: 'WAGI', firstResult: '1차 합격', secondResult: '2차 합격' }
    ]);
    const [recrewting, setRecrewting] = useState([
        { id: 1, name: 'NCT WISH', title: '얼굴 괜찮고 키크고 일본어 할 줄 아는 남학생 찾습니다.' }
    ]);
    const [myCrew, setMyCrew] = useState([
        { id: 1, name: '컴퓨터공학과 제 13대 학생회', badge: '컴퓨터공학과 2학년 과대표', status: '리크루팅 현황 확인하기' }
    ]);
    const [badgeOptions, setBadgeOptions] = useState([
        { id: 1, name: '컴퓨터공학과 2학년 대표' },
        { id: 2, name: 'WAGI 홍보부 운영진' }
    ]);
    const [crewForm, setCrewForm] = useState({
        isAdding: false,
        selectedOption: badgeOptions[0]?.name || '',
        newCrewName: ''
    });
    const [nullBadge, setNullBadge] = useState(false);
    const [nullPopup, setNullPopup] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        setNullBadge(badgeOptions.length === 0); 
    }, [badgeOptions]);

    const toggleCrewForm = () => {
        if (nullBadge) {
            setNullPopup(true);
            setTimeout(() => {
                setNullPopup(false);
            }, 1500);
        } else {
            setCrewForm(prevForm => ({
                ...prevForm,
                isAdding: !prevForm.isAdding
            }));
        }
    };

    const handleRegisterCrew = () => {
        if (crewForm.newCrewName.trim() === '') {
            alert('크루명을 입력하세요');
            return;
        }

        const newCrew = {
            id: myCrew.length + 1,
            name: crewForm.newCrewName,
            badge: crewForm.selectedOption,
            status: '리크루팅 현황 확인하기'
        };

        setMyCrew([...myCrew, newCrew]);
        setCrewForm({
            isAdding: false,
            selectedOption: badgeOptions[0]?.name || '',
            newCrewName: ''
        });
    };

    const handleDeleteCrew = (crewId) => {
        if (window.confirm('정말로 이 크루를 삭제하시겠습니까?')) {
            const updatedCrew = myCrew.filter(crew => crew.id !== crewId);
            setMyCrew(updatedCrew);
        }
    };

    const handleDeleteApply = (applyId) => {
        if (window.confirm('정말로 이 지원서를 삭제하시겠습니까?')) {
            const updatedApply = apply.filter(item => item.id !== applyId);
            setApply(updatedApply);
        }
    };

    const handleOptionChange = (e) => {
        setCrewForm(prevForm => ({
            ...prevForm,
            selectedOption: e.target.value
        }));
    };

    const handleNameChange = (e) => {
        setCrewForm(prevForm => ({
            ...prevForm,
            newCrewName: e.target.value
        }));
    };

    const handleCrewClick = (crewId) => {
        navigate(`/mycrew/${crewId}`);
    };

    return (
        <div className='crew-page'>
            <Header />
            <div className="wrap">
                <motion.div className="banner"
                 variants={fadeIn}
                 initial="hidden"
                 animate="visible">
                    <img src={Banner} alt="배너" />
                    
                </motion.div>
                <motion.div className="apply"variants={fadeIn}
                 initial="hidden"
                 animate="visible">
                    <h2 className='sub-title'>현재 나의 지원 현황</h2>
                    {apply.map((item) => (
                        <div className="apply-crew" key={item.id}>
                            <div className="crewname">{item.name}</div>
                            <div className="result first">{item.firstResult}</div>
                            <div className="result second">{item.secondResult}</div>
                            <div className="link">
                                <Link>지원서 다시보기 /</Link>
                                <Link to={`/notice/${item.id}`}>공지사항 보기</Link>
                                <img
                                    src={Trash}
                                    alt="쓰레기"
                                    className="trash-icon"
                                    onClick={() => handleDeleteApply(item.id)}
                                />
                            </div>
                        </div>
                    ))}
                </motion.div>
                <motion.div className="recrewting"
                variants={fadeIn}
                initial="hidden"
                animate="visible">
                    <h2 className='sub-title'>리크루팅 중인 크루</h2>
                    <div className="notice-list">
                        {recrewting.map((item) => (
                            <div className="notice" key={item.id}>
                                <div className="wrap">
                                    <h3 className='name'>{item.name}</h3>
                                    <Link className='notice-title' to={`/recrewting/${item.id}`}><h3>{item.title}</h3></Link>
                                </div>
                                <div className="line"></div>
                            </div>
                        ))}
                    </div>
                </motion.div>
                <motion.div className="mycrew"
                variants={fadeIn}
                initial="hidden"
                animate="visible">
                    <div className='sub-title'>
                        <h2 className='sub-title'>나의 크루</h2>
                        <button className='addbtn' onClick={toggleCrewForm}>{`${crewForm.isAdding ? '완료' : '추가하기'}`}</button>
                    </div>
                    <div className={`${nullBadge ? 'nullcrew' : 'hide'}`}>
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
                    <div className={`${nullBadge ? 'hide' : 'crew-list'}`}>
                        {myCrew.map((crew) => (
                            <div key={crew.id} className="crew">
                                <div className="badge">
                                    <div className="text">{crew.badge}</div>
                                </div>
                                <h2 className="crewname">{crew.name}</h2>
                                <Link className='link' onClick={() => handleCrewClick(crew.id)} to={`/mycrew/${crew.id}`}>리크루팅 현황 확인하기</Link>
                                <button className="crewdel" onClick={() => handleDeleteCrew(crew.id)}>삭제하기</button>
                            </div>
                        ))}
                        <div className={`${crewForm.isAdding ? 'addcrew' : 'hide'}`}>
                            <img src={Plus} alt="+" />
                            <div className="select-wrap">
                                <select name="crewname" id="crewname" value={crewForm.selectedOption} onChange={handleOptionChange}>
                                    {badgeOptions.map((option) => (
                                        <option key={option.id} value={option.name}>{option.name}</option>
                                    ))}
                                </select>
                            </div>
                            <input
                                type="text"
                                placeholder='크루명을 입력하세요'
                                value={crewForm.newCrewName}
                                onChange={handleNameChange}
                            />
                            <button onClick={handleRegisterCrew}>등록하기</button>
                        </div>
                    </div>
                </motion.div>
            </div>
            <div className="empty"></div>

        </div>
    );
};

export default Crew;
