import React from 'react';
import '../../../assets/scss/contents/crew/passlist.scss';

const PassList = () => {
    return (
        <div className='passlist'>
            <div className="table-title">
                <div className='passlist-item'>학번/이름</div>
                <div className='passlist-item'>첨부파일</div>
                <div className='passlist-item'>합/불</div>
            </div>
            <div className="row">
                <div className='passlist-item'></div>
                <div className='passlist-item'></div>
                <div className='passlist-item'>
                    <button className='select-pass'>미정</button>
                </div>
            </div>
        </div>
    )
}

export default PassList;
