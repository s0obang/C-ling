import React from 'react'
import LOGO from '../../../assets/img/logo.png'
import '../../../assets/scss/contents/checkschool.scss'
import { Link } from 'react-router-dom'

const checkschool = () => {
    return (
        <div className='checkschool'>

            <div className="c-logo">
                <img src={LOGO} alt="logo" />
            </div>
            <form method="post" className='check-form'>
                <input className="email" type="email" placeholder='학교 E-mail' />
                <input className="number" type="text" placeholder='인증번호' />
                <Link to= '/create/info'><input className="btn" type="button" value="학교 인증하기" /></Link>
            </form>
        </div>
    )
}

export default checkschool
