import React, { useState } from "react";
import axios from 'axios';

const Position = () => {
    const [name, setName] = useState('');
    const [studentId, setStudentId] = useState('');
    const [position, setPosition] = useState('');
    const [file, setFile] = useState(null);

    const handleSubmit = (e) => {
        e.preventDefault();

        const formData = new FormData();
        formData.append('name', name);
        formData.append('studentId', studentId);
        formData.append('position', position);
        if (file) {
            formData.append('file', file);
        }

        axios.post('https://clinkback.store/api/position/request', formData, {
            headers: {
                'Content-Type': 'multipart/form-data',
                Authorization: `Bearer ${localStorage.getItem('accessToken')}`
            }
        })
            .then(res => {
                if (res.status === 200) {
                    console.log(res);
                }
            })
            .catch(err => {
                console.error(err);
            });
    };

    const handleFileChange = (e) => {
        setFile(e.target.files[0]);
    };

    return (
        <div className="modaldiv">
            <form onSubmit={handleSubmit} className="form">
                <div className='formcolumn'>
                    <span className="formtext">이름</span>
                    <input
                        className="input"
                        type="text"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        required
                    />
                </div>

                <div className='formcolumn'>
                    <span className="formtext">학번</span>
                    <input
                        className="input"
                        type="text"
                        value={studentId}
                        onChange={(e) => setStudentId(e.target.value)}
                        required
                    />
                </div>
                <div className='formcolumn'>
                    <span className="formtext">직책</span>
                    <input
                        className="input"
                        type="text"
                        value={position}
                        onChange={(e) => setPosition(e.target.value)}
                        required
                    />
                </div>

                <div className='fileform'>
                    <label htmlFor="file">인증 이미지 선택</label>
                    <input
                        type="file"
                        id="file"
                        onChange={handleFileChange}
                        required
                    />
                    {file && <span className="filename">{file.name}</span>}
                </div>

                <div className="submitBox">
                    <button type="submit" className="btnSubmit">전송</button>
                </div>
            </form>
        </div>
    );
}

export default Position;
