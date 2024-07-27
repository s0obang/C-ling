import React from 'react'
import '../assets/scss/section/landing.scss'
import Header from './Header'
import Landing1 from '../assets/img/landing1.png'
import Landing2 from '../assets/img/landing2.png'
import Landing3 from '../assets/img/landing3.png'
import Landing4 from '../assets/img/landing4.png'
import bubble1 from '../assets/img/bubble1.png'
import bubble2 from '../assets/img/bubble2.png'
import bubble3 from '../assets/img/bubble3.png'
import bubble4 from '../assets/img/bubble4.png'
import bubble5 from '../assets/img/bubble5.png'
import bubble6 from '../assets/img/bubble6.png'
import signBtn from '../assets/img/signupBtn.png'
import RingImg from '../assets/img/ring.png'
import { Link } from 'react-router-dom'

const landing = () => {
    return (
        <div className='landing'>
            <Header></Header>
            <div className="wrap">
                <section className='top'>
                    <img className='ringImg' src={RingImg} alt="연결고리" />
                    <img className='top-text' src={Landing1} alt="랜딩1" />
                </section>
                <section className='mid'>
                    <h1 className='mid-text1'>나와 같은 학과 선배, 후배, 동기들과의<br />
                        네트워킹은 물론!
                    </h1>
                    <img className='bubble1' src={Landing2} alt="예시1" />
                    <img className='bubble2' src={Landing3} alt="예시2" />
                    <img className='bubble3' src={Landing4} alt="예시3" />
                    <h1 className='mid-text2'>다른 학과에 궁금한 이야기들도<br />
                        크링에서 물어봐!
                    </h1>
                </section>
                <section className='bot'>
                    <div className='bubble'>
                        <img src={bubble1} alt="bubble1"  className='b1'/>
                        <img src={bubble2} alt="bubble2" className='b2'/>
                        <img src={bubble3} alt="bubble3" className='b3'/>
                        <img src={bubble4} alt="bubble4" className='b4'/>
                        <img src={bubble5} alt="bubble5" className='b5'/>
                        <img src={bubble6} alt="bubble6" className='b6'/>

                    </div>
                    <Link><img src={signBtn} alt="signBtn" className='signBtn' /></Link>
                    
                </section>
            </div>
        </div>
    )
}

export default landing
