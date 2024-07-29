import React from 'react'
import '../../../assets/scss/contents/user/findpw.scss'
import LOGO from '../../../assets/img/logo.png'
import { Link } from 'react-router-dom'


const Findpw = () => {
  return (
    <div className='findpw'>
      <div className="popup">
        <div className="alert">
          <h1>E-mail로 인증번호가 발송되었습니다.</h1>
        </div>
      </div>
      <div className="empty"></div>
      <Link to='/' className="c-logo">
        <img src={LOGO} alt="logo" />
      </Link>

      <div method="post" className='wrap'>
        <div className="email">
          <input type="email" placeholder='E-mail' />
          <button className='push'>인증번호</button>
        </div>
        <input className="name" type="text" placeholder='Name' />
        <div className="checknumber">
          <input type="text" placeholder='인증번호' />
          <button className='check'>인증확인</button>
        </div>
      </div>

    </div>
  )
}

export default Findpw
