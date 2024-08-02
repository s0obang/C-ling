import React from 'react'
import LOGO from '../../../assets/img/logo.png'
import AddBtn from '../../../assets/img/addbtn.png'
import Ex from '../../../assets/img/eximg.png'
import '../../../assets/scss/contents/user/create.scss'
import { Link } from 'react-router-dom'

const create = () => {
    return (
        <div className='create'>
             <Link to = '/' className="c-logo">
                <img src={LOGO} alt="logo" />
            </Link>
            <div className="info">
                <div className="img">
                    <img src={Ex} alt="프로필사진" />
                    <button className='addimg'>
                        <img src={AddBtn} alt="이미지추가" />
                    </button>
                </div>
                <form method="post" className='create-form'>
                    <div className="form">
                        <input type="name" className='name' placeholder='Name' />
                        <input type="text" className='id' placeholder='Student ID' />
                        <input type="email" className='email' name="email" placeholder='E-mail' />
                        <div className="pwd">
                            <input type="password" placeholder='Password' />
                            <input type="password" placeholder='PasswordCheck' />
                        </div>
                    </div>
                    
                </form>
                

            </div>
            <button className='signbtn' type="submit" >Sign In</button>
            <h4 className='caution'>※ 특수한 역할 (학생회, 동아리 임원 등 )이 있는 경우 관리자 문의를 통해 뱃지를 드립니다.  문의 - MY 페이지 하단 버튼 이동.
               
            </h4>
        </div>
    )
}

export default create
