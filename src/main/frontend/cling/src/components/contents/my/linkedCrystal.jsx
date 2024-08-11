import React, { useEffect, useState } from "react";
import '../../../assets/scss/contents/my/linkedCrystal.scss'
import BGPROFIL from '../../../assets/img/my/profil_background.png'
import axios from 'axios';

const LinkedCrystal = () => {

    const [users, setUsers] = useState([]);
    useEffect(() => {
        showLinked();
      }, []);
    
    const showLinked = () => {
      axios.get('https://clinkback.store/matching', {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('accessToken')}`
        },
      })
      .then((response) => {
        console.log(response.data);
        setUsers(response.data);
      })
      .catch((error) => {
        console.error('연결된 수정이들 목록을 가져오는 중 오류가 발생했습니다.', error);
      });
    }
    
    return (
        <div className="linkedprofil">
            <div className="profilcard">
                <div id="title">
                    <span id="username">나와 연결된 수정이들</span>
                </div>
                <div id="profillist">
                    {users.length === 0 ? (
                        <span className="linkedZero">나와 연결된 수정이가 없습니다 !</span>
                    ) : (
                        users.map((user, index) => (
                            <div className="profil" key={index}>
                                <img src={user.profileImageUrl} alt="profile" className="imghuman" />
                                <img src={BGPROFIL} alt="imgprofil" className="imgprofil" />
                                <div id="info">
                                    <div className="infotext">
                                        <span className="text1">이름</span>
                                        <span className="text2">{user.name}</span>
                                    </div>
                                    <div className="infotext">
                                        <span className="text1">학번</span>
                                        <span className="text2">{user.studentId}</span>
                                    </div>
                                    <div className="infotext">
                                        <span className="text1">학과</span>
                                        <span className="text2">{user.major}</span>
                                    </div>
                                </div>
                            </div>
                        ))
                    )}
                </div>
            </div>
        </div>
    );
};

export default LinkedCrystal;
