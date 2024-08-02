import React, { useState } from "react";
import '../../../assets/scss/contents/my/wantConnect.scss';
import { Swiper, SwiperSlide } from 'swiper/react';
import { Navigation, EffectCreative, Pagination } from 'swiper/modules';
import { ImCircleLeft, ImCircleRight } from "react-icons/im";
import BGCHAT from '../../../assets/img/chat_img.png';

import 'swiper/css';
import 'swiper/css/pagination';
import 'swiper/css/navigation';
import 'swiper/css/effect-creative';

import image1 from '../../../assets/img/image1.png';
import image2 from '../../../assets/img/image2.png';
import image3 from '../../../assets/img/image3.png';
import image4 from '../../../assets/img/image4.png';
import image5 from '../../../assets/img/image5.png';

const WantConnect = () => {

    const wantlist = [
        {
            image: image1,
            username: '김준희',
            userkey: '20231133',
            usermajor: '컴퓨터공학과'
        },
        {
            image: image2,
            username: '최수진',
            userkey: '20212233',
            usermajor: '미디어컴퓨터학과'
        },
        {
            image: image3,
            username: '떵개',
            userkey: '20289133',
            usermajor: '서양화과'
        },
        {
            image: image4,
            username: '이예진',
            userkey: '20215233',
            usermajor: '게임학과'
        },
        {
            image: image5,
            username: '강동원',
            userkey: '19874514',
            usermajor: '기계공학과'
        },
    ];

    const [currentIndex, setCurrentIndex] = useState(0);

    const handleSlideChange = (swiper) => {
        setCurrentIndex(swiper.realIndex);
    };

    return (
        <div id="wantPage">
            <span id="title"> 연결을 원하는 수정이들 </span>
            {wantlist.length === 0 ? (
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
                                {wantlist.map((item, index) => (
                                    <SwiperSlide key={index} className="Slide">
                                        <div className="image-container">
                                            <img src={item.image} alt={`slide-${index}`} className="imgList" />
                                        </div>
                                    </SwiperSlide>
                                ))}
                            </Swiper>
                            <div id="circle">{wantlist.length}</div>
                        </div>
                        <ImCircleRight id="nextButton" />
                    </div>

                    <div id="yesOrNo">
                        <button className="button">수락</button>
                        <button className="button">거절</button>
                    </div>
                    <div id="introduce">
                        <div className="balloonBox">
                        <div className="balloon1">
                            {`${wantlist[currentIndex].userkey} ${wantlist[currentIndex].username} 입니다`}
                        </div>
                        </div>
                        <div className="balloon2">
                        {wantlist[currentIndex].usermajor}
                        </div>
                    </div>
                </>
            )}
        </div>
    );
};

export default WantConnect;
