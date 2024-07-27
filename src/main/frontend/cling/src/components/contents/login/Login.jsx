import React from 'react'
import LOGO from '../../../assets/img/logo.png'
import '../../../assets/scss/contents/user/login.scss'
import { Link } from 'react-router-dom'

const Login = () => {
    return (
        <div className='login'>
            <div className="c-logo">
                <img src={LOGO} alt="logo" />
            </div>
            <div className="wrap">
                <Link to = "" className='findpw'>비밀번호 찾기</Link>
                
                <form method="post" className='login-form'>
                    <input className="email" type="email" placeholder='학교 E-mail' />
                    <input className="number" type="text" placeholder='인증번호' />
                    <div className="btn"><button className='signbtn' type="submit" >Sign In</button>
                        <Link to='/create' ><button className='signup'  >Sign Up</button></Link></div>
                </form>
            </div>
        </div>
    )
}

export default Login
