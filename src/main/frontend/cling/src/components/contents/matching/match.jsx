// Match.js
import React, { useState } from 'react';
import Header from '../../Header';
import '../../../assets/scss/contents/matching/match.scss';
import Ex from '../../../assets/img/eximg.png';
import { Link } from 'react-router-dom';
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import axios from 'axios';

const Match = () => {
    const [users, setUsers] = useState([]);

    const maching = () => {
        axios.get('http://13.48.207.238:1234/matching', {})

        .then(response => {
            setUsers(response.data);
            console.log(response);
          })
          .catch(err => {
            console.log(err);
          });
    }

    const settings = {
        dots: false,
        
        arrows: false,
        infinite: true,
        speed: 500,
        autoplay: true,
        slidesToShow: 4,
        slidesToScroll: 3,
        responsive: [
            {
                breakpoint: 1024, // 1024 이하
                settings: {
                    slidesToShow: 3,
                }
            },
            {
                breakpoint: 768,
                settings: {
                    slidesToShow: 2,
                }
            },
            {
                breakpoint: 480,
                settings: {
                    slidesToShow: 1,
                }
            }
        ]
    };


    return (
        <div className='match'>
            <Header />
            <div className='content'>
                <Slider {...settings}>
                    {users.map(user => (
                        <div key={user.studentId} className="slide">
                            <div className="profileimg">
                                <img src={user.profileImageUrl} alt="프로필사진" />
                            </div>
                            <div className="slide-content">
                                <Link to={`/chat/${user.studentId}`}>
                                    <input className="btn" type="button" value={'채팅하기'} />
                                </Link>
                                <div className="text">
                                    <h4 className="text">{user.name}</h4>
                                    <div className="text">{user.studentId}</div><br />
                                    <div className="text">{user.major}</div>
                                </div>
                            </div>
                            
                        </div>
                    ))}
                </Slider>

            </div>

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
