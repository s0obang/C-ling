import React from 'react';
import { useNavigate } from 'react-router-dom';
import Header from '../../Header';
import '../../../assets/scss/contents/matching/match_info.scss';
import axios from 'axios';

const MatchInfo = () => {
    const navigate = useNavigate();

    const clinkSame = async (path) => {
        try {
            const response = await axios.get('https://clinkback.store/clink-same', {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem('accessToken')}`
                },
            });

            navigate('/loading', { state: { profiles: response.data } });

            setTimeout(() => {
                navigate(path, { state: { profiles: response.data } });
            }, 800);

        } catch (error) {
            console.error('Error fetching data:', error);
            alert('데이터를 가져오는 중 오류가 발생했습니다. 나중에 다시 시도해 주세요.');
        }
    };

    const clinkOther = async (path) => {
        try {
            const response = await axios.get('https://clinkback.store/clink-other', {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem('accessToken')}`
                }
            });

            navigate('/loading', { state: { profiles: response.data } });

            setTimeout(() => {
                navigate(path, { state: { profiles: response.data } });
            }, 800);

        } catch (error) {
            console.error('Error fetching data:', error);
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
