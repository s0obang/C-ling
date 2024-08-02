import React, { useState } from 'react'
import LOGO from '../../../assets/img/logo.png'
import '../../../assets/scss/contents/user/checkschool.scss'
import { Link, useNavigate } from 'react-router-dom'
import axios from 'axios'

const CheckSchool = () => {
    const [email, setEmail] = useState('');
    const navigate = useNavigate();

    const checkSubmit = () => {
        axios.post('http://13.48.207.238:1234//mailSend/', {
            email: email
        })
            .then((res) => {
                if (res.status === 200) {
                    const accessToken = res.data.access;
                    localStorage.setItem('accessToken', accessToken);
                    navigate('/create/info');
                    alert('메일로 인증번호가 전송되었습니다');
                    console.log(res);
                }
            })
            .catch((err) => {
                console.log(err);
            });
    }

    return (
        <div className='checkschool'>
            <Link to='/' className="c-logo">
                <img src={LOGO} alt="logo" />
            </Link>
            <div className='check-form'>
                <div className="email-check">
                    <input
                        className="email"
                        type="email"
                        placeholder='학교 E-mail'
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                    <div className="send-email" onClick={checkSubmit}>인증번호</div>
                </div>

                <input className="number" type="text" placeholder='인증번호' />
                <div className="btn" type="button" >학교 인증하기</div>
            </div>
        </div>
    );
}

export default CheckSchool;
