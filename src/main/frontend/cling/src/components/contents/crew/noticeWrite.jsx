import React, { useState } from 'react';
import DatePicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";
import Header from '../../Header';
import '../../../assets/scss/contents/crew/noticeWrite.scss';
import left from '../../../assets/img/crew/left.png';
import { useNavigate } from 'react-router-dom';

const NoticeWrite = () => {
    const [title, setTitle] = useState('');
    const [endDate, setEndDate] = useState(new Date());
    const [selectionPlan, setSelectionPlan] = useState('1');
    const [content, setContent] = useState('');


    const navigate = useNavigate();

    const today = new Date();
    const formattedDate = `${today.getFullYear()}/${String(today.getMonth() + 1).padStart(2, '0')}/${String(today.getDate()).padStart(2, '0')}`;

    const handleBackClick = () => {
        navigate(-1);
    };

    

    return (
        <div id="noticeWrite">
            <Header />
            <div className="wrap">
                <div className="title">
                    <img src={left} alt="뒤로가기" onClick={handleBackClick} />
                    <h1>컴퓨터공학과 제 13대 학생회</h1>
                </div>
                <form className="form">
                    <input
                        id="title"
                        type="text"
                        placeholder="제목을 작성하세요"
                        value={title}
                        onChange={(e) => setTitle(e.target.value)}
                    />
                    <div className="plan">
                        <div className="day">
                            <h2>모집일정</h2>
                            <div className="today">{formattedDate}</div>
                            <h2>-</h2>
                            <DatePicker
                                selected={endDate}
                                onChange={(date) => setEndDate(date)}
                                dateFormat="yyyy/MM/dd"
                                className="endday"
                                
                            />
                        </div>
                        <div className="plan-select">
                            <h2>선발계획</h2>
                            <select
                                value={selectionPlan}
                                onChange={(e) => setSelectionPlan(e.target.value)}
                            >
                                <option value="1">1차</option>
                                <option value="2">2차</option>
                            </select>
                        </div>
                    </div>
                    <textarea
                        id="contents"
                        placeholder="지원자들의 혼동을 줄이기위해 글 작성 이후 수정이 제한되어있습니다."
                        value={content}
                        onChange={(e) => setContent(e.target.value)}
                    />
                </form>
                <div id="btnBox">
                    
                    <button className="send-img">
                        이미지 업로드
                    </button>
                    <button className="send-file">
                        파일 첨부하기
                    </button>
                    <button className="send">
                        작성완료
                    </button>
                </div>
            </div>
            <div className="empty"></div>
        </div>
    );
}

export default NoticeWrite;
