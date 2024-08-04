import React, { useState } from "react";
import Header from '../../Header';
import { useLocation, useNavigate } from 'react-router-dom';
import { Swiper, SwiperSlide } from 'swiper/react';
import { Autoplay, Pagination } from 'swiper/modules';
import { IoChevronBackCircleOutline } from "react-icons/io5";
import axios from 'axios';

import 'swiper/css';
import 'swiper/css/pagination';

import '../../../assets/scss/contents/homes/noticeOpen.scss'
import image1 from '../../../assets/img/logo.png'
import image2 from '../../../assets/img/logo.png'
import image3 from '../../../assets/img/logo.png'
import image4 from '../../../assets/img/logo.png'
import image5 from '../../../assets/img/logo.png'

const images = [
    image1,
    image2,
    image3,
    image4,
    image5
];

const notice = [
    ['제목입디다', '내용1']
];

const NoticePage = ({ src, className }) => {
  const location = useLocation();
  const { selectedIndex } = location.state || { selectedIndex: 0 };
  const [currentIndex, setCurrentIndex] = useState(selectedIndex);
  const navigate = useNavigate();

{/*const [posts, setPosts] = useState([]);
  useEffect(() => {
    axios({
      method:'GET',
      url:''
    }).then(response => setPosts(response.data))
  })*/}
  
  const editNotice = () => {

  }
  const deleteNotice = () => {
    // 삭제 기능 구현. 내가 쓴 글이면 보이게
  }

  const handleSlideChange = (swiper) => {
    setCurrentIndex(swiper.activeIndex);
  }
  
  const GonoticeWrite = () => {
    navigate('/noticeWrites');
  };

  return (
    <div id="noticeOpen">
      <Header />
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
              <img src={src} alt={`image-${index}`} className="imghuman" />
            </SwiperSlide>
          ))}
        </Swiper>
        
      </div>
      <div id="contentBox">
        <div className="backBox">
          
        </div>
          <div id = "title">
          <IoChevronBackCircleOutline className="back" onClick={()=>{navigate(-1)}} />
          <span className="text1">{notice[0][0]}</span>
          </div>
        <div id="editBox">
          <span id="edit" onClick={editNotice}>수정하기</span>
          <span> / </span>
          <span id="delete" onClick={deleteNotice}>삭제하기</span>
        </div>
          <div id = "contents">
          <span className="text2">{notice[0][1]}</span>
          </div>
      </div>
      <div>
        <button id="btnWrite" onClick={GonoticeWrite}>글 작성하기</button>
      </div>
    </div>
  );
}

export default NoticePage;
