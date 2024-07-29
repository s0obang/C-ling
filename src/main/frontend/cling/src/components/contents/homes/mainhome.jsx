import React, { useRef, useState } from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';
import { Autoplay, Pagination } from 'swiper/modules';
import { useNavigate } from 'react-router-dom';

import 'swiper/css';
import 'swiper/css/pagination';

import '../../../assets/scss/contents/homes/mainhome.scss'
import image1 from '../../../assets/img/image1.png'
import image2 from '../../../assets/img/image2.png'
import image3 from '../../../assets/img/image3.png'
import image4 from '../../../assets/img/image4.png'
import image5 from '../../../assets/img/image5.png'

const images = [
    image1,
    image2,
    image3,
    image4,
    image5
  ];

const Mainhome = ({ src, className })=>{
  const navigate = useNavigate();

  const GonoticePage = (index) => {
      navigate('/notice', { state: { selectedIndex: index } });
  }

  return (
    <div id="mainhome">
      <Swiper
        className="mainBanner"
        grabCursor={true} //잡기 커서 활성화
        slidesPerView={'auto'}   
        spaceBetween={30} // 슬라이드 간 간격 
        pagination={{clickable: true}}  //하단 동그라미
        centeredSlides={true} //센터모드
        autoplay= {{delay:1000}}  
        modules={[Pagination, Autoplay]}
      >
        {images.map((src, index) => (
          <SwiperSlide key={index} className="Slide" onClick={() => GonoticePage(index)}>
            <img src={src} alt={`slide-${index}`} className="imghuman" />
          </SwiperSlide>
        ))}
      </Swiper>
    </div>
  );
}

export default Mainhome;
