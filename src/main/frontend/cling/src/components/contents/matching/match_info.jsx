import React from 'react'
import Header from '../../Header'
import '../../../assets/scss/contents/matching/no_matches.scss'
import { Link } from 'react-router-dom'


const input_info = () => {
    return (
     
        <div className='input_info'>
            
            <Header></Header>

            <div>

                <h1 className="text1">같은 학번 수정이와 크링해보세요!</h1>
            </div>

            <form method="post" className='matching-form'>
                <input className="studentId" type="text" placeholder='Student id' />
                <input className="major" type="text" placeholder='Major' />

                <div className="button-group">
                    <Link to='/create/info'>
                        <input className="matchingbtn" type="button" value="Clink" />
                    </Link>
                    <Link to='/create/info'>
                        <input className="matchingbtn" type="button" value="타 과 Clink" />
                    </Link>
                </div>
            </form>
        </div>
    )
}

export default input_info