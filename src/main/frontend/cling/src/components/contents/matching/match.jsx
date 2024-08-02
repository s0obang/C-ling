// Match.js
import React from 'react';
import Header from '../../Header';
import '../../../assets/scss/contents/matching/match.scss';
import Ex from '../../../assets/img/eximg.png';
import { Link } from 'react-router-dom';
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

const users = [
    { id: 1, name: 'User1' },
    { id: 2, name: 'User2' },
    { id: 3, name: 'User3' },
    { id: 4, name: 'User4' },
    { id: 5, name: 'User5' },
    { id: 6, name: 'User6' },
    { id: 7, name: 'User7' },
    { id: 8, name: 'User8' },
    { id: 9, name: 'User9' },
    { id: 10, name: 'User10' },
    { id: 11, name: 'User11' },
    { id: 12, name: 'User12' },
];

const Match = () => {
    // Slider의 설정 옵션
    const settings = {
        dots: false,
        
        arrows: false,
        infinite: true, // 무한 슬라이드 여부
        speed: 500, // 슬라이드 속도
        autoplay: true,
        slidesToShow: 4, // 한 화면에 보여줄 슬라이드 수
        slidesToScroll: 3, // 한번에 스크롤할 슬라이드 수
        responsive: [
            {
                breakpoint: 1024, // 화면 크기가 1024px 이하일 때
                settings: {
                    slidesToShow: 3, // 보여줄 슬라이드 수를 3개로 설정
                }
            },
            {
                breakpoint: 768, // 화면 크기가 768px 이하일 때
                settings: {
                    slidesToShow: 2, // 보여줄 슬라이드 수를 2개로 설정
                }
            },
            {
                breakpoint: 480, // 화면 크기가 480px 이하일 때
                settings: {
                    slidesToShow: 1, // 보여줄 슬라이드 수를 1개로 설정
                }
            }
        ]
    };

    const currentUserId = 'currentUserId'; // 이 부분을 로그인된 사용자 ID로 대체

    return (
        <div className='match'>
            <Header />
            <Slider {...settings}>
                {users.map(user => (
                    <div key={user.id} className="slide">
                        <div className="profileimg">
                            <img src={Ex} alt="프로필사진" />
                        </div>
                        <div className="slide-content">
                            <Link to={`/chat/${currentUserId}/${user.id}`}>
                                <input className="btn" type="button" value={`${user.name}와 채팅하기`} />
                            </Link>
                            <div className="text">
                                <h4 className="text">사쿠야</h4>
                                <div className="text">20231138</div><br />
                                <div className="text">제과제빵학과</div>
                            </div>
                        </div>
                        
                    </div>
                ))}
            </Slider>

            <div className='bigtext'>
                다시 C-link 하기 <br></br>
            </div>

            <div className='smalltext'>
            나를 기다리는 다른 수정이들과 C-link해보세요! <br></br>
            </div>

            <div>
                <Link to= '/nomatches/info'><input className="clinkbtn" type="button" value="Clink 하기" /></Link>
            </div>
        </div>
    );
}

export default Match;
