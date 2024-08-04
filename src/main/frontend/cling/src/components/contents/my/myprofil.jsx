import React, { useState, useRef } from "react";
import '../../../assets/scss/contents/my/myprofil.scss';
import BGPROFIL from '../../../assets/img/my/profil_background.png';
import IMG_PROFIL from '../../../assets/img/logo.png';

import OFF from '../../../assets/img/my/btnoff.png';
import ON from '../../../assets/img/my/btnon.png';
import Select from "react-select"; 

import cancle from '../../../assets/img/my/cancle.png';
import styled from "styled-components";


const FileInputWrapper = styled.div`
  .fileInput {
    display: none;
  }

  .customFileUpload {
    border: 1px solid #ccc;
    display: inline-block;
    padding: 6px 12px;
    cursor: pointer;
  }

  .fileName {
    margin-left: 10px;
  }
`;
const Myprofil = () => {
    const [mailAlarm, setMailAlarm] = useState(true);
    const [connectionAlarm, setConnectionAlarm] = useState(true);
    const [myinfo, setMyinfo] = useState(['김준희', '20231133', '컴퓨터공학과']);
    const myBadge = ['컴퓨터공학과 아무개','컴퓨터공학과 아무개'];
    const [isEditing, setIsEditing] = useState(false);
    const [newInfo, setNewInfo] = useState([...myinfo]);
    const imageInput = useRef();
    const [fileName, setFileName] = useState('');
    const [badgeModal, setBadgeModal] = useState(false);
    const [delModal, setDelModal] = useState(false);

    // 버튼클릭시 input태그에 클릭이벤트를 걸어준다. 
    const onClickImageUpload = () => {
        imageInput.current.click();
    };

    const handleFileChange = (e) => {
        const file = e.target.files[0];
        if (file) {
            setFileName(file.name);
        } else {
            setFileName('');
        }
    };
    const badge = () => {
        setBadgeModal(!badgeModal);
    }
    const delBadge = () => {
        setDelModal(!delModal);
    }

    const majorLists = [
        { value: '국어국문학과', label: '국어국문학과' },
        { value: '영어영문학과', label: '영어영문학과' },
        { value: '독일어문·문화학과', label: '독일어문·문화학과' },
        { value: '프랑스어문·문화학과', label: '프랑스어문·문화학과' },
        { value: '일본어문·문화학과', label: '일본어문·문화학과' },
        { value: '중국어문·문화학과', label: '중국어문·문화학과' },
        { value: '사학과', label: '사학과' },
        { value: '문화예술경영학과', label: '문화예술경영학과' },
        { value: '정치외교학과', label: '정치외교학과' },
        { value: '심리학과', label: '심리학과' },
        { value: '지리학과', label: '지리학과' },
        { value: '경제학과', label: '경제학과' },
        { value: '미디어커뮤니케이션학과', label: '미디어커뮤니케이션학과' },
        { value: '경영학부', label: '경영학부' },
        { value: '사회복지학과', label: '사회복지학과' },
        { value: '법학부', label: '법학부' },
        { value: '의류산업학과', label: '의류산업학과' },
        { value: '소비자생활문화산업학과', label: '소비자생활문화산업학과' },
        { value: '뷰티산업학과', label: '뷰티산업학과' },
        { value: '수리통계데이터사이언스학부', label: '수리통계데이터사이언스학부' },
        { value: '통계학과', label: '통계학과' },
        { value: '생명과학·화학부', label: '생명과학·화학부' },
        { value: '법학부', label: '법학부' },
        { value: '화학과', label: '화학과' },
        { value: '화학·에너지융합학부', label: '화학·에너지융합학부' },
        { value: '바이오신약의과학부', label: '바이오신약의과학부' },
        { value: '바이오헬스융합학부', label: '바이오헬스융합학부' },
        { value: '서비스·디자인공학과', label: '서비스·디자인공학과' },
        { value: '융합보안공학과', label: '융합보안공학과' },
        { value: '컴퓨터공학과', label: '컴퓨터공학과' },
        { value: '청정융합에너지공학과', label: '청정융합에너지공학과' },
        { value: '바이오식품공학과', label: '바이오식품공학과' },
        { value: '바이오생명공학과', label: '바이오생명공학과' },
        { value: 'AI융합학부', label: 'AI융합학부' },
        { value: '간호학과', label: '간호학과' },
        { value: '교육학과', label: '교육학과' },
        { value: '사회교육과', label: '사회교육과' },
        { value: '윤리교육과', label: '윤리교육과' },
        { value: '한문교육과', label: '한문교육과' },
        { value: '유아교육과', label: '유아교육과' }
    ];

    const mailOnOff = () => {
        setMailAlarm(prevState => !prevState);
    }

    const connectionOnOff = () => {
        setConnectionAlarm(prevState => !prevState);
    }

    const editProfil = () => {
        if (isEditing) {
            // 프로필 정보가 모두 입력되었는지 확인
            if (newInfo.some(info => !info)) {
                alert('프로필 정보를 모두 입력해주세요.');
                return;
            }
            setMyinfo(newInfo);
        }
        setIsEditing(!isEditing);
    }

    const myinfoChange = (index, value) => {
        const updatedInfo = [...newInfo];
        updatedInfo[index] = value;
        setNewInfo(updatedInfo);
    }

    const logOut = () => {
        // 로그아웃 로직
    }



    return (
        <div className='myprofil'>
            <div className={`modal-badge ${badgeModal ? 'hide' : ' '}`}>
                <div className="modaldiv">
                    <img src={cancle} alt="X" onClick={badge} />
                    <form action="post" className="form">
                        <div className='formcolumn'><span className="formtext">이름</span><input className="input" type="text" /></div>
                        <div className='formcolumn'><span className="formtext">학과</span><input className="input" type="text" /></div>
                        <div className='formcolumn'><span className="formtext">학번</span><input className="input" type="text" /></div>
                        <div className='formcolumn'><span className="formtext">직책</span><input className="input" type="text" /></div>
                    </form>
                    <FileInputWrapper>
                        <input
                            type="file"
                            accept="image/jpg, image/png, image/jpeg"
                            ref={imageInput}
                            className="fileInput"
                            onChange={handleFileChange}
                        />
                        <div className="btnUpload" onClick={onClickImageUpload}>
                            인증 이미지 선택
                        </div>
                        <div className="fileName">{fileName}</div>
                    </FileInputWrapper>
                    <div className="submitBox">
                        <button className="btnSubmit">전송</button>
                    </div>
                </div>
            </div>
            <div className={`delmodaldiv ${delModal ? 'hide' : ' '}`}>
            <img src={cancle} alt="X" onClick={delBadge} />
                {myBadge.length > 0 && myBadge.map((badge, index) => (
                    <div className="badgelist">
                        
                        <div key={index} id="badgenamediv">
                            <span className="badgename">{badge}</span>
                        </div>
                        <button className="del">삭제하기</button>
                    </div>
                ))}
                <div className="submitBox">
                    <button className="btnSubmit">저장</button>
                </div>
            </div>

            <div className={`wrap  ${badgeModal && delModal ? ' ' : 'blur'}`}>
                <div id="name">
                    <div id="username">{myinfo[0]} 님</div>
                    <div className="badgeBox">
                        {myBadge.length > 0 && myBadge.map((badge, index) => (
                            <div key={index} id="badgenamediv">
                                <span id="badgename">{badge}</span>
                            </div>
                        ))}
                    </div>
                    <div id="badge">
                        <div onClick={badge} id="badge1">역할 뱃지 등록하기</div>
                        <div> / </div>
                        <div onClick={delBadge} id="badge2">삭제하기</div>
                    </div>
                </div>
                <div id="profilBox">
                    <div className="profil">
                        <img src={IMG_PROFIL} alt="imghuman" className="imghuman" />
                        <img src={BGPROFIL} alt="imgprofil" className="imgprofil" />
                        <div id="info">
                            <div className="infotext">
                                <span className="text1">이름</span>
                                {isEditing ? (
                                    <input
                                        className="text2"
                                        value={newInfo[0]}
                                        onChange={(e) => myinfoChange(0, e.target.value)}
                                    />
                                ) : (
                                    <span className="text2">{myinfo[0]}</span>
                                )}
                            </div>
                            <div className="infotext">
                                <span className="text1">학번</span>
                                {isEditing ? (
                                    <input
                                        className="text2"
                                        value={newInfo[1]}
                                        onChange={(e) => myinfoChange(1, e.target.value)}
                                    />
                                ) : (
                                    <span className="text2">{myinfo[1]}</span>
                                )}
                            </div>
                            <div className="infotext">
                                <span className="text1">학과</span>
                                {isEditing ? (
                                    <Select
                                        className="majorSelectOption"
                                        classNamePrefix="custom-select"
                                        value={majorLists.find(dept => dept.value === newInfo[2])}
                                        onChange={(selectedOption) => myinfoChange(2, selectedOption.value)}
                                        options={majorLists}
                                    />
                                ) : (
                                    <span className="text2">{myinfo[2]}</span>
                                )}
                            </div>
                        </div>
                    </div>
                </div>
                <div id="log">
                    <span id="edit" onClick={editProfil}>{isEditing ? "저장하기" : "수정하기"}</span>
                    <span> / </span>
                    <span id="logout" onClick={logOut}>로그아웃</span>
                </div>
                <div className="setdiv">
                    <div className="onoff">
                        <span className="onofftext">메일 알람</span>
                        <div>
                            <img src={mailAlarm ? ON : OFF} alt="imgon" className="onOffBtn" onClick={mailOnOff} />
                        </div>
                    </div>
                    <div className="onoff">
                        <span className="onofftext">수정이들과의 연결</span>
                        <div>
                            <img src={connectionAlarm ? ON : OFF} alt="imgon" className="onOffBtn" onClick={connectionOnOff} />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Myprofil;
