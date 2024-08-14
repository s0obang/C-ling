import React from 'react';
import { useNavigate } from 'react-router-dom';
import Header from '../../Header';
import '../../../assets/scss/contents/matching/match_info.scss';
import axios from 'axios';

const MatchInfo = () => {
    const navigate = useNavigate();

    const clinkSame = async (path) => {
        try {
            // 데이터 요청 및 응답 받기
            const response = await axios.get('http://13.48.207.238:1234/clink-same', {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem('accessToken')}`
                },
            });

            // 데이터가 성공적으로 받아졌을 때, 로딩 페이지로 이동
            navigate('/loading', { state: { profiles: response.data } });

            // 0.8초 후에 해당 페이지로 이동
            setTimeout(() => {
                navigate(path, { state: { profiles: response.data } });
            }, 800);

        } catch (error) {
            console.error('Error fetching data:', error);
            // 사용자에게 에러 메시지 표시
            alert('데이터를 가져오는 중 오류가 발생했습니다. 나중에 다시 시도해 주세요.');
        }
    };

    const clinkOther = async (path) => {
        try {
            // 데이터 요청 및 응답 받기
            const response = await axios.get('http://13.48.207.238:1234/clink-other', {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem('accessToken')}`
                }
            });

            // 데이터가 성공적으로 받아졌을 때, 로딩 페이지로 이동
            navigate('/loading', { state: { profiles: response.data } });

            // 0.8초 후에 해당 페이지로 이동
            setTimeout(() => {
                navigate(path, { state: { profiles: response.data } });
            }, 800);

        } catch (error) {
            console.error('Error fetching data:', error);
            // 사용자에게 에러 메시지 표시
            alert('데이터를 가져오는 중 오류가 발생했습니다. 나중에 다시 시도해 주세요.');
        }
    };

    return (
        <div className='matchinfo'>
            <Header />
            <div>
                <h1 className="text">같은 학번 수정이와 크링해보세요!</h1>
            </div>

            <div className='matching-form'>
                <input className="studentId" type="text" placeholder='Student id' />
                <input className="major" type="text" placeholder='Major' />

                <div className="button-group">
                    <input
                        className="matchingbtn"
                        type="button"
                        value="Clink"
                        onClick={() => clinkSame('/samemajor')}
                    />
                    <input
                        className="matchingbtn"
                        type="button"
                        value="타 과 Clink"
                        onClick={() => clinkOther('/othermajor')}
                    />
                </div>
            </div>
        </div>
    );
};

export default MatchInfo;
