import React, { useRef, useState } from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';
import { Autoplay, Pagination } from 'swiper/modules';

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
      
  return (
    <div id="mainhome">
    <Swiper
        className="mainBanner"
        grabCursor={true}
        slidesPerView={'auto'}   
        spaceBetween={30} // 슬라이드 간 간격 설정
        pagination={{clickable: true}}  //하단 동그라미
        centeredSlides={true}
        autoplay= {{delay:1000}}
        mousewheel={true} // 마우스 휠
        modules={[Pagination, Autoplay]}
        coverflowEffect={{
            rotate : 20, // 슬라이더 회전 각 : 클수록 슬라이딩시 회전이 커짐
            stretch : -100, // 슬라이더간 거리. 클수록 많이 겹침
            depth : 100, // 깊이 효과값 : 클수록 멀리있는 느낌이 강해짐
            
          }}
      >
        {images.map((src, index) => (
                <SwiperSlide key={index} className="Slide">
                    {<img src={src} alt={`slide-${index}`} className="imghuman" />}
                </SwiperSlide>
            ))}
      </Swiper>
      </div>
  );
}

export default Mainhome
