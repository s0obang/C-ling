import React, { useState, useEffect, useRef } from 'react';
import '../../../assets/scss/contents/chat/chat.scss';
import Ib from '../../../assets/img/chat/imagebutton.png';
import Sb from '../../../assets/img/chat/sendbutton.png';
import Header from '../../Header'

// Mock messages
const mockMessages = [
  { id: 1, sender: 'User1', message: '안녕하세요!', type: 'text' },
  { id: 2, sender: 'Me', message: '안녕하세요, 만나서 반가워요!', type: 'text' },
];

// 상대방의 정보를 담은 객체
const userInfo = {
  id: 2,
  name: 'User2',
  studentNumber: '20231138',
  department: '제과제빵학과',
  profileImage: 'https://example.com/profile.jpg', // 프로필 사진의 URL
};

const Chat = () => {
  const [messages, setMessages] = useState(mockMessages);
  const [inputMessage, setInputMessage] = useState('');
  const messagesEndRef = useRef(null);

  // 메시지가 추가될 때마다 스크롤을 맨 아래로 이동시키는 useEffect
  useEffect(() => {
    messagesEndRef.current?.scrollIntoView({ behavior: 'smooth' });
  }, [messages]);

  const sendMessage = () => {
    if (inputMessage.trim()) {
      const newMessage = {
        id: messages.length + 1,
        sender: 'Me',
        message: inputMessage,
        type: 'text',
      };
      setMessages([...messages, newMessage]);
      setInputMessage('');
    }
  };

  const sendImage = (event) => {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onloadend = () => {
        const newMessage = {
          id: messages.length + 1,
          sender: 'Me',
          message: reader.result,
          type: 'image',
        };
        setMessages([...messages, newMessage]);
      };
      reader.readAsDataURL(file);
    }
  };

  return (
    <div className="chat-container">
      <Header />
      {/* 상대방의 정보를 상단에 표시 */}
      <div className="chat-header">
        <div className="profile-img">
          <img
            src={userInfo.profileImage}
            alt={`${userInfo.name}의 프로필`}
          />
        </div>

        <div className="user-info">
          <div className='text'>{userInfo.name}</div>
          <div className='text'>{userInfo.studentNumber}</div>
          <div className='text'>{userInfo.department}</div>
        </div>
      </div>
      <div className="chat-messages">
        {messages.map((msg) => (
          <div
            key={msg.id}
            className={`chat-message ${msg.sender === 'Me' ? 'me' : 'other'}`}
          >
            <strong>{msg.sender}: </strong>
            {msg.type === 'text' ? (
              <span>{msg.message}</span>
            ) : (
              <img src={msg.message} alt="sent image" className="sent-image" />
            )}
          </div>
        ))}
        {/* 스크롤 위치를 조정하기 위한 요소 */}
        <div ref={messagesEndRef} />
      </div>
      <div className="chat-input">
        
        <input
          type="text"
          value={inputMessage}
          onChange={(e) => setInputMessage(e.target.value)}
          placeholder="메시지를 입력하세요..."
        />
        <input
          type="file"
          accept="image/*"
          onChange={sendImage}
          style={{ display: 'none' }}
          id="image-upload"
        />
        <label htmlFor="image-upload" className="image-upload-label">
          <img src={Ib} alt="이미지 전송" />
        </label>

        <button onClick={sendMessage} className="send-button">
          <img src={Sb} alt="전송" />
        </button>

      </div>
    </div>
  );
};

export default Chat;
