import React from 'react'
import LOGO from '../../../assets/img/logo.png'
import '../../../assets/scss/contents/user/checkschool.scss'
import { Link } from 'react-router-dom'

const checkschool = () => {
    return (
        <div className='checkschool'>

            <Link to='/' className="c-logo">
                <img src={LOGO} alt="logo" />
            </Link>
            <div method="post" className='check-form'>
                <input className="email" type="email" placeholder='학교 E-mail' />
                <input className="number" type="text" placeholder='인증번호' />
                <Link to='/create/info'><input className="btn" type="button" value="Next" /></Link>
            </div>
        </div>
    )
}

export default checkschool
