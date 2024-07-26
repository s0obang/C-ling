import React, { useState } from 'react';
import { useParams } from 'react-router-dom';
import socketIo from "socket.io-client";

const Chat = () => {
  const { userId } = useParams();


  const [messages, setMessages] = useState([]);
  const [newMessage, setNewMessage] = useState('');
  const [selectedImage, setSelectedImage] = useState(null);
  const [imagePreview, setImagePreview] = useState(null);



  const handleSendMessage = () => {
    if (newMessage.trim() !== '') {
      setMessages([...messages, newMessage]);
      setNewMessage('');
    }
    if (selectedImage) {
      setMessages([...messages, { image: selectedImage }]);
      setSelectedImage(null);
      setImagePreview(null);
    }
  };


  const handleImageChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setSelectedImage(file);
      setImagePreview(URL.createObjectURL(file));
    }
  };


  const handleKeyPress = (event) => {
    if (event.key === 'Enter') {
      handleSendMessage();
    }
  };

  return (
    <div>
      <h2>이름의 채팅방</h2>
      <div className="chat-messages">
        {messages.map((message, index) => (
          <div key={index} className="chat-message">
            {typeof message === 'string' ? (
              <p>{message}</p>
            ) : (
              <img src={URL.createObjectURL(message.image)} alt="Sent" className="chat-image" />
            )}
          </div>
        ))}
      </div>
      {imagePreview && (
        <div className="image-preview">
          <img src={imagePreview} alt="Preview" className="preview-image" />
        </div>
      )}
      <div className="chat-input">
        <input
          type="text"
          value={newMessage}
          onChange={(e) => setNewMessage(e.target.value)}
          placeholder="메시지를 입력하세요..."
        />
        <input type="file" accept="image/*" onChange={handleImageChange} />
        <button onClick={handleSendMessage}>전송</button>
      </div>
    </div>
  );
};

export default Chat;
