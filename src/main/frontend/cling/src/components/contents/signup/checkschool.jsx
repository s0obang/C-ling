import React from 'react'
import LOGO from '../../../assets/img/logo.png'
import '../../../assets/scss/contents/create.scss'

const checkschool = () => {
    return (
        <div className='checkschool'>

            <div className="logo">
                <img src={LOGO} alt="logo" />
            </div>
            <form action="post">
                <input className="email" type="email" placeholder='학교 E-mail' />
                <input className="number" type="text" placeholder='인증번호' />
                <input className="btn" type="button" value="학교 인증하기" />
            </form>
        </div>
    )
}

export default checkschool
