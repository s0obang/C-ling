import React, { useState } from 'react';
import LOGO from '../../../assets/img/logo.png';
import '../../../assets/scss/contents/user/login.scss';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';

const Login = () => {
    const [studentId, setStudentId] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = (e) => {
        e.preventDefault();

        const formData = new FormData();
        formData.append('studentId', studentId);
        formData.append('password', password);

        axios.post('http://13.48.207.238:1234/api/auth/login', formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        })
            .then(res => {
                if (res.status === 200) {
                    console.log('Login successful', res.data);
                    localStorage.setItem('accessToken', res.data.accessToken);
                    navigate('/dashboard'); // 로그인 후 이동할 페이지로 변경하세요
                } else {
                    console.error('Unexpected response status:', res.status);
                }
            })
            .catch(err => {
                console.error('Error during login:', err);
            });
    };

    return (
        <div className='login'>
            <Link to='/' className="c-logo">
                <img src={LOGO} alt="logo" />
            </Link>
            <div className="wrap">
                <Link to="/login/findpw" className='findpw'>비밀번호 찾기</Link>
                
                <form method="post" className='login-form' onSubmit={handleLogin}>
                    <input
                        className="number"
                        type="number"
                        placeholder='학번'
                        value={studentId}
                        onChange={(e) => setStudentId(e.target.value)}
                    />
                    <input
                        className="check"
                        type="password"
                        placeholder='Password'
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                    <div className="btn">
                        <button className='signbtn' type="submit">Sign In</button>
                        <Link to='/create'><button className='signup' type="button">Sign Up</button></Link>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default Login;
