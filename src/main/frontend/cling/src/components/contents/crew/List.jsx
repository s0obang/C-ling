import React from 'react'
import Folder from '../../../assets/img/crew/Folder.png';
import axios from 'axios'

const List = () => {
    const listDown = () => {

        axios.get('https://clinkback.store/downloadStudentList', {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
                'Content-Type': 'text/plain'
            },
            responseType: 'blob',
            params: {
                recruitingDepartment: 'cs',
                step: '2'
            }
        })
            .then(res => {
                if (res.status === 200) {
                    console.log(res);
                    const url = window.URL.createObjectURL(new Blob([res.data], { type: 'text/plain' }));
                    const link = document.createElement('a');
                    link.href = url;
                    link.setAttribute('download', 'student_list.txt');
                    document.body.appendChild(link);
                    link.click();
                    document.body.removeChild(link);
                }
            })
            .catch(err => {
                console.error(err);

            });
    };
    return (
        <div className="list-down">
            <h2 className="sub-title">최종 합격자 명단 다운로드</h2>
            <div className="file">
                <img src={Folder} alt="Folder" />
                <div className="filename" onClick={listDown}>컴퓨터공학과 제 13대 학생회 부원을 모집합니다.</div>
            </div>
        </div>

    )
}

export default List
