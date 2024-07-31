import React from 'react'
import Header from '../../Header'
import '../../../assets/scss/contents/matching/no_matches.scss'
import { Link } from 'react-router-dom'


const loading = () => {
    return (
        <div className='loading'>
            
            <Header></Header>
            <div>
                <h1 className="text1">C-link 중입니다</h1>
            </div>
            <div>Loading</div>;
        </div>
    )
}

export default loading