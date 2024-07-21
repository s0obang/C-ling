import React from 'react'
import Header from '../../Header'
import '../../../assets/scss/contents/matching/no_matches.scss'
import { Link } from 'react-router-dom'
import AddBtn from '../../../assets/img/addbtn.png'
import Ex from '../../../assets/img/eximg.png'

const check_othermajor = () => {
    return (

        
        <div className='check_othermajor'>
            
            <Header></Header>
            <div>
                <h1 className="text1">크링된 다른 과 수정이를 확인해보세요</h1>
            </div>
            <div className="img">
                    <img src={Ex} alt="프로필사진" />
            </div>

        </div>
    )
}

export default check_othermajor