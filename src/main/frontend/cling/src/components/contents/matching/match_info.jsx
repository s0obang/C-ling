import React from 'react'
import Header from '../../Header'
import '../../../assets/scss/contents/matching/no_matches.scss'
import { Link } from 'react-router-dom'


const MatchInfo = () => {
    return (
        <div className='matchinfo'>
            <Header></Header>
            <div>
                <h1 className="text">같은 학번 수정이와 크링해보세요!</h1>
            </div>

            <form method="post" className='matching-form'>
                <input className="studentId" type="text" placeholder='Student id' />
                <input className="major" type="text" placeholder='Major' />

                <div className="button-group">
                    <Link to='/samemajor'>
                        <input className="matchingbtn" type="button" value="Clink" />
                    </Link>
                    <Link to='/othermajor'>
                        <input className="matchingbtn" type="button" value="타 과 Clink" />
                    </Link>
                </div>
            </form>
        </div>
    )
}

export default MatchInfo