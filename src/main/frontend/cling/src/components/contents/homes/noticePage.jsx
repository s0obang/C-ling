import React, { useState, useEffect } from "react";
import { useLocation } from 'react-router-dom';
import { Swiper, SwiperSlide } from 'swiper/react';
import { Autoplay, Pagination } from 'swiper/modules';

import 'swiper/css';
import 'swiper/css/pagination';

import '../../../assets/scss/contents/homes/noticePage.scss'
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

const notices = [
    ['제목1', '내용1'],
    ['제목2', '내용2'],
    ['제목3', '내용3'],
    ['제목4', '내용4'],
    ['제목5', '내용5']
];

const NoticePage = ({ src, className }) => {
  const location = useLocation();
  const { selectedIndex } = location.state || { selectedIndex: 0 };
  
  const [currentIndex, setCurrentIndex] = useState(selectedIndex);
  const [isEditing, setIsEditing] = useState(false);
  const [newNotice, setnewNotice] = useState([...notices[currentIndex]]);

  const editNotice = () => {
    if (isEditing) {
      const updatedNotices = [...notices];
      updatedNotices[currentIndex] = newNotice;
      // 새로운 공지사항 저장 로직 
    }
    setIsEditing(!isEditing);
  }
  
  const myNoticeChange = (index, value) => {
    const updatedNotice = [...newNotice];
    updatedNotice[index] = value;
    setnewNotice(updatedNotice);
  }

  const deleteNotice = () => {
    // 삭제 기능 구현
  }

  const handleSlideChange = (swiper) => {
    setCurrentIndex(swiper.activeIndex);
    setnewNotice([...notices[swiper.activeIndex]]);
  }

  return (
    <div id="noticepage">
      <div id="imgbox">
        <Swiper
          className="mainBanner"
          grabCursor={true} //잡기 커서 활성화
          slidesPerView={1}   
          spaceBetween={30} // 슬라이드 간 간격 
          pagination={{clickable: true}}  //하단 동그라미
          centeredSlides={true} //센터모드
          autoplay={false} 
          modules={[Pagination, Autoplay]}
          initialSlide={selectedIndex}
          onSlideChange={handleSlideChange}
        >
          {images.map((src, index) => (
            <SwiperSlide key={index} className="Slide">
              <img src={src} alt={`slide-${index}`} className="imghuman" />
            </SwiperSlide>
          ))}
        </Swiper>
      </div>
      <div id="contentBox">
        {isEditing ? (
          <form>
          <input 
            className="text1" 
            value={newNotice[0]} 
            onChange={(e) => myNoticeChange(0, e.target.value)} 
          />
          </form>
        ) : (
          <span className="text1">{notices[currentIndex][0]}</span>
        )}

        <div id="editBox">
          <span id="edit" onClick={editNotice}>{isEditing ? "저장하기" : "수정하기"}</span>
          <span> / </span>
          <span id="delete" onClick={deleteNotice}>삭제하기</span>
        </div>

        {isEditing ? (
          <form>
          <textarea 
            className="text2" 
            value={newNotice[1]} 
            onChange={(e) => myNoticeChange(1, e.target.value)} 
          />
          </form>
        ) : (
          <span className="text2">{notices[currentIndex][1]}</span>
        )}
      </div>
      <div>
        <button id="btnWrite">글 작성하기</button>
      </div>
    </div>
  );
}

export default NoticePage;
