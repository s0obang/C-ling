import React, { useEffect, useState } from 'react';
import Header from '../../Header';
import { Swiper, SwiperSlide } from 'swiper/react';
import { Autoplay, Pagination } from 'swiper/modules';
import { useNavigate } from 'react-router-dom';
import { IoIosArrowDown } from "react-icons/io";

import 'swiper/css';
import 'swiper/css/pagination';

import '../../../assets/scss/contents/homes/mainhome.scss';
import axios from 'axios';

const Mainhome = () => {
  const navigate = useNavigate();
  const [noticeAll, setNoticeAll] = useState([]);
  const [showMyNotices, setShowMyNotices] = useState(false);
  const sortedNotices = [...noticeAll].reverse();
  const latestNotices = sortedNotices.slice(0, 5);

  useEffect(() => {
    allNotice();
  }, []);

  const allNotice = () => {
    axios.get('https://clinkback.store/notice', {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`
      },
    })
    .then((response) => {
      setNoticeAll(response.data);
      console.log(response.data);
    })
    .catch((error) => {
      console.error('HOME 화면의 데이터를 불러오는 데 실패했습니다.', error);
      alert('HOME 화면의 데이터를 불러오는 데 실패했습니다.');
    });
  }

  // 내 글만 보기 
  const handlemynotice = (e) => {
    if (showMyNotices) {
      allNotice();
    } else {
      axios.get('https://clinkback.store/notice/my', {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('accessToken')}`
        },
      })
      .then((response) => {
        setNoticeAll(response.data);
        console.log('내 글 목록:', response.data);
      })
      .catch((error) => {
        console.error('내 글 목록을 찾을 수 없습니다.', error);
        alert('내 글 목록을 찾을 수 없습니다.');
      });
    }
    setShowMyNotices(!showMyNotices);
  };

  const goNoticeWrite = () => {
    navigate('/noticeWrites');
    window.scrollTo(0, 0);
  };

  const goNoticeOpen = (id) => {
    console.log(`공지사항 상세보기 페이지로 이동합니다. ID: ${id}`);
    navigate(`/noticeOpen/${id}`);
    window.scrollTo(0, 0);
  };

  return (
    <div id="mainhome">
      <Header />
      <Swiper
        className="mainBanner"
        grabCursor={true}
        slidesPerView={'auto'}
        spaceBetween={170}
        pagination={{ clickable: true }}
        centeredSlides={true}
        autoplay={{ delay: 2500 }}
        modules={[Pagination, Autoplay]}
      >
        {latestNotices.map((notice, index) => {
          
          const firstImage = notice.images[0] ;
          const imageSrc = `data:image/jpg;base64,${firstImage.attachmentByteList}`;


          return (
            <SwiperSlide key={index} className="Slide" onClick={() => goNoticeOpen(notice.id)}>
              <img src={imageSrc} alt={`slide-${index}`} className="imgBanner" />
              <div className="mainTitle">
                <div>{notice.title}</div>
              </div>
            </SwiperSlide>
          );
        })}
      </Swiper>
      <IoIosArrowDown className='downIcon' />
      <div className='downIconBG' />
      <div className="myNoticeBox">
        <div className="myNotice" onClick={handlemynotice}>
          {showMyNotices ? '전체 글 보기' : '내 글만 보기'}
        </div>
      </div>
      <div className="tableBox">
        {sortedNotices.map((notice, index) => (
          <div key={index} className="noticeItem">
            <div id="notices" onClick={() => goNoticeOpen(notice.id)}>
              <div className="noticeDate">{notice.createdDate}</div>
              <div className="noticeTitle">{notice.title}</div>
            </div>
          </div>
        ))}
      </div>
      <div>
        <button id="btnWrite" onClick={goNoticeWrite}>글 작성하기</button>
      </div>
    </div>
  );
}

export default Mainhome;
