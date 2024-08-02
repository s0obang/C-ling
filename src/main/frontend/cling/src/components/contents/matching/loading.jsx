import React from 'react';
import Header from '../../Header';
import '../../../assets/scss/contents/matching/no_matches.scss';
import { Link } from 'react-router-dom';

const Loading = () => {
    return (
        <div className='loading'>
            <Header />
            <div className="content">             
                <div className="circle circle1"></div>
                <div className="circle circle2"></div>
                <div className="circle circle3"></div>
                <div className="text">
                    C-link 중입니다
                </div>
            </div>
        </div>
    );
}

export default Loading;
