import React from 'react';
import Header from '../../Header';
import '../../../assets/scss/contents/matching/no_matches.scss';
import Ex from '../../../assets/img/eximg.png';
import { Link } from 'react-router-dom';

// 가상의 사용자 데이터를 배열로 정의
const users = [
    { id: 1, name: 'User1' },
    { id: 2, name: 'User2' },
    { id: 3, name: 'User3' },
    { id: 4, name: 'User4' },
];

const match = () => {
    return (
        <div className='match'>
            <Header />

            <div>
                <h1 className="text1">같은 학번 수정이와 크링해보세요!</h1>
            </div>

            {users.map(user => (
                <div key={user.id}>
                    <div className="img">
                        <img src={Ex} alt="프로필사진" />
                    </div>
                    <div>
                        <Link to={`/chat/${user.id}`}>
                            <input className="btn1" type="button" value={`${user.name}와 채팅하기`} />
                        </Link>
                    </div>
                </div>
            ))}
        </div>
    );
}

export default match;
