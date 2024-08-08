import React from 'react';
import { useNavigate } from 'react-router-dom';
import Header from '../../Header';
import '../../../assets/scss/contents/matching/match_info.scss';
import axios from 'axios';

const MatchInfo = () => {
    const navigate = useNavigate();

    const handleNavigation = async (path) => {
        try {
            // 로딩 페이지로 이동
            navigate('/loading');
            
            // 데이터 요청 및 응답 받기
            const response = await axios.get('http://13.48.207.238:1234/clink-same');
            
            // 데이터가 성공적으로 받아졌을 때, 해당 페이지로 이동
            navigate(path, { state: { profiles: response.data } });
        } catch (error) {
            // 오류가 발생한 경우
            console.error('Error fetching data:', error);
            // 사용자에게 에러 메시지 표시하거나 에러 처리 로직 추가 가능
        }
    };

    return (
        <div className='matchinfo'>
            <Header />
            <div>
                <h1 className="text">같은 학번 수정이와 크링해보세요!</h1>
            </div>

            <form method="get" className='matching-form'>
                <div className="button-group">
                    <input
                        className="matchingbtn"
                        type="button"
                        value="Clink"
                        onClick={() => handleNavigation('/samemajor')}
                    />
                    <input
                        className="matchingbtn"
                        type="button"
                        value="타 과 Clink"
                        onClick={() => handleNavigation('/othermajor')}
                    />
                </div>
            </form>
        </div>
    );
};

export default MatchInfo;
