import React from 'react'
import Header from '../../Header'
import '../../../assets/scss/contents/matching/no_matches.scss'
import { Link } from 'react-router-dom'


const no_matches = () => {
    return (

        
        <div className='no_matches'>
            
            <Header></Header>
            <div>
                <h1 className="text">나와 연결된 수정이가 없습니다.</h1>
            </div>
            <div>

                <Link to= '/nomatches/info'><input className="btn" type="button" value="Clink 하기" /></Link>
            </div>
        </div>
    )
}

export default no_matches