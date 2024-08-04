import React from 'react'
import { useNavigate } from 'react-router-dom'
import Header from '../../Header'
import '../../../assets/scss/contents/matching/match_info.scss'

const MatchInfo = () => {
    const navigate = useNavigate();

    const handleNavigation = (path) => {
        navigate('/loading');

        setTimeout(() => {
            navigate(path);
        }, 2000);
    };

    return (
        <div className='matchinfo'>
            <Header />
            <div>
                <h1 className="text">같은 학번 수정이와 크링해보세요!</h1>
            </div>

            <form method="post" className='matching-form'>
                <input className="studentId" type="text" placeholder='Student id' />
                <input className="major" type="text" placeholder='Major' />

                <div className="button-group">
                    <input 
                        className="matchingbtn" 
                        type="button" 
                        value="Clink" 
                        onClick={() => handleNavigation('/samemajor')}
                    />
                    <input 
                        className="matchingbtn" 
                        type="button" 
                        value="타 과 Clink" 
                        onClick={() => handleNavigation('/othermajor')}
                    />
                </div>
            </form>
        </div>
    )
}

export default MatchInfo;
