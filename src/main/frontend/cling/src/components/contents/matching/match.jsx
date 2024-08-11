import React, { useState, useEffect } from 'react';
import Header from '../../Header';
import '../../../assets/scss/contents/matching/match.scss';
import { Link, useNavigate } from 'react-router-dom';
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import axios from 'axios';

const Match = () => {
    const [users, setUsers] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        matching();
    }, []);

    const matching = () => {
        axios.get('http://13.48.207.238:1234/matching', {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('accessToken')}`
            },
        })
            .then(response => {
                setUsers(response.data);
            })
            .catch(err => {
                console.error(err);
            });
    };

    const enterChatRoom = (user) => {
        console.log(`Bearer ${localStorage.getItem('accessToken')}`)
        axios.get(`http://13.48.207.238:1234/roomEnter?id2=${user.studentId}`, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('accessToken')}`
            },
        })
            .then(response => {
                const { roomId } = response.data;
                navigate(`/chat/${roomId}`, { state: { userInfo: user } });
            })
            .catch(err => {
                console.error('Failed to enter chat room:', err);
            });
    };

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
                breakpoint: 1024,
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
                {users.length > 0 ? (
                    <>
                        <Slider {...settings}>
                            {users.map(user => (
                                <div key={user.studentId} className="slide">
                                    <div className="profileimg">
                                        <img src={user.profileImageUrl} alt="프로필사진" />
                                    </div>
                                    <div className="slide-content">
                                        <button className="btn" onClick={() => enterChatRoom(user)}>
                                            채팅하기
                                        </button>
                                        <div className="text">
                                            <h4 className="text">{user.name}</h4>
                                            <div className="text">{user.studentId}</div><br />
                                            <div className="text">{user.major}</div>
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
                    </>
                ) : (
                    <div className="no-users">
                        나와 연결된 수정이가 없습니다.
                    </div>
                )}
            </div>

            <div>
                <Link to='/match/info'><input className="clinkbtn" type="button" value="Clink 하기" /></Link>
            </div>
        </div>
    );
}

export default Match;