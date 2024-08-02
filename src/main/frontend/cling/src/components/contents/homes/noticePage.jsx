import React, { useState } from "react";
import Header from '../../Header';
import { useLocation, useNavigate } from 'react-router-dom';
import { Swiper, SwiperSlide } from 'swiper/react';
import { Autoplay, Pagination } from 'swiper/modules';
import { IoChevronBackCircleOutline } from "react-icons/io5";
import axios from 'axios';

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
    ['제목 엄~~~~~~~~~~~~~~~~~~~~~~~~~~~청 길다', '내용1'],
    ['제목2', '내용2'],
    ['제목3', '내용3'],
    ['제목4', '내용4'],
    ['제목5', '내용5']
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
  
  const deleteNotice = () => {
    // 삭제 기능 구현. 내가 쓴 글이면 보이게
  }

  const handleSlideChange = (swiper) => {
    setCurrentIndex(swiper.activeIndex);
  }
  

  return (
    <div id="noticepage">
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
              <img src={src} alt={`slide-${index}`} className="imghuman" />
            </SwiperSlide>
          ))}
        </Swiper>
        
      </div>
      <div id="contentBox">
        <div className="backBox">
          
        </div>
          <div id = "title">
          <IoChevronBackCircleOutline className="back" onClick={()=>{navigate(-1)}} />
          <span className="text1">{notices[currentIndex][0]}</span>
          </div>
        <div id="editBox">
          <span id="delete" onClick={deleteNotice}>삭제하기</span>
        </div>
          <div id = "contents">
          <span className="text2">{notices[currentIndex][1]}</span>
          </div>
      </div>
      <div>
        <button id="btnWrite">글 작성하기</button>
      </div>
    </div>
  );
}

export default NoticePage;
