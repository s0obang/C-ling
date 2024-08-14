import React, { useState } from 'react';
import PassList from './PassList';

const Recrewting = () => {
    const [selectedPlan, setSelectedPlan] = useState('2'); // 기본값으로 2차를 설정

    const getPlanOptions = () => {
        if (selectedPlan === '2') {
            return (
                <>
                    <option value="1">1차</option>
                    <option value="2">2차</option>
                </>
            );
        } else {
            return (
                <option value="1">1차</option>
            );
        }
    };
    return (
        <div className="recrew">
            <h2 className='sub-title'>리크루팅 현황</h2>
            <div className="recrew-wrap">
                <h3>현재 리크루팅 진행중인 공고 : <span>컴퓨터공학과 제 13대 학생회 부원 모집</span></h3>
                <h3>모집 일정 : <span>2030/04/05 ~ 2030/05/05 </span></h3>
                <h3>선발 계획 : <span>2차</span></h3>
            </div>
            <select className="plan"  >
                {getPlanOptions()}
            </select>
            <PassList />
            <button className="send">합/불 입력 완료</button>
            <p className="send-text">※ 합/불 입력 완료 이후  지원자에게 메일이 발송되며  합/불 수정이 불가능합니다. </p>
        </div>
    )
}

export default Recrewting
