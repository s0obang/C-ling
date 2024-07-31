import React from 'react'
import LOGO from '../../../assets/img/logo.png'
import '../../../assets/scss/contents/user/login.scss'
import { Link } from 'react-router-dom'

const Login = () => {
    return (
        <div className='login'>
            <Link to = '/' className="c-logo">
                <img src={LOGO} alt="logo" />
            </Link>
            <div className="wrap">
                <Link to = "/login/findpw" className='findpw'>비밀번호 찾기</Link>
                
                <form method="post" className='login-form'>
                    <input className="number" type="number" placeholder='학번' />
                    <input className="check" type="text" placeholder='Password' />
                    <div className="btn"><button className='signbtn' type="submit" >Sign In</button>
                        <Link to='/create' ><button className='signup'  >Sign Up</button></Link></div>
                </form>
            </div>
        </div>
    )
}

export default Login
