import React, { useEffect } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import NoticeWrite from './NoticeWrite';

const NoticeList = () => {
    const fetch = () => {
        axios.get('https://clinkback.store/application/my', {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('accessToken')}`
            }
        })
            .then(res => {
                if (res.status === 200) {
                    console.log(res.data);
             
                }
            })
            .catch(err => {
                console.error(err);
            });
    };

    useEffect(() => {
        fetch();
    }, []);
    const navigate = useNavigate();
    const write = () =>{
        NoticeWrite()
    }
    
  return (
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
                      
                    </div>
                    <div className='towrite'>글 작성</div>
                </div>
  )
}

export default NoticeList
