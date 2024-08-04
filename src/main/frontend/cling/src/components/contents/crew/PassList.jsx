import React from 'react';
import PassListItem from './PassListItem';
import '../../../assets/scss/contents/crew/passlist.scss';

const PassList = () => {
    const students = [
        { studentId: '20231168', name: '최수진', fileName: '파일명1' },
        { studentId: '20231169', name: '홍길동', fileName: '파일명2' }
        
    ];

    return (
        <div className='passlist'>
            <div className="table-title">
                <div className='passlist-item'>학번/이름</div>
                <div className='passlist-item'>첨부파일</div>
                <div className='passlist-item'>합/불</div>
            </div>
            {students.map((student, index) => (
                <PassListItem
                    key={index}
                    studentId={student.studentId}
                    name={student.name}
                    fileName={student.fileName}
                />
            ))}
           
        </div>
    );
};

export default PassList;
