import React from 'react';
import Header from '../../Header';
import { Swiper, SwiperSlide } from 'swiper/react';
import { Autoplay, Pagination } from 'swiper/modules';
import { useNavigate } from 'react-router-dom';

import 'swiper/css';
import 'swiper/css/pagination';

import '../../../assets/scss/contents/homes/mainhome.scss';
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

const noticeLists = [
  { date: '24.07.29', titles: '제목이다재1' },
  { date: '24.07.29', titles: '제목이다재2' },
  { date: '24.07.29', titles: '제목이다재3' },
  { date: '24.07.29', titles: '제목이다재4' },
  { date: '24.07.29', titles: '제목이다재5' },
  { date: '24.07.29', titles: '제목이다재6' },
  { date: '24.07.29', titles: '제목이다재7' },
  { date: '24.07.29', titles: '제목이다재8' },
  { date: '24.07.29', titles: '제목이다재9' },
  { date: '24.07.29', titles: '제목이다재10'},
  { date: '24.07.29', titles: '제목이다재7' },
  { date: '24.07.29', titles: '제목이다재8' },
  { date: '24.07.29', titles: '제목이다재9' },
  { date: '24.07.29', titles: '제목이다재10'}
];

const Mainhome = ({ src, className }) => {
  const navigate = useNavigate();

  const GonoticePage = (index) => {
    navigate('/notice', { state: { selectedIndex: index } });
  };

  const GonoticeWrite = () => {
    navigate('/noticeWrites');
    window.scrollTo(0, 0);
  };

  const GonoticeOpen = () => {
    navigate('/noticeOpen');
    window.scrollTo(0, 0);
  };

  return (
    <div id="mainhome">
      <Header />
      <Swiper
        className="mainBanner"
        grabCursor={true} // 잡기 커서 활성화
        slidesPerView={'auto'}
        spaceBetween={30} // 슬라이드 간 간격
        pagination={{ clickable: true }} // 하단 동그라미
        centeredSlides={true} // 센터모드
        autoplay={{ delay: 2500 }}
        modules={[Pagination, Autoplay]}
      >
        {images.map((src, index) => (
          <SwiperSlide key={index} className="Slide" onClick={() => GonoticePage(index)}>
            <img src={src} alt={`slide-${index}`} className="imgBanner" />
          </SwiperSlide>
        ))}
      </Swiper>
      <div className="tableBox">
        <table>
          <tbody>
            {noticeLists.map((notice, index) => (
              <tr key={index}>
                <th className="tableDate">{notice.date}</th>
                <th className="tableTitle" onClick={() => GonoticeOpen(index)}>{notice.titles}</th>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <div>
        <button id="btnWrite" onClick={GonoticeWrite}>글 작성하기</button>
      </div>
    </div>
  );
}

export default Mainhome;
