import React, { useState, useEffect, useRef } from 'react';
import { useLocation } from 'react-router-dom';
import '../../../assets/scss/contents/chat/chat.scss';
import Ib from '../../../assets/img/chat/imagebutton.png';
import Sb from '../../../assets/img/chat/sendbutton.png';
import Header from '../../Header';
import WebSocketService from './WebSocketService';
import axios from 'axios';
import { useParams } from 'react-router-dom';

const Chat = () => {
  const { roomId } = useParams(); 
  const location = useLocation(); 
  const { userInfo: locationUserInfo } = location.state || {}; 
  const [userInfo, setUserInfo] = useState(locationUserInfo || {
    profileImage: '',
    name: '',
    studentNumber: '',
    department: ''
  });
  const [messages, setMessages] = useState([]);
  const [inputMessage, setInputMessage] = useState('');
  const messagesEndRef = useRef(null);

  useEffect(() => {
    axios.get(`http://13.48.207.238:1234/oldChat/${roomId}`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`
      }
    })
    .then(response => {
      if (response.data && response.data.chatEntities) {
        setMessages(response.data.chatEntities);
      }
    })
    .catch(error => {
      console.error("Failed to fetch old chat messages:", error);
    });

    WebSocketService.connect(roomId, onMessageReceived);

    return () => {
      WebSocketService.disconnect();
    };
  }, [roomId]);

  const onMessageReceived = (message) => {
    const parsedMessage = JSON.parse(message.body);
    setMessages(prevMessages => [...prevMessages, parsedMessage]);
  };

  const sendMessage = () => {
    if (inputMessage.trim() !== '') {
      axios.get('http://13.48.207.238:1234/api/auth/getLoggedInUsername', {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('accessToken')}`
        }
      })
      .then(response => {
        const sender = response.data;
        const messageObject = {
          room: roomId,
          sender: sender,
          message: inputMessage,
          sendDate: new Date().toISOString(),
          imageBytes: null
        };

        WebSocketService.send(roomId, messageObject);
        setInputMessage('');
      })
      .catch(error => {
        console.error("Failed to get logged in username:", error);
      });
    }
  };

  const sendImage = (event) => {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onloadend = () => {
        axios.get('http://13.48.207.238:1234/api/auth/getLoggedInUsername', {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('accessToken')}`
          }
        })
        .then(response => {
          const sender = response.data;
          const messageObject = {
            room: roomId,
            sender: sender,
            message: "Image sent",
            sendDate: new Date().toISOString(),
            imageBytes: reader.result.split(',')[1] 
          };

          WebSocketService.send(roomId, messageObject);
        })
        .catch(error => {
          console.error("Failed to get logged in username:", error);
        });
      };
      reader.readAsDataURL(file);
    }
  };

  useEffect(() => {
    messagesEndRef.current?.scrollIntoView({ behavior: 'smooth' });
  }, [messages]);

  return (
    <div className="chat-container">
      <Header />
      <div className='content'>
        <div className="chat-header">
          <div className="profile-img">
            <img src={userInfo.profileImage} alt={`${userInfo.name}의 프로필`} />
          </div>

          <div className="user-info">
            <div className='text'>{userInfo.name}</div>
            <div className='text'>{userInfo.studentId}</div>
            <div className='text'>{userInfo.major}</div>
          </div>
        </div>

        <div className="chat-messages">
            {messages.map((msg, index) => (
                <div key={index} className={`chat-message ${msg.sender === userInfo.studentNumber ? 'me' : 'other'}`}>
                {msg.imageBytes ? (
                    <img src={`data:image/jpeg;base64,${msg.imageBytes}`} alt="sent image" className="sent-image" />
                ) : (
                    <span>{msg.message}</span>
                )}
                </div>
            ))}
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
    </div>
  );
};

export default Chat;
