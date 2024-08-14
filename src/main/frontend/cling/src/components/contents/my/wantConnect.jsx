import React, { useState, useEffect } from "react";
import '../../../assets/scss/contents/my/wantConnect.scss';
import { Swiper, SwiperSlide } from 'swiper/react';
import { Navigation, EffectCreative, Pagination } from 'swiper/modules';
import { ImCircleLeft, ImCircleRight } from "react-icons/im";
import axios from 'axios';

import 'swiper/css';
import 'swiper/css/pagination';
import 'swiper/css/navigation';
import 'swiper/css/effect-creative';

const WantConnect = () => {
    const [wantUsers, setWantUsers] = useState([]);
    const [currentIndex, setCurrentIndex] = useState(0);
    const [images, setImages] = useState({});

    useEffect(() => {
        showWantConnect();
      }, []);
    
    const showWantConnect = () => {
      axios.get('https://clinkback.store/request', {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('accessToken')}`
        },
      })
      .then(response => {
        setWantUsers(response.data);

        response.data.forEach(user => {
            axios.get(`https://clinkback.store/profile-image/get/${user.studentId}`, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem('accessToken')}`
                },
            })
            .then(imageResponse => {
                setImages(prevImages => ({
                    ...prevImages,
                    [user.studentId]: imageResponse.data.imageByte
                }));
            })
            .catch(error => {
                console.error(`${user.studentId}의 이미지를 불러오는데 실패했습니다.:`, error);
            });
        });
    })
    .catch((error) => {
        console.error('연결을 원하는 수정이들 목록을 가져오는 중 오류가 발생했습니다.', error);
        });
    };

    const handleSlideChange = (swiper) => {
        setCurrentIndex(swiper.realIndex);
    };


    //수락
    const handleAccept =() => {
        const studentId = wantUsers[currentIndex]?.studentId;

        axios.post('https://clinkback.store/request/accept', { studentId },
            {
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${localStorage.getItem('accessToken')}`
            }
        })
        .then(response => {
            if (response.status === 200) {
                console.log(response);
                alert('연결되었습니다!');
                showWantConnect();
            }
        })
        .catch(err => {
            console.error(err);
            alert('수락 중 오류가 발생했습니다.');
        });
    }
    //거절
    const handleDecline=() => {
        const studentId = wantUsers[currentIndex]?.studentId; 

        axios.delete('https://clinkback.store/request/decline',
            {
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${localStorage.getItem('accessToken')}`
            },
            data: { studentId }
        })
        .then(response => {
            if (response.status === 200) {
                console.log(response);
                alert('거절되었습니다.');
                showWantConnect();
            }
        })
        .catch(err => {
            console.error(err);
            alert('거절 중 오류가 발생했습니다.');
        });
    }

    return (
        <div id="wantPage">
            <span id="title"> 연결을 원하는 수정이들 </span>
            {wantUsers.length === 0 ? (
                <div id="noConnections">
                    나와 연결을 원하는 수정이가 없습니다!
                </div>
            ) : (
                <>
                    <div id="box1">
                        <ImCircleLeft id="prevButton" />
                        <div id="box2">
                            <Swiper
                                className="mainBanner"
                                slidesPerView={1}
                                centeredSlides={true}
                                pagination={false}  //밑에 동그라미 끔
                                modules={[Navigation, EffectCreative, Pagination]}
                                navigation={{
                                    nextEl: '#nextButton',
                                    prevEl: '#prevButton',
                                }}
                                loop={true}
                                effect="creative"
                                creativeEffect={{
                                    prev: {
                                        shadow: true,
                                        translate: ['22%', '-20%', -300],
                                    },
                                    next: {
                                        shadow: true,
                                        translate: ['22%', '-20%', -300],
                                    },
                                }}
                                onSlideChange={handleSlideChange}
                            >
                                {wantUsers.map((user, index) => (
                                    <SwiperSlide key={user.studentId} className="Slide">
                                        <div className="image-container">
                                        <img 
                                            src={`data:image/jpeg;base64,${images[user.studentId]}`}
                                            alt={`slide-${index}`} className="imgList" />
                                        </div>
                                    </SwiperSlide>
                                ))}
                            </Swiper>
                            <div id="circle">{wantUsers.length}</div>
                        </div>
                        <ImCircleRight id="nextButton" />
                    </div>

                    <div id="yesOrNo">
                        <button className="button" onClick={handleAccept}>수락</button>
                        <button className="button" onClick={handleDecline}>거절</button>
                    </div>
                    <div id="introduce">
                        <div className="balloonBox">
                        <div className="balloon1">
                        {`${wantUsers[currentIndex].studentId} ${wantUsers[currentIndex].name} 입니다`}
                        </div>
                        </div>
                        <div className="balloon2">
                        {wantUsers[currentIndex].major}
                        </div>
                    </div>
                </>
            )}
        </div>
    );
};

export default WantConnect;
