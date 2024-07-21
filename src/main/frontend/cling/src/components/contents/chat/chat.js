import React, { useState } from 'react';
import { useParams } from 'react-router-dom';

// 가상의 사용자 데이터 및 채팅 내용
const initialUserChats = {
  1: { name: 'User1', messages: ['안녕하세요!', '오늘 날씨 좋네요.'] },
  2: { name: 'User2', messages: ['반갑습니다.', '계획이 뭐예요?'] },
  3: { name: 'User3', messages: ['오랜만이에요.', '잘 지내셨나요?'] },
  4: { name: 'User4', messages: ['처음 뵙겠습니다.', '잘 부탁드립니다.'] },
};

const Chat = () => {
  const { userId } = useParams();
  const userChat = initialUserChats[userId];

  // 메시지 상태 관리
  const [messages, setMessages] = useState(userChat ? userChat.messages : []);
  const [newMessage, setNewMessage] = useState('');

  if (!userChat) {
    return <div>채팅 정보를 찾을 수 없습니다.</div>;
  }

  // 메시지 전송 핸들러
  const handleSendMessage = () => {
    if (newMessage.trim() !== '') {
      setMessages([...messages, newMessage]);
      setNewMessage(''); // 입력 필드 초기화
    }
  };

  // 엔터 키로 메시지 전송
  const handleKeyPress = (event) => {
    if (event.key === 'Enter') {
      handleSendMessage();
    }
  };

  return (
    <div>
      <h2>{userChat.name}와의 채팅방</h2>
      <div className="chat-messages">
        {messages.map((message, index) => (
          <div key={index} className="chat-message">
            {message}
          </div>
        ))}
      </div>
      <div className="chat-input">
        <input
          type="text"
          value={newMessage}
          onChange={(e) => setNewMessage(e.target.value)}
          placeholder="메시지를 입력하세요..."
          onKeyPress={handleKeyPress}
        />
        <button onClick={handleSendMessage}>전송</button>
      </div>
    </div>
  );
};

export default Chat;
