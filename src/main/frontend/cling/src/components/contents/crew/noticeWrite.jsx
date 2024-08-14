import React, { useState } from 'react';
import DatePicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";
import Header from '../../Header';
import '../../../assets/scss/contents/crew/noticeWrite.scss';
import left from '../../../assets/img/crew/left.png';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const NoticeWrite = (name) => {
    const [title, setTitle] = useState('');
    const [endDate, setEndDate] = useState(new Date());
    const [selectionPlan, setSelectionPlan] = useState('1');
    const [content, setContent] = useState('');
    const [image, setImage] = useState(null);
    const [file, setFile] = useState(null);
    const navigate = useNavigate();

    const write = () => {
        const formData = new FormData();
        formData.append('recruitingDepartment', {name}); 
        formData.append('title', title);
        formData.append('content', content);
        formData.append('step', selectionPlan);
        formData.append('startDate', formattedDate);
        formData.append('dueDate', endDate.toISOString().split('T')[0]);

        if (image) {
            formData.append('images', image);
        }

        if (file) {
            formData.append('file', file);
        }

        axios.post('https://clinkback.store/recruitment/write', formData, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
                'Content-Type': 'multipart/form-data'
            }
        })
            .then(res => {
                if (res.status === 200) {
                    console.log(res);   
                    navigate(-1);
                }
            })
            .catch(err => {
                console.error(err);
            });
    };

    const today = new Date();
    const formattedDate = `${today.getFullYear()}/${String(today.getMonth() + 1).padStart(2, '0')}/${String(today.getDate()).padStart(2, '0')}`;

    const handleBackClick = () => {
        navigate(-1);
    };

    const handleImageChange = (e) => {
        setImage(e.target.files[0]);
    };

    const handleFileChange = (e) => {
        setFile(e.target.files[0]);
    };

    return (
        <div id="noticeWrite">
            <Header />
            <div className="wrap">
                <div className="title">
                    <img src={left} alt="뒤로가기" onClick={handleBackClick} />
                    <h1>{name}</h1>
                </div>
                <div className="form">
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

                </div>
                <div id="btnBox">
                    <label htmlFor="send-img" className='send-img'>이미지 업로드</label>
                    <input
                        type="file"
                        accept="image/*"
                        onChange={handleImageChange}
                        id="send-img"
                    />
                    <label htmlFor='send-file' className="send-file">파일 첨부하기</label>
                    <input
                        type="file"
                        onChange={handleFileChange}

                        id='send-file'
                    />
                    <button className="send" onClick={write}>
                        작성완료
                    </button>
                </div>
            </div>
            <div className="empty"></div>
        </div>
    );
}

export default NoticeWrite;
